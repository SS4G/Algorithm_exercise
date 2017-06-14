import datetime
# below is example
starttime = datetime.datetime.now()
currentProblem = ""
currentTimeUsed = 0
f = open("D:\\work_space\\Algorithm_practice_py\\AlgorithmTraining\\common_script\\timeRecord.txt", "r",
         encoding="utf-8")
result = f.readlines()
f.close()
while True:
    cmd = input(">>")
    if cmd == "quit":
        break
    elif cmd == "complete":
        print(currentProblem+"completed!")
        endtime = datetime.datetime.now()
        currentTimeUsed += endtime.timestamp() - starttime.timestamp()
        str0 = currentProblem + ": complete at %4d : %02d\n" % (currentTimeUsed // 60, currentTimeUsed % 60)
        print(str0)
        result.append(str0)
        f = open("D:\\work_space\\Algorithm_practice_py\\AlgorithmTraining\\common_script\\timeRecord.txt", "w", encoding="utf-8")
        f.writelines(result)
        f.close()
    elif cmd == "pause":
        print("pause at :", currentProblem)
        endtime = datetime.datetime.now()
        currentTimeUsed += endtime.timestamp() - starttime.timestamp()
    elif cmd == "restart":
        starttime = datetime.datetime.now()
        print("restart problem:"+currentProblem)
    elif cmd == "start":
        currentTimeUsed = 0
        currentProblem = input("input the problem No.")
        starttime = datetime.datetime.now()
        print("start at problem"+currentProblem)
    else:
        pass
print("process terminated")