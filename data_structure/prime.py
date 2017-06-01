#n = 1000001
#res = []
#for i in range(2,n):
#    if i%10000 == 0:
#        print(i)
#    upper = int(i**0.5)+1
#    flag = True
#    for k in range(2, upper+1):
#        if i % k == 0:
#            flag = False
#            break
#    if flag:
#        res.append(i)
#f = open("prime.txt", "w", encoding="utf-8")
#f.write(",".join([str(p) for p in res]))
#f.close()
f = open("prime.txt", "r", encoding="utf-8")
print(len(f.readline().split(",")))
f.close()