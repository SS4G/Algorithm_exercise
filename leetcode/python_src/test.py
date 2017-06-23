import collections
a = {"a":1, "b":2, "c":3}
print(a.items())

d = collections.defaultdict(list)


d["a"].append(43)
print(d["a"])