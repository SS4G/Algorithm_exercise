package AlgorithmTraining.learning.java_learn;


import java.lang.reflect.Array;
import java.util.*;
/**
 * Created by 903 on 2017/8/5.

 */

/**
 * 粗浅的理解上可以把类型参数想象成像 编译器会把左右的类型参数在编译是替换成为具体的参数类型
 * 但是实际上的泛型的实现并不是这样的，这种样的实现是cpp的方法 java是使用擦除来实现的
 * 比如 类型参数是T 那么就把所有的T的地方实际上是用Object代替的。如果是<T extends X> 就表明
 * T是X的子类或者本身这样就可以去调用X所拥有的方法
 * 这种实现本质上是 codesharing 即对于一个泛型类 虽然我们传入了不同的参数 但是实际上编译好的代码只有一份
 * 并不会像cpp那样真正的为每一个类来创建一个泛型类编译好的对象，所以这就限制了java泛型的使用。
 * 毕竟只有一段真正的代码，所以没有真正的为每一个对象创建所有的方法。 但是如果直接对T去转型 都是不安全也是不允许的
 *
 * @param <T>
 */

//HolderGeneric 本质上的实现是 Holder 只不过是区别在于 编译器会给HolderGeneric 在对于Holder
class HolderGeneric<T> {
    T tObj;
    HolderGeneric (T obj) {
        tObj = obj;
    }

    T get() {
        return tObj;
    }

    void set(T obj) {
        tObj = obj;
    }
}

class  Holder {
    Object tObj;
    Holder (Object obj) {
        this.tObj = obj;
    }

    Object get() {
        return tObj;
    }

    void set(Object obj) {
        tObj = obj;
    }
}

class Fruit {
    void dispClassInfo() {
        System.out.println(Fruit.class.getSimpleName());
    }
}

class Apple extends Fruit {
    void dispClassInfo() {
        System.out.println(Apple.class.getSimpleName());
    }
}

class Orange extends Fruit {
    void dispClassInfo() {
        System.out.println(Orange.class.getSimpleName());
    }
}

class MyList<T> {
    private T[] arr = null; //可以创建泛型对象的引用 因为这个引用可以用Object的引用来替代
    //!!! T var = new T();// 不可以编译 因为T在泛型代码内部器类型信息已经被擦除了 所以不能确定具体该如何分配空间
    //!!! T[] arr = new T[100]; //同样不可以编译 和上面的原因一样 虽然也可以创建 Objcet数组来替代, 但是数组也是一类对象
    // 创建数组对象也需要知道具体的对象类型 而此时泛型代码在编译时 并不知道具体的对象是什么
    // 综上所述: 当在泛型代码内部需要创建与泛型有关的对象时 需要知道具体对象的大小才能去分配内存
    // 但是泛型代码可以认为是独立于具体的类型事先编译出来的 这是java的codeshare机制 所以这些信息在泛型代码内部并不知道
    // 所以也就不能执行这类操作
    // 如果真的想要穿件对象 就需要通过某些入口传入 类型信息 然后利用反射机制来穿件具体的对象
    // 这样对象就可以动态的在运行时创建
    private T[] arr1 = (T[])new Object[10]; //这个样倒是可以 但是T任然实际上没有任何类型信息 仍然是Object[]

    @SuppressWarnings("unchecked")
    MyList(Class<?> type, int size) {
        arr = (T[])Array.newInstance(type, size); //这里面的Array类实际上就是 数组的那个类
        //arr = new T[Size]是错误的 虽然本质上是Object 但是java不允许直接创建类型不明的的数组
        //所以如果想要使用数组的话， 就应该使用Array.newInstance(type, size) 来指明要创建的数组的对象的真正的类型
        //而这样做需要给函数传入一个真正的类型参数
    }

    public void showType() {
        if (arr.length > 0)
            System.out.println(arr[0].getClass().getName());
    }

    @SuppressWarnings("unchecked")
    public T[] getList() {
        return arr;
    }
}

public class GenericClass {
    public static void main(String[] args) {
        Holder holder0 = new Holder(new Apple());
        Apple apple0 = (Apple)holder0.get();
        apple0.dispClassInfo();

        holder0.set(new Orange());
        //!!! .ClassCastException : Apple orange0 = (Apple)holder0.get(); //虽然放进去的是一个Orange对象 但是由于在内部被转换成了Object
        //丢失了本身的类型信息，所以如果在get返回Object后 将他强制转换为Apple 编译器并不会发现 但是这个问题会在
        //运行时出现异常 提示类型转换错误 因为运行时系统发现将一个Orange对象转换为了Apple对象 因为Orange对象虽然被Object引用擦除了类型信息
        //但是由于RTTI的原因 对象本身还是保留了自己的真正的类型信息
        //orange0.dispClassInfo(); //虽然Orange实际上是一个Apple引用 但是因为多态机制 他打印出来的信息是 Orange的


        HolderGeneric<Apple> holderGenericApple = new HolderGeneric<>(new Apple());
        Apple apple1 = holderGenericApple.get();
        // !!! holderGenericApple.set(new Orange()); //这句不能够运行 根据泛型的边界检查
        // 在给内部的类型引用复制时 会检查类型
        // !!! Orange orange1 = holderGenericApple.get(); //根据反省的边界检查
        // 编译器会在返回泛型的类型变量T类型对象的时候会由编译器插入泛型的类型转换代码
        // 也就是说 在你返回T 类型的对象的时候 虽然泛型是用擦除来实现的
        // 本质上这个对象在返回时应该只是一个Object 但是因为是离开泛型代码的对象 编译器会自动的将他转换为原本的泛型参数所指示的对象
        // 就像Holder 返回Object后加上了一个强制类型转换

        //所以根据上面的对比我们可以发现 泛型将本来可能发生在运行时期的问题 提前到了编译期 是的更早的处理问题
        //所以 泛型内部的代码本身是没有类型信息的， 全部是靠编译器在反省代码的边界处进行检查

        //引用会擦除对象的类型信息，但是对象会一直保留自己的类型信息

        MyList<Integer> list = new MyList<>(Integer.class, 10);
        Integer[] arr = list.getList();
        LinkedList<Integer>[] integerList = (LinkedList<Integer>[]) new LinkedList[10];
        integerList[0] = new LinkedList<Integer>();
        integerList[0].add(1);

        //直接创建泛型数组是不允许的原因如下
        /**
        List<String>[] lsa = new List<String>[10]; // Not really allowed.
        Object o = lsa;
        Object[] oa = (Object[]) o;
        List<Integer> li = new ArrayList<Integer>();
        li.add(new Integer(3));
        oa[1] = li; // Unsound, but passes run time store check  //放入了一个本来不应该允许的类型
        String s = lsa[1].get(0); // Run-time error: ClassCastException.

         这种情况下，由于JVM泛型的擦除机制，在运行时JVM是不知道泛型信息的，
         所以可以给oa[1]赋上一个ArrayList<Integer>而不会出现ArrayStoreException，
         但是在取出数据的时候却要做一次类型转换，所以就会出现ClassCastException，
         如果可以进行泛型数组的声明，上面说的这种情况在编译期将不会出现任何的警告和错误，
         只有在运行时才会出错。而对泛型数组的声明进行限制，对于这样的情况，
         可以在编译期提示代码有类型安全问题，比没有任何提示要强很多
         所以最终为了安全 组织了一般的泛型数组的定义
        **/
        //！！！List<Fruit> flist = new ArrayList<Apple>(); //编译不可通过 只有主要的类型可以自动向上转型
        //而对于类型参数 限制是必须要一样
        //下面两种使用通配符的方式是 可以的 保证了参数类型的向上转型
        List<? extends Fruit> flist = new ArrayList<Apple>();
        List<?> flist1 = new ArrayList<Apple>();
        //还可以使用无限定通配符来完成泛型数组的创建 但是在真正需要使用的时候需要显示的类型转换
        //也可以表示成原生的没有类型参数的泛型数组
        //带有extends 通配符的

        //但是泛型数组并不好用 各种限制颇多 主要是因为在想数组中装入对象时 可能会缺少应该有的类型检查 导致
        //像猫队中插入狗的问题 所以更推荐使用泛型容器 泛型容器能够更好地保证边界上的检查 即在对象插入容器时进行类型检查
        //在取出对象时 进行具体的类型转换
        //顺便说一句 在引用类型检查时， 泛型可以被认为是一种类
        List<?>[] flist2 = new ArrayList<?>[100];
        List[] flist3 = new ArrayList[100];
        //使用显示类型转换的方法就像下面
        flist2[0] = new ArrayList<Integer>(100);
        flist2[1] = new ArrayList<Double>(100);
        flist2[2] = new ArrayList<String>(100);
        //!!!flist1.add(1); 可以创建flist1 但是不能直接通过这种方式去添加对象 之恩个

        //另外 泛型是不支持重载的 因为所有的类型参数在泛型类内部都被擦掉了
    }
}
