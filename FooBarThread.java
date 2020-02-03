package com.example.java;

import java.util.Iterator;

/**
 * Created by JOE on
 **/
/*public class FooBarThread extends Thread {
    private FooBar prev;
    private FooBar self;

    public FooBarThread(FooBar prev, FooBar self) {
        this.prev = prev;
        this.self = self;
    }

    Thread t1 = new Thread(new Runnable() {
        @Override
        public void run() {
            synchronized (prev) {
                synchronized (self) {
                    self.foo();
                    self.notify();
                }
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                prev.bar();
            }
        }
    });

    public static void main(String args[]) throws InterruptedException {
        FooBar fooBar = new FooBar(2);
        FooBarThread thread1 = new FooBarThread(fooBar, fooBar);
        FooBarThread thread2 = new FooBarThread(fooBar, fooBar);

        thread1.t1.start();
        thread2.t1.start();
    }
}*/

/*
public class FooBarThread extends Thread {
    private int n;

    public FooBarThread(int n) {
        this.n = n;
    }

    public void run() {
        for (int i = 1; i <= n; i++) {
            if (i % 2 != 0) {
                Thread t1 = new Thread(new PrintFoo());
                try {
                    t1.start();
                    t1.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (i % 2 == 0) {
                Thread t2 = new Thread(new PrintBar());
                try {
                    t2.start();
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        FooBarThread t1 = new FooBarThread(10);
        t1.start();
    }

}

class PrintFoo implements Runnable {

    @Override
    public void run() {
        System.out.print("foo");
    }
}

class PrintBar implements Runnable {

    @Override
    public void run() {
        System.out.print("bar");
    }
}*/

public class FooBarThread extends Thread {
    private int synchronizedNumber;

    public synchronized void foo(){
        try {
            while (synchronizedNumber != 0) {
                wait();
            }
            System.out.print("foo");
            synchronizedNumber = 1;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void bar(){
        try {
            while (synchronizedNumber != 1) {
                wait();
            }
            System.out.print("bar");
            synchronizedNumber = 0;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]){
        FooBarThread fooBar = new FooBarThread();
        FooBarTest thread1 = new FooBarTest(3, fooBar, "foo");
        FooBarTest thread2 = new FooBarTest(3, fooBar, "bar");

        thread1.start();
        thread2.start();
    }

}

class FooBarTest extends Thread {
    private FooBarThread fooBar;
    private Integer n;
    private String option;

    public FooBarTest(Integer n, FooBarThread fooBar, String option){
        this.fooBar = fooBar;
        this.n = n;
        this.option = option;
    }

    public void printFoo(){
        for (int i = 0; i < n; i++) {
            fooBar.foo();
        }
    }

    public void printBar(){
        for (int i = 0; i < n; i++) {
            fooBar.bar();
        }
    }

    public void run(){
        if (option == "foo") {
            printFoo();
        } else {
            printBar();
        }
    }

}