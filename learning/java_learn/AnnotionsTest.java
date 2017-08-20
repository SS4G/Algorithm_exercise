package AlgorithmTraining.learning.java_learn;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import java.lang.reflect.*;

/**
 * Created by BUPT_SS4G on 2017/8/14.
 *
 */
@Target(ElementType.METHOD)// 指明注解的对象
@Retention(RetentionPolicy.RUNTIME)
@interface Test { //如何去定义一个注解
    public int id() default -1; //给注解定义一个   默认的值
    public String name() default "ss";
}

@Target(ElementType.METHOD)// 指明注解的对象
@Retention(RetentionPolicy.RUNTIME)
@interface Test2 { //如何去定义一个注解
    public int id() default -1; //给注解定义一个   默认的值
    public String name() default "ss";
}

class TmpClass {
    @Test(id = 1, name = "SSS")
    public void func() {
        System.out.println("just for annotions");
    }

    @Test2(id = 7, name = "SSS")
    public void func2() {
        System.out.println("just for annotions");
    }
}

public class AnnotionsTest {
    public static void main(String[] args) {
        Class<TmpClass> cc = TmpClass.class;
        for (Method m : cc.getMethods()) { //自己写一个或者用现成的注解处理器来提取注解才能真正的发挥注解的作用
            System.out.println(m.getAnnotation(Test.class)); //参数是表明要提取的注解 意思是从method中提取名为 Test的注解
        }
        System.out.println("CC");

        for (Method m : cc.getMethods()) { //自己写一个或者用现成的注解处理器来提取注解才能真正的发挥注解的作用
            System.out.println(m.getAnnotation(Test2.class)); //参数是表明要提取的注解 意思是从method中提取名为 Test的注解
        }
        //如果提取到就返回一个注解的对象否则返回 null
    }
}
