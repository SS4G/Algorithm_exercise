package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by BUPT_SS4G on 2017/8/19.

 */

class CommonCnt3 implements Runnable {
    private int cnt = 0;//一个Runnable对象本身的域 相当于这个线程自己的内部变量 如果设置为Private 那么其他的线程将无法直接访问
    boolean isCanceled = false;

    void cancle() {
        isCanceled = true;
    }

    //这样就可以利用get 或者set 的sync方法来保证这些域在什么时候应该被访问
    int getCnt() {
        return cnt;
    }

    void inc2() { //如果不加sync 关键字那么 其他线程将会有机会在这个函数执行的过程中打断他
        //这个函数本身是要求 执行过程中不应该被打断的 否则外部将会有可能回去到奇数 相当于一个临界段代码
        //sync 相当于给这个函数的代码加了一个锁 只有运行完这段代码(函数) 才会释放这个锁
        //同一个Runnable对象中 只有一把锁 这个锁叫做对象锁 每当sync方法被调用的时候，这个锁就被获取 所有该对象的其他sync方法
        //只有等当前的sync锁释放了以后才能获取该锁 但如果是一个非sync方法 的调用则无所顾忌

        try {
            cnt += 2; //对于该语句而言 在java中并不是原子性操作 原子性操作是指 代码翻译为一条指令 因为指令的执行中间是不会
            // 如果不加sync关键字的话 是线程不安全的 也就是说在这条语句执行期间他可能被打断
            // 被打断的，只有在指令和下一条指令中间才有可能被中断打断 也就是说EvenChecker还是有可能得到奇数
            Thread.yield();
        }
        catch (Exception e) {
            System.out.println("ERROR!");
        }
    }

    public void run() {
        while (!isCanceled) {
            inc2();
        }
    }
}

class EvenChecker3 implements Runnable {
    CommonCnt3 ccnt;
    EvenChecker3(CommonCnt3 ccnt) { //希望在一个线程中获取另一个线程的方法就是 在创建这个线程的时候 通过他的构造器 传入其他的线程
        this.ccnt = ccnt;
    }

    public void run() {
        int x;
        while (true) {
            if ((x = ccnt.getCnt()) % 2 != 0) {
                ccnt.cancle();
                break;
            }
        }
        System.out.println("I got Odd!:" + x);
    }
}


//验证语句的原子性操作
public class MultiThreadTest2 {
    public static void main(String[] args) {
        CommonCnt3 ccnt3 = new CommonCnt3();
        //EvenChecker evenCheck = new EvenChecker(ccnt);
        EvenChecker3 evenCheck3 = new EvenChecker3(ccnt3);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(ccnt3);
        exec.submit(evenCheck3);
        exec.shutdown();
    }
}
