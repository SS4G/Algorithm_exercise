import os


def get_all_file(base_dir, file_list=None):
    if file_list is None:
        file_list = []
        this_folder = os.listdir(base_dir)
        for name in this_folder:
            if os.path.isfile(base_dir+"/"+name):
                file_list.append(base_dir+"/"+name)
            elif os.path.isdir(base_dir+"/"+name):
                get_all_file(base_dir+"/"+name, file_list=file_list)
            else:
                print("这是什么文件？？")
                pass
        return file_list
    else:
        this_folder = os.listdir(base_dir)
        for name in this_folder:
            if os.path.isfile(base_dir + "/" + name):
                file_list.append(base_dir + "/" + name)
            elif os.path.isdir(base_dir + "/" + name):
                get_all_file(base_dir + "/" + name, file_list=file_list)
            else:
                print("这是什么文件？？")
                pass
        return None


if __name__ == "__main__":
    # arguments
    # 要计算的基础目录
    base_dir = "/home/mi/workspace0"  # os.getcwd()
    # 要统计的文件扩展名（仅限于文本文件 二进制文件不行）
    cnt_file_type = ["scala", "java", "py", "xml", "js", "sh"]
    extname_set = set([])

    print(base_dir)
    filename_list = []
    all_files = get_all_file(base_dir)
    # extname_set = set(filter(lambda str:len(str)<10, [name.split(".")[-1] for name in all_files]))
    # print(extname_set)

    useful_files = filter(lambda s: s.split(".")[-1] in cnt_file_type, all_files)
    cnt = 0
    for file in useful_files:
        f = open(file, "r", encoding="utf-8")
        cnt += len(f.readlines())
        f.close()
    print("total lines is :", cnt)