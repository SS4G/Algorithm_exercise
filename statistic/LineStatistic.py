import os


def get_all_file0(base_dir):
    file_list = []
    get_all_file(base_dir, file_list=file_list)
    return file_list


def get_all_file(base_dir, file_list=None):
    this_folder = os.listdir(base_dir)
    for name in this_folder:
        if os.path.isfile(base_dir + "/" + name):
            file_list.append(base_dir + "/" + name)
        elif os.path.isdir(base_dir + "/" + name):
            get_all_file(base_dir + "/" + name, file_list=file_list)
        else:
            print("这是什么文件？？")
    return None


def get_file_type(file_name):
    return file_name.split(".")[-1]

if __name__ == "__main__":
    # arguments
    # 要计算的基础目录
    base_dir = "D:\work_space\Algorithm_training_java\src\AlgorithmTraining"  # os.getcwd()
    # 要统计的文件扩展名（仅限于文本文件 二进制文件不行）
    cnt_file_type = ["java", "py", "c", "cpp", ]
    # extname_set = set([])

    print("statistic directory is :", base_dir)
    filename_list = []
    all_files = get_all_file0(base_dir)
    # extname_set = set(filter(lambda str:len(str)<10, [name.split(".")[-1] for name in all_files]))
    # print(extname_set)

    useful_files = filter(lambda s: s.split(".")[-1] in cnt_file_type, all_files)
    cnt = 0

    CountByType_dict = {}
    for type in cnt_file_type:
        CountByType_dict[type] = 0

    for file in useful_files:
        f = open(file, "r", encoding="utf-8")
        CountByType_dict[get_file_type(file)] += len(f.readlines())
        f.close()
    total = 0
    for type in CountByType_dict:
        print("type ", type, "  has :", CountByType_dict[type], " lines")
        total += CountByType_dict[type]

    print("total lines is :", total)