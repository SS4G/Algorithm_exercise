import os
import re
if __name__ == "__main__":
    dstPath = "D:\work_space\Algorithm_practice_py\AlgorithmTraining\leetcode\python_src"
    os.chdir(dstPath)
    nameList = os.listdir(os.getcwd())
    for name in filter(lambda s: "py" in s, nameList):
        if re.match("^\\d+", name.split(".")[0]) is not None:
            print(name)
            os.system("cp "+name+" Leet"+name.split(".")[0]+".py")
