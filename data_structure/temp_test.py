class Obj:
    def __init__(self, val):
        self.val = val
    def replace_self(self):
        self = Obj(val=self.val+1)

a = Obj(1)
print(id(a))
a.replace_self()
print(id(a))
print(a.val)