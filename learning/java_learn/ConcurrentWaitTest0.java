package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by BUPT_SS4G on 2017/8/19.
 */

class WaitThread implements Runnable {
    synchronized public void waitFor() {
        try {
            System.out.println("waiting...");
            wait(); //释放当前对象获取的所有锁 使得其他的线程可以调用这个线程的sync方法
            System.out.println("got !");
        }
        catch (InterruptedException e) {
            System.out.println("interrupt");
        }
        finally {
            System.out.println("finally");
        }
    }

    public void run() {
        int cnt = 10;
        while (cnt-- > 0) {
            waitFor();
        }
    }
}

class NoticeThread implements Runnable {
    private final Runnable x;
    NoticeThread(Runnable tobeNotice) {
        x = tobeNotice;
    }

    public void notice() {
        System.out.println("notice after 100 ms");
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
            synchronized (x) { //想要环形一个进程必须在 sync控制块中这么做 保证notify能够获取到对应线程的对象锁
                x.notifyAll();
            }
            System.out.println("noticed");
        }
        catch (InterruptedException e) {
            System.out.println("interrupt");
        }
    }

    public void run() {
        notice();
    }
}

public class ConcurrentWaitTest0 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        Runnable waitThread = new WaitThread();
        Runnable NoticeThread = new NoticeThread(waitThread);
        exec.execute(waitThread);
        exec.execute(NoticeThread);
        exec.shutdown();
    }
}
