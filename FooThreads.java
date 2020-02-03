package com.example.java;

import java.util.concurrent.Semaphore;

/**
 * Created by JOE on
 **/

public class FooThreads extends Thread{
    private Foo foo;
    int method;
    public FooThreads(Foo foo, int method){
        this.foo = foo;
        this.method = method;
    }

    public void run() {
        if (method == 1) {
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    foo.first();
                }
            });
            t1.start();
        } else if (method == 2) {
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                foo.second();
            }
        });
            t2.start();
        } else if (method == 3) {
            Thread t3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    foo.third();
                }
            });
            t3.start();
        }
    }

    public static void main(String args[]) throws InterruptedException {
        Foo foo = new Foo();
        FooThreads thread1 = new FooThreads(foo, 1);
        FooThreads thread2 = new FooThreads(foo, 3);
        FooThreads thread3 = new FooThreads(foo, 2);

        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        thread3.start();
        thread3.join();
    }
}

class Foo {
    public synchronized void first(){
        System.out.print("first");
    }
    public synchronized void second(){
        System.out.print("second");
    }
    public synchronized void third(){
        System.out.print("third");
    }
}

/*

public class Foo{
    private Semaphore sem1;
    private Semaphore sem2;
    private int pasuse = 1000;

    public Foo(){
        try {
            sem1 = new Semaphore(1);
            sem2 = new Semaphore(1);
            sem1.acquire();
            sem2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void first(){
        try {
            System.out.println("first");
            Thread.sleep(1000);
            sem1.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void second(){
        try {
            sem1.acquire();
            sem1.release();
            System.out.println("second");
            Thread.sleep(pasuse);
            sem2.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void third(){
        try {
            sem2.acquire();
            sem2.release();
            System.out.println("third");
            Thread.sleep(pasuse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Foo foo = new Foo();
        FooThread thread1 = new FooThread(foo, 1);
        FooThread thread2 = new FooThread(foo, 2);
        FooThread thread3 = new FooThread(foo, 3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class FooThread extends Thread {
    private Foo foo;
    private int method;

    public FooThread(Foo foo, int method) {
        this.method = method;
        this.foo = foo;
    }

    public void run(){
        if (method == 1) {
            foo.first();
        } else if (method == 2) {
            foo.second();
        } else if (method == 3) {
            foo.third();
        }
    }
}
*/


