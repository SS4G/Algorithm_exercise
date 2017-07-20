class Iter0:
    def __init__(self, time):
        self.time = time
        self.cur = 0

    def __iter__(self):
        return self

    def __next__(self):
        if self.cur < self.time:
            self.cur += 1
            return str(self.cur) + "res"
        raise StopIteration

def gen0(amount):
    for i in range(amount):
        yield str(i) + "yy"

if __name__ == "__main__":
    for i in Iter0(20):
        print(i)
