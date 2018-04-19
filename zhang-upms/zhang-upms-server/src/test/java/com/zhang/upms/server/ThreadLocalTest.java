package com.zhang.upms.server;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/17
 * @history
 */

class Accessor implements Runnable {
    private final int id;

    public Accessor(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            ThreadLocalTest.increment();
            System.out.println(this);
            Thread.yield();
        }
    }
    @Override
    public String toString() {
        return "#"+id+":"+ThreadLocalTest.get();
    }
}
public class ThreadLocalTest {
    private static ThreadLocal<Integer> value =
            new ThreadLocal<Integer>(){
                private Random random = new Random(8);
                protected synchronized Integer initialValue() {
                    return random.nextInt(10000);
                }
    };
    public static void increment() {
        value.set(value.get() + 1);
    }
    public static int get(){
        return value.get();
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
    }



}
