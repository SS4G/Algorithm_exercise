class HashNode:
    """
    哈希链表的节点
    """
    def __init__(self, keyword=None,val=None):
        self.val = val
        self.keyword = keyword
        self.next = None

class SepreateLinkHash:
    """
    使用分离链接发构建的哈希表
    """
    def __init__(self, table_size=10, hash_function=None):
        """
        在创建哈希表时就指定好表的大小
        :param table_size:
        """
        self.table_size = table_size
        self.table = self.create_hash_table(table_size=table_size)
        self.hash_fun = hash_function

    def hash_fun0(self, keyword, table_size=10):
        """
        :param keyword:要插入到哈希表中的关键字
        :param table_size:
        :return: 返回求得的哈希值
        暂时使用求余数运算 是一个可以替换的函数
        """
        return keyword%table_size

    def create_hash_table(self, table_size=10):
        list0 = [None, ]*table_size
        return list0

    def insert(self, keyword, val):
        """
        将关键字插入到哈希表中指定的位置
        对于相同的哈希映射 关键字 插入到链表头部
        :param keyword:
        :return:
        """
        hashed_key = self.hash_fun(keyword=keyword, table_size=self.table_size)
        tmp = HashNode(keyword=keyword, val=val)
        if self.table[hashed_key] is not None:
            tmp.next = self.table[hashed_key]  # 将新的节点插入到链表的头部
        self.table[hashed_key] = tmp

    def query(self, keyword):
        """
        根据关键字查询到相关的单元 返回相关的单元的值的引用
        :param key_work:
        :return: handler if find else None
        """
        hashed_key = self.hash_fun(keyword=keyword, table_size=self.table_size)
        if self.table[hashed_key] is not None:
            tmp = self.table[hashed_key]
            while tmp.keyword != keyword and (tmp is not None):
                tmp = tmp.next
            return None if tmp is None else tmp
        else:
            return None

# Test
if __name__ == "__main__":
    hashtable0 = SepreateLinkHash(table_size=1000)








