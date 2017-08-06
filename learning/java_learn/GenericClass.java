package AlgorithmTraining.learning.java_learn;

import javax.jnlp.IntegrationService;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

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

class MyList<T> {
    private T[] arr = null;
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
        MyList<Integer> list = new MyList<>(Integer.class, 10);
        Integer[] arr = list.getList();
        //LinkedList<Integer>[] integerList = (LinkedList<Integer>)LinkedList[10];

    }
}
