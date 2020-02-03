package com.example.java;

import java.util.concurrent.Semaphore;

/**
 * Created by JOE on
 **/

public class Foo extends Thread{
    private int synchronizedNumber;

    public synchronized void first(){
        System.out.print("first");
        synchronizedNumber = 1;
        notifyAll();
    }

    public synchronized void second(){
        try {
            while (synchronizedNumber < 1) {
                wait();
            }
            synchronizedNumber = 2;
            notifyAll();
            System.out.print("second");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void third(){
        try {
            while (synchronizedNumber < 2) {
                wait();
            }
            System.out.print("third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Foo foo = new Foo();
        FooTest threadA = new FooTest(1, foo);
        FooTest threadB = new FooTest(3, foo);
        FooTest threadC = new FooTest(2, foo);

        threadA.start();
        threadB.start();
        threadC.start();
    }

}

class FooTest extends Thread {
    private int method;
    private Foo foo;

    public FooTest(int method,Foo foo){
        this.method = method;
        this.foo = foo;
    }

    @Override
    public void run() {
        if (method == 1) {
            foo.first();
        } else if (method == 2) {
            foo.second();
        } else if (method == 3){
            foo.third();
        }
    }
}





