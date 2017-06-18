import os
import pymysql
config = {
          'host': '127.0.0.1',
          'port': 3306,
          'user': 'root',
          'password': 'ss4g0616',
          'db': 'ydy_web',
          'charset': 'utf8mb4',
          'cursorclass': pymysql.cursors.DictCursor,
          }
connection = pymysql.connect(**config)
cursor = connection.cursor()
cursor.execute("select topic_id from ydy_web_topic")
connection.commit()
print(cursor.fetchall())  # 取回结果
connection.close()

