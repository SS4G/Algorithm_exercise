package AlgorithmTraining.learning.java_learn;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 */
class Dog{
    int id;
    static {
        System.out.println("class Dog loaded");
    }

    Dog() {
        id = -1;
    }

    Dog(int id) {
        this.id = id;
    }
    public String toString() {
        return "This is Dog";
    }
}

class Dog0 extends Dog {
    static {
        System.out.println("class Dog0 loaded");
    }

    Dog0(){
        super();
    }

    Dog0(int id){
        super(id);
    }

    public String toString() {
        return "This is Dog0";
    }
}

class Dog1 extends Dog {
    static {
        System.out.println("class Dog1 loaded");
    }

    Dog1 () {
        super();
    }

    Dog1 (int id) {
        super(id);
    }

    public String toString() {
        return "This is Dog1";
    }
}

public class RttiTest {
    public static void main(String[] args) {
        //Dog d = typeDog.getClass();
        //所有类的各种信息都被保存在一个对应的Class 类对象中
        try {
            //使用字符获取对应类的Class对象的 引用, 这种方式在运行时检查 性能略差
            Class<?> typeDog0 = Class.forName("AlgorithmTraining.learning.java_learn.Dog0");//这里必须加上包名称才能够找得到
        }
        catch (ClassNotFoundException e) {
            System.out.println("class Dog0 not Found");
        }

        Object obj = new Dog();
        Class<?> objType = obj.getClass();
        System.out.println(objType.getName()); //输出仍然为Dog 说明类型信息始终保持在具体的对象中
        //不管这个对象如何向上转型 他的具体类型内容都是不变的，可以理解为是堆中的对象始终是不变的。
        //向上转型只是缩小了这个对象的可操作范围

        Class<Dog1> typeDog1 = Dog1.class;  //使用字面量在程序中直接指定.
        System.out.println(typeDog1.getName());
        System.out.println(typeDog1.getSimpleName());
        Class<?> c = typeDog1.getSuperclass();
        System.out.println(typeDog1.getSimpleName() + "super class name is " + c.getSimpleName());

        try {
            Dog1 d = typeDog1.newInstance(); // 可以使用类型对象来调用对应类的无参数构造器
            System.out.println("d:id=" + d.id);
        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (IllegalAccessException e) {
            System.out.print(12);
        }

        Dog1 d1 = new Dog1(111);
        Class<?> c0 = d1.getClass(); // 对于一个具体的对象来回去他对应的的Class 对象
        System.out.println("the new Object class is" + c0.getSimpleName());

        // 下面是关于instance of 的
        Dog dog_ = new Dog();
        Dog0 dog_0 = new Dog0();
        Dog1 dog_1 = new Dog1();
        Dog dog_11 = new Dog1();


        /**
         * instanceof运算符用法
         * 运算符是双目运算符,左面的操作元是一个对象,右面是一个类.当
         * 左面的对象是右面的类创建的对象时,该运算符运算的结果是true,否则是false
         *
         * 说明:(1)一个类的实例包括本身的实例,以及所有直接或间接子类的实例
         * (2)instanceof左边操作元显式声明的类型与右边操作元必须是同种类或右边是左边父类的继承关系,
         * (3)不同的继承关系下,编译出错
         */
        System.out.println(dog_0 instanceof Dog); // true  instanceof 可以表示这个对象是其基类的某个实例
        System.out.println(dog_0 instanceof Dog0); // true
        System.out.println(dog_11 instanceof Dog0); // false dog11 虽然是一个 Dog引用 但是其实例是 Dog1
        // 而不是Dog0 这就是RTTI的作用 真正的信息会一直被记录在类型对象中
        //System.out.println(dog_0 instanceof Dog1); //
        System.out.println("------");
        /*所以 instanceof是编译期 instanceof可以表示继承关系 比Class.isInstance要灵活*/
        // Class<?> ClassObj.isInstance(obj) 比较是否是同一种类别 类似于Python的type
        System.out.println(typeDog1.isInstance(dog_)); // false 就算比较对象是该对象的超类也不可以
        System.out.println(typeDog1.isInstance(dog_0)); // false
        System.out.println(typeDog1.isInstance(dog_1)); // true
        //System.out.println(dog_0 instanceof Dog1);

    }
}
