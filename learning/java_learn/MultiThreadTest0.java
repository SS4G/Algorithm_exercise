package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.*;

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

public class MultiThreadTest0 {
    private static void useThreadType0() {
        //ThreadMission0 m0 = new ThreadMission0();
        //m0.run();//只是普通的调用 跟线程，没有半毛钱关系
        for (int i = 0; i < 5; i++) {
            Thread t0 = new Thread(new ThreadMission0()); //使用Thread类启动的方式
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

    public static void main(String[] args) {
        //useThreadType0();
        useThreadType1();
    }
}
