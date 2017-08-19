package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.*;
import java.util.concurrent.Executors;

/**
 * Created by BUPT_SS4G on 2017/8/19.

 */

class CommonCnt4 implements Runnable {
    private int cnt = 0;//一个Runnable对象本身的域 相当于这个线程自己的内部变量 如果设置为Private 那么其他的线程将无法直接访问
    private boolean isCanceled = false;

    void cancle() {
        isCanceled = true;
    }

    synchronized int getCnt() {
        return cnt;
    }

    void inc2() { //如果不加sync 关键字那么 其他线程将会有机会在这个函数执行的过程中打断他
        //这个函数本身是要求 执行过程中不应该被打断的 否则外部将会有可能回去到奇数 相当于一个临界段代码
        //sync 相当于给这个函数的代码加了一个锁 只有运行完这段代码(函数) 才会释放这个锁
        //同一个Runnable对象中 只有一把锁 这个锁叫做对象锁 每当sync方法被调用的时候，这个锁就被获取 所有该对象的其他sync方法
        //只有等当前的sync锁释放了以后才能获取该锁 但如果是一个非sync方法 的调用则无所顾忌

        try {
            synchronized (this) { //除了将方法置为sync 外 还可以使用sync语句块来 实现更细粒度的同步
                // 括号内的参数为 要获取的对象锁的对象
                cnt++;
                Thread.yield();
                cnt++;
            Thread.yield();
            }
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

class EvenChecker4 implements Runnable {
    CommonCnt4 ccnt;
    EvenChecker4(CommonCnt4 ccnt) { //希望在一个线程中获取另一个线程的方法就是 在创建这个线程的时候 通过他的构造器 传入其他的线程
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

public class ConcurrentTest0 {
    public static void main(String[] args) {
        CommonCnt4 ccnt = new CommonCnt4();
        EvenChecker4 evenCheck = new EvenChecker4(ccnt);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(ccnt);
        exec.submit(evenCheck);
        exec.shutdown();
    }
}
