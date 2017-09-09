# -*- coding: utf-8 -*-
import scrapy
#from AlgorithmTraining.spiderSample.WeatherGet.WeatherGet.items import *
from WeatherGet.items import *


class GetprovinceSpider(scrapy.Spider):
    name = "getProvinceSpider"
    allowed_domains = ["www.tianqi.com/"]
    start_urls = ['http://www.tianqi.com//']

    def parse(self, response):
        provinceLinks = response.css('.tqqgsf a')
        # items = []
        # return scrapy.Request("http://www.tianqi.com/province/shanxi/", self.parseProvince, dont_filter=True)

        for link in provinceLinks:
            newitem = ProvinceItem()
            newitem['provinceName'] = link.xpath("text()").extract()[0]
            newitem['provinceHref'] = link.xpath("@href").extract()[0]
            # items.append(newitem)
            yield newitem  # call pipeline
            # print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAa")
            #yield scrapy.Request(newitem['provinceHref'], callback=self.parseProvince)
            yield scrapy.Request(newitem['provinceHref'], callback=self.parseProvince, dont_filter=True)

    def parseProvince(self, response):  # 解析对应的省 获得省内的所有区县
        print("--------------------------------------------------------------")
        # cityHref = response.css(".tqxsCont ul li a")
        # response.css(".tqxsCont")[0].xpath("ul/li/a/@href").extract()
        cityHref = response.css(".tqxsCont")[0].xpath("ul/li/a")
        for city in cityHref:
            newitem = CityItem()
            newitem["cityHref"] = city.xpath("@href").extract()[0]
            newitem["cityName"] = city.xpath("text()").extract()[0]
            yield newitem  # call pipeline
            yield scrapy.Request(newitem['cityHref'], callback=self.parseCity, dont_filter=True)

    def parseCity(self, response):  # 解析区县 获得区县的天气
        print("*************************************************************")
        todayText = response.xpath("//div[@class='tqshow']")[0]
        item = WeatherItem()
        item["city"         ] = todayText.xpath("h3/text()").extract()[0]
        item["temperature"  ] = "~".join(todayText.xpath("font/text()").extract())
        item["date"         ] = todayText.xpath("//li[@class='time']/text()").extract()[0]
        item["weather"      ] = todayText.xpath("//li[@class='cDRed']/text()").extract()[0]
        item["wind"         ] = todayText.xpath("//li[@style]/text()").extract()[0]
        return [item, ]