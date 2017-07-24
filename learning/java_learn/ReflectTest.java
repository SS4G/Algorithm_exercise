package AlgorithmTraining.learning.java_learn;

/**
 * Created by BUPT_SS4G on 2017/7/24.
 *
 */
import java.lang.reflect.*;
class Duck {
    public int id = -1;
    Duck() {
        this.id = -2;
    }

    Duck(int id) {
        this.id = id;
    }

    public String f() {
        return "Duck" + id + ": f()";
    }
}

class Duck0 extends Duck {
    Duck0() {
        super();
    }

    Duck0(int i) {
        super(i);
    }

    public String f0() {
        return "Duck0" + id + ":f0()";
    }

    public String f0(int i) {
        return "Duck0" + id + ":f0(int=" + i +")";
    }

    private String fprivate() {
        return "this is a private func!";
    }

    public String f0(String i) {
        return "Duck0" + id + ":f0(String=" + i +")";
    }

    public String f0(int a, String s) {
        return "Duck0" + id + ":f0(int=" + a + " String=" + s + ")";
    }


    public static String staticf0() {
        return "Duck0" + "staticf0()";
    }

    public static String staticf0(int i, String s) {
        return "Duck0" + "staticf0(int, String)";
    }
}

public class ReflectTest {
    public static void main(String[] args) {
        try {
            Class<?> class0 = Class.forName("AlgorithmTraining.learning.java_learn.Duck0");
            System.out.println("---below is Method of class---");
            Method[] ms = class0.getMethods(); // 获得了一系列类似于python中函数对象的Method对象
            for (Method m: ms) {
                System.out.println(m);
            }
            System.out.println("---below is Field of class---");
            Field[] fs = class0.getFields();
            for (Field f: fs) {
                System.out.println(f);
            }
        } catch (ClassNotFoundException e) {
            System.out.println(args[0] + " not Found!");
        }
        System.out.print("\n\n\n");
        //以下是获取一个方法 并对其进行调用的过程
        Class<?> cc = Duck0.class;

        try {
            //要获取一个具体的方法对象需要提供三部分的信息
            // 1.是对应的Class对象 表明获取的方法来自于哪一个类
            // 2.对应的方法名称
            // 3.参数类型的列表 一个Class对象数组 Class对象可以被堂做类型信息进行传递 类似于Python的type 如下所示
            Method m0 = cc.getMethod("f0", new Class<?>[0]);
            Method mi_s = cc.getMethod("f0", int.class, String.class);
            Method ms = cc.getMethod("f0", String.class);
            Method mi = cc.getMethod("f0", int.class);

            //使用method对象调用一个方法 需要有两部分的内容 一个method方法本身已经包含了 类型 具体的方法以及参数列表信息
            //对于非静态方法而言 方法的调用一般会与内部的状态有关 所以应该告诉invoke函数 调用的到低是哪个对象的方法
            System.out.println(m0.invoke(new Duck0(1), null));
            System.out.println(mi_s.invoke(new Duck0(2), 90, "HAHA"));
            System.out.println(mi.invoke(new Duck0(3), 99));
            System.out.println(ms.invoke(new Duck0(4), "DPA"));

            //以下对于静态方法的调用
            //静态的方法 不需要提供给invoke具体的对象引用 只提供给他一个null即可
            Method staticM0 = cc.getMethod("staticf0");
            Method staticM1 = cc.getMethod("staticf0", int.class, String.class);
            System.out.println(staticM0.invoke(null));
            System.out.println(staticM1.invoke(null, 78, "ASSP"));

            //使用getDeclaredMethod 可以获取所有权限的方法 包括private 使用setAccessible true  后 可以调用private方法
            //而getMethod只能获取public权限的
            Method mPrivate = cc.getDeclaredMethod("fprivate");
            //Method mPrivate = cc.getMethod("fprivate");
            mPrivate.setAccessible(true);
            System.out.println(mPrivate.invoke(new Duck0(77)));
        } catch (NoSuchMethodException e) {
            System.out.println("no such ,ethod");
        } catch (IllegalAccessException e) {
            System.out.println("illegalAccessException");
        } catch (InvocationTargetException e) {
            System.out.println("xx");
        }
    }
}
