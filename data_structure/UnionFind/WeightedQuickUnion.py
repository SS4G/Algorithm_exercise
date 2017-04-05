# 并查集 quick-union 版本
# 该版本将两个分量链接在一起时较快
# 总是将小的分量链接在大的分量上 可以保证权重为k是 树的深度小于lg(k)
# author:g55
# date April 5th 2017
# status: complete
import os
class WeightedQuickFind:
    def __init__(self, length):
        """
        所有节点所属的连通分量最初使用节点的名称来表示
        所有节点的名称从0～length-1
        length是所有不同节点的个数
        :param length:
        """
        self.item_arr = [j for j in range(length)]  # 由节点id作为索引
        self.item_weight_arr = [1 for j in range(length)]
        self.node_amount = length
        self.union_cnt = length

    def find(self, id):
        """
        O(1)
        :param id: 节点的名称
        :return: 节点所属联通分量的名称
        """
        tmp_id = id
        while self.item_arr[tmp_id]!=tmp_id:
            tmp_id = self.item_arr[tmp_id]  # point to next node
        return tmp_id

    def union(self, p, q):
        """
        O(n)
        输入的p，q表示p，q在一个连通分量
        :param p:节点p
        :param q:节点q
        :return:
        """
        p_id = self.find(p)
        q_id = self.find(q)
        if p_id != q_id:
            if self.item_weight_arr[p_id] > self.item_weight_arr[q_id]:
                self.item_arr[q_id] = p_id # 把权重小的树挂在权重大的树上面
                self.item_weight_arr[p_id] += self.item_weight_arr[q_id]
            else:
                self.item_arr[p_id] = q_id  # 把权重小的树挂在权重大的树上面
                self.item_weight_arr[q_id] += self.item_weight_arr[p_id]
            self.union_cnt -= 1
        return None

    def union_count(self):
        return self.union_cnt

    def make_union(self, pairs):
        for pair in pairs:
            self.union(pair[0], pair[1])
        return self.union_count()

if __name__ == "__main__":
    testcase_path = "/home/mi/SS4G/Alf_py_linux2/AlgorithmTraining/data_structure/UnionFind/testcase/"
    for file_name in os.listdir(testcase_path):
        # f = open(testcase_path+file_name)
        f = open(testcase_path+file_name)
        lines = f.readlines()
        # 测试文件的第一行的第一个数字表示节点数目 第二个数字表示真正的联通分量的个数
        testcase_length = int(lines[0].strip().split()[0])
        union_amount = int(lines[0].strip().split()[1])
        testcase_pairs = [(int(j[0]), int(j[1])) for j in [i.strip().split() for i in lines[1:] if len(i)>=2]]
        f.close()

        quf = WeightedQuickFind(testcase_length)
        for pair in testcase_pairs:
            quf.union(pair[0], pair[1])
        assert quf.union_count()==union_amount, "result ERROR: test file has %d unions." % union_amount
    print("Test process terminated successfully!")
