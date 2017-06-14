import re
pattern = "\\w{2}.+?(\\w{3})"
str0 = "abcd lkjs iuiw"
# 使用编译后的pattern版本
p = re.compile(pattern)  # 生成一个pattern对象
print(type(p))
matchobj = p.match(str0)  # 返回一个捕获对象 如果什么都没有匹配到 就返回一个None match只匹配开头
searchobj = p.search(str0)  # 返回一个捕获对象 如果什么都没有匹配到 就返回一个None search冲任意位置
print(type(matchobj))
print(type(searchobj))
print(matchobj.group(0))
print(matchobj.group(1))  # 捕获出分组中的内容

pattern = "(\\d+).*?(\\.+).*(\\1)"
str0 = " l 123  ... ls 123"
res = re.match(pattern, str0)
assert res is None, "??"  # 应该是匹配不上什么东西
res = re.search(pattern, str0)
print(res)
print("group(0)", res.group(0))
print("group(1)", res.group(1))
print("group(2)", res.group(2))
print("group(3)", res.group(3))

pattern = "\\d{3}"
str0 = " 123 sdf 718 al 890 99 8276 232"
l = re.findall(pattern, str0)  # 从前向后搜索所有结果
print(l)



