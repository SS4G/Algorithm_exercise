import datetime
# below is example
starttime = datetime.datetime.now()
result = []
while True:
    which = input("finish which problem:")
    if which == "q":
        break
    endtime = datetime.datetime.now()
    str0 = which+": complete at %4d : %02d" % (int(endtime.timestamp() - starttime.timestamp())//60, int(endtime.timestamp() - starttime.timestamp()) % 60)
    starttime = datetime.datetime.now()
    result.append(str0)
    print(str0)

for item in result:
    print(item)
