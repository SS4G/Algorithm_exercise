from urllib import parse,request
#普通数据使用
textmod = {
"DDDDD": "2016140402",
"upass": "ss4g0616",
"0MKKey": ""}
textmod = parse.urlencode(textmod).encode()
print(textmod)
#输出内容:b'{"params": {"user": "admin", "password": "zabbix"}, "auth": null, "method": "user.login", "jsonrpc": "2.0", "id": 1}'
header_dict = {
"Host": "10.3.8.211",
"Connection": "keep-alive",
"Content-Length": "39",
"Cache-Control": "max-age=0",
"Origin": "http://10.3.8.211",
"Upgrade-Insecure-Requests": "1",
"User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.113 Safari/537.36",
"Content-Type": "application/x-www-form-urlencoded",
"Accept": "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8",
"Referer": "http://10.3.8.211/",
"Accept-Encoding": "gzip, deflate",
"Accept-Language": "zh-CN,zh;q=0.8",
"Cookie": "myusername=2016140402; username=2016140402; smartdot=ss4g0616"
}
url='http://10.3.8.211'
req = request.Request(url=url,data=textmod,headers=header_dict, method="POST")
res = request.urlopen(req)
statusCode = res.getcode()
print(statusCode)
print(res.info())