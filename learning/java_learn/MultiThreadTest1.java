package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by BUPT_SS4G on 2017/8/18.
 */
class CommonCnt implements Runnable {
    private int cnt = 0;//一个Runnable对象本身的域 相当于这个线程自己的内部变量 如果设置为Private 那么其他的线程将无法直接访问
    //这样就可以利用get 或者set 的sync方法来保证这些域在什么时候应该被访问
    synchronized int getCnt() {
        return cnt;
    }

    synchronized void inc2() { //如果不加sync 关键字那么 其他线程将会有机会在这个函数执行的过程中打断他
        //这个函数本身是要求 执行过程中不应该被打断的 否则外部将会有可能回去到奇数 相当于一个临界段代码
        //sync 相当于给这个函数的代码加了一个锁 只有运行完这段代码(函数) 才会释放这个锁
        //同一个Runnable对象中 只有一把锁 这个锁叫做对象锁 每当sync方法被调用的时候，这个锁就被获取 所有该对象的其他sync方法
        //只有等当前的sync锁释放了以后才能获取该锁 但如果是一个非sync方法 的调用则无所顾忌

        try {
            cnt++;
            TimeUnit.MILLISECONDS.sleep(1);
            Thread.yield(); //declare
            cnt++;
            Thread.yield(); //declare
        }
        catch (Exception e) {
            System.out.println("ERROR!");
        }
    }

    public void run() {
        while (true) {
            inc2();
        }
    }
}

class CommonCnt2 implements Runnable {
    private int cnt = 0;//一个Runnable对象本身的域 相当于这个线程自己的内部变量 如果设置为Private 那么其他的线程将无法直接访问
    //这样就可以利用get 或者set 的sync方法来保证这些域在什么时候应该被访问

    private Lock lock = new ReentrantLock(); //这个东西可以当做一般操作系统中多进程编程时的信号量来对待

    int getCnt() {
        lock.lock();
        try {
            return cnt;
        }
        finally {
            lock.unlock();
        }
    }

    void inc2() {
        try {
            lock.lock(); //尝试获取锁 使用显示的lock 可以标定比函数更小的范围 提高灵活性sync关键字相当于给整个的函数添加了锁
            //而且lock的锁可以不是对象锁
            cnt++;
            TimeUnit.MILLISECONDS.sleep(1);
            Thread.yield(); //declare
            cnt++;
            System.out.println("gen " + cnt);
            Thread.yield(); //declare
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        catch (Exception e) {
            System.out.println("ERROR!");
        }
        finally {
            //释放锁
            lock.unlock(); //写在finally中是为了防止在发生异常的时候仍然能够释放这个锁以保证其他的函数仍然能够
        }
    }

    public void run() {
        while(true) {
            inc2();
        }
    }
}

class EvenChecker implements Runnable {
    CommonCnt ccnt;
    EvenChecker(CommonCnt ccnt) { //希望在一个线程中获取另一个线程的方法就是 在创建这个线程的时候 通过他的构造器 传入其他的线程
        this.ccnt = ccnt;
    }

    public void run() {
        int x;
        while (true) {
            if ((x = ccnt.getCnt()) % 2 != 0)
                break;
        }
        System.out.println("I got Odd!:" + x);
    }
}

class EvenChecker2 implements Runnable {
    CommonCnt2 ccnt;
    EvenChecker2(CommonCnt2 ccnt) { //希望在一个线程中获取另一个线程的方法就是 在创建这个线程的时候 通过他的构造器 传入其他的线程
        this.ccnt = ccnt;
    }

    public void run() {
        int x;
        while (true) {
            if ((x = ccnt.getCnt()) % 2 != 0)
                break;
        }
        System.out.println("I got Odd!:" + x);
    }
}

public class MultiThreadTest1 {
    public static void main(String[] args) {
        //CommonCnt ccnt = new CommonCnt();
        CommonCnt2 ccnt2 = new CommonCnt2();
        //EvenChecker evenCheck = new EvenChecker(ccnt);
        EvenChecker2 evenCheck2 = new EvenChecker2(ccnt2);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(ccnt2);
        exec.submit(evenCheck2);
        exec.shutdown();
    }
}
