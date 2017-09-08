import pymysql
if __name__ == "__main__":
    with open("D:\work_space\Algorithm_practice_py\AlgorithmTraining\spiderSample\WeatherGet\WeatherGet\weather.txt",
              "r", encoding="utf-8") as fp:
        date = ""
        weather = ""
        wind = ""
        city = ""
        cnt = 0
        config = {
            'host': '127.0.0.1',
            'port': 3306,
            'user': 'root',
            'password': 'ss4g0616',
            'db': 'weather',
            'charset': 'utf8mb4',
            'cursorclass': pymysql.cursors.DictCursor,
        }
        db = pymysql.connect(**config)
        with db.cursor() as cursor:
            # 执行sql语句，插入记录
            total = 0
            for line in fp:
                # striped = line.strip()
                if len(line) <= 1:
                    cnt = 0
                else:
                    if cnt == 0:
                        date = line.strip()
                    elif cnt == 1:
                        weather = line.strip()
                    elif cnt == 2:
                        wind = line.strip()
                    elif cnt == 3:
                        city = line.strip()[:-4]

                        item = {}
                        item["date"] = '"' + "2017-" + date[:2] + "-" + date[3:5] + '"'
                        item["weather"] = '"' + weather + '"'
                        item["wind"] = '"' + wind + '"'
                        item["cityName"] = '"' + city + '"'
                        #print(item)
                        total += 1
                        sql = 'INSERT INTO weather1Day (cityName, weather, temperature, wind, date) VALUES (%s, %s, %d, %s, %s)' % (item["cityName"], item["weather"], 22, item["wind"], item["date"])
                        print(sql)
                        #sql = 'INSERT INTO weather1Day (cityName, weather, temperature, wind, date) VALUES (%s, %s, %d, %s, %s)' % ('"CITY"', '"RAINY"', 22, '"wind"', '"1980-08-08"')
                        #sql = 'INSERT INTO weather1Day (cityName, weather, temperature, wind, date) VALUES ("CITY", "RAINY", 22, "wind", "1980-08-08")'
                        cursor.execute(sql)
                    cnt += 1
            db.commit()  # 没有设置默认自动提交，需要主动提交，以保存所执行的语句
            print(total)


