package AlgorithmTraining.learning.java_learn;

import java.util.concurrent.*;

/**
 * Created by BUPT_SS4G on 2017/8/19.
 *
 */
class Sleeper implements Runnable {
    int cnt;
    public void run() {
        boolean flag = false;
        while (true) {
            try {
                System.out.println("awake");
                cnt++;
                System.out.println("sleep:" + cnt);
                TimeUnit.MILLISECONDS.sleep(1000);
                Thread.yield();
            }
            catch (InterruptedException e) {
                System.out.println("sleeper is interrupt");
                flag = true;
            }
            finally {
                System.out.println(Thread.interrupted() + ":" + cnt);
            }
        }
    }
}

class Sleeper2 implements Runnable {
    int cnt;
    public void run() {
        boolean flag = false;
        while (!Thread.interrupted()) { // 可以使用轮询中断标志位的方式来检测中断是否发生 一单检测到以后 这个标志位自动清零
            //和单片机处理中断的方式有些相似
            try {
                //System.out.println("awake");
                cnt++;
                System.out.println("sleep:" + cnt);
                Thread.yield();
            }
            finally {
                //clean upCode
            }
        }
        System.out.println("tremnate!");
    }
}



public class InterruptTest {
    private static void interrupt0() {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<?> f = exec.submit(new Sleeper());
        try {
            TimeUnit.MILLISECONDS.sleep(2100);
        }
        catch (InterruptedException e) {
            System.out.println("");
        }
        finally {
            ;//add clean code here
        }
        f.cancel(true); //使用submit返回的Future对象调用cancle(true)来产生interrupt
        // 这将会导致对应于该Future对象的线程产生InterruptException
        //但是cancle无法对因为sync关键字产生的阻塞以及 IO产生的阻塞 产生中断
        System.out.println("finS");
    }

    private static void interrupt1() {
        Thread t0 = new Thread(new Sleeper2());
        t0.start();
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        catch (InterruptedException e) {
            System.out.println("");
        }
        t0.interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(t0.isInterrupted());

    }

    public static void main(String[] args) {
        interrupt1();
        // interrupt0();
    }
}
