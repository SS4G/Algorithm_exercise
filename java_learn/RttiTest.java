package AlgorithmTraining.java_learn;

/**
 * Created by BUPT_SS4G on 2017/6/5.
 */
class Dog{
    int age;
    String name;
    static {
        System.out.println("class Dog loaded");
    }
    Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }
    void fun() {
        System.out.println("this is my dog:"+age+" at "+age);
    }
}

class AlaskDog extends Dog {
    static {
        System.out.println("class AlaskDog loaded");
    }
    AlaskDog(int age, String name) {
        super(age, name);
    }

    @Override
    void fun() {
        System.out.println("this is my Alaska dog:"+age+" at "+age);
    }

    void alaskaBark() {
        System.out.println("Wang Wang");
    }
}

public class RttiTest {
    public static void main(String[] args) {
        Dog d = new Dog(10, "pipi");
        AlaskDog ad = new AlaskDog(10, "pipi");
        if (ad instanceof Dog) {
            System.out.println("yes it is ");
        }
    }
}
