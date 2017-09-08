# -*- coding: utf-8 -*-

# Define your item pipelines here
#
# Don't forget to add your pipeline to the ITEM_PIPELINES setting
# See: http://doc.scrapy.org/en/latest/topics/item-pipeline.html

from WeatherGet.items import *


class WeathergetPipeline(object):
    def process_item(self, item, spider):
        if isinstance(item, ProvinceItem):
            with open("province.txt", "a", encoding="utf-8") as fp:
                fp.write((item["provinceName"] + "\n"))
                fp.write((item["provinceHref"] + "\n"))
            return item
        elif isinstance(item, CityItem):
            with open("city.txt", "a", encoding="utf-8") as fp:
                fp.write((item["cityName"] + "\n"))
                fp.write((item["cityHref"] + "\n"))
            return item
        elif isinstance(item, WeatherItem):
            with open("weather.txt", "a", encoding="utf-8") as fp:
                fp.write((item["city"         ] + "\n"))
                fp.write((item["temperature"  ] + "\n"))
                fp.write((item["date"         ] + "\n"))
                fp.write((item["weather"      ] + "\n"))
                fp.write((item["wind"         ] + "\n"))
            return item
