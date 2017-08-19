package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.*;
import java.util.*;
/**
 * Created by BUPT_SS4G on 2017/8/17.
 *
 */

class ThreadMission0 implements Runnable {
    static private int threadCnt = 0;
    public final int id = threadCnt++;
    private int times = 0;
    public void run() { //将要执行的线程代码写在run方法中 这个方法应该其实只是一个普通的方法只不过是
        // 被Thread类使用Runnable来保证点用的1类有run这个方法
        for (int i = 0; i < 2; i++) {
            System.out.println("this is thread #" + id + " times:" + times++);
            Thread.yield(); //告知线程调度器可以进行切换 但是调度器是否采纳这个意见是不一定的
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            catch (InterruptedException e) {
                System.out.println("thread interrupted!");
            }
        }
    }
}

class ThreadMission1 implements Callable<String> {
    static private int threadCnt = 0;
    public final int id = threadCnt++;
    private int times = 0;
    private int perido = 0;
    ThreadMission1(int period) {
        this.perido = period;
    }

    public String call() { //将要执行的线程代码写在run方法中 这个方法应该其实只是一个普通的方法只不过是
        // 被Thread类使用Runnable来保证点用的1类有run这个方法
        for (int i = 0; i < this.perido; i++) {
            System.out.println("this is thread #" + id + " times:" + times++);
            Thread.yield(); //告知线程调度器可以进行切换 但是调度器是否采纳这个意见是不一定的
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            catch (InterruptedException e) {
                System.out.println("thread interrupted!");
            }
        }
        return "mission" + id + ":completed";
    }
}

class ThreadMission2 implements Runnable {
    Thread r0;
    ThreadMission2 (Thread t0) {
        r0 = t0;
    }

    public void run() {
        try {
            System.out.println("start waiting!");
            r0.join();
            System.out.println("mission2 waiting complete!");
        }
        catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}

class ThreadMission3 implements Runnable {
    public void run() {
        try {
             System.out.println("mission3 start");
             TimeUnit.MILLISECONDS.sleep(1000);
             System.out.println("mission3 complete!");
        }
        catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}

public class MultiThreadTest0 {
    private static void useThreadType0() { //使用Thread 对象
        //ThreadMission0 m0 = new ThreadMission0();
        //m0.run();//只是普通的调用 跟线程，没有半毛钱关系
        for (int i = 0; i < 5; i++) {
            Thread t0 = new Thread(new ThreadMission0()); //使用Thread类启动的方式 创建一个Thread对象
            // 该对象需要一个Runnable对象来作为要运行的任务
            t0.start(); //调用Thread类的start方法
        }
    }

    private static void useThreadType1() { //使用executors
        ExecutorService exec = Executors.newCachedThreadPool(); //executors 提供一种接收任务的机制
        //从名称上来看 是使用了线程池这个东西 穿件很多线程 然后由用户提交的任务来认领线程
        for (int i = 0; i < 3; i++) {
            exec.execute(new ThreadMission0()); // 将Runnable对象提交给 executors
        }
        exec.shutdown(); //shutdown之后 可以防止新的任务再被提交给executors 主程序会退出 但是 其他线程还会运行
        System.out.println("executors terminates!!");
        //output
        /**
         *
         this is thread #0 times:0
         executors terminates!!
         this is thread #2 times:0
         this is thread #1 times:0
         this is thread #2 times:1
         this is thread #1 times:1
         this is thread #0 times:1
         */
         //可以看到退出主程序后其他线程还在运行 他们并不会随着主程序的退出而终止

    }

    private static void useThreadType2 (int threadCnt) { //使用Callable对象
        ExecutorService exec = Executors.newCachedThreadPool(); //executors 提供一种接收任务的机制
        //从名称上来看 是使用了线程池这个东西 穿件很多线程 然后由用户提交的任务来认领线程
        List<Future<String>> fuList = new ArrayList<>();
        for (int i = 0; i < threadCnt; i++) {
            fuList.add(exec.submit(new ThreadMission1(5))); // 将Callable对象提交给 executors
            // 返回一个与新建线程对应的的Future对象 我们可以通过get() 或者isDone来检查线程是否完成
            //get()的返回值即是 call()方法的返回值 如果一个线程没有结束那么对他get()的调用将会被阻塞
            //但是可以调用 isDone()来获取当前线程是否完成的状态

        }
        exec.shutdown(); //shutdown之后 可以防止新的任务再被提交给executors 主程序会退出 但是 其他线程还会运行
        System.out.println("executors terminates!!");
        try {
            for (Future<String> f : fuList) {
                System.out.println(f.get());
            }
        }
        catch (Exception e) {
            System.out.println();
        }
    }

    private static void useThreadType3() {
        Thread t1 = new Thread(new ThreadMission3());
        Thread t0 = new Thread(new ThreadMission2(t1));
        t0.start(); //首先启动那个等待别人的线程
        t1.start();
    }

    public static void main(String[] args) {
        //useThreadType0();
        //useThreadType1();
        //useThreadType2(3);
        useThreadType3();
    }
}
