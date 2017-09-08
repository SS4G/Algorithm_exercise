# -*- coding: utf-8 -*-

# Define here the models for your scraped items
#
# See documentation in:
# http://doc.scrapy.org/en/latest/topics/items.html

import scrapy
from WeatherGet.items import *

class WeatherItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    city = scrapy.Field()
    temperature = scrapy.Field()  # 摄氏温度
    date = scrapy.Field()  # 温度时间
    weather = scrapy.Field()  # 天气状况 晴 多云 等
    wind = scrapy.Field()  # 风向
    #windRank = scrapy.Field()  # 风级

class ProvinceItem(scrapy.Item):
    provinceName = scrapy.Field()
    provinceHref = scrapy.Field()


class CityItem(scrapy.Item):
    cityName = scrapy.Field()
    cityHref = scrapy.Field()
