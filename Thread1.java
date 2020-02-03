package com.example.java;

import java.util.Random;

/**
 * Created by JOE on
 **/
public class Thread1 extends Thread {

    public static void main(String args[]) {
        Thread1 t = new Thread1();
        t.start();
    }

    @Override
    public void run(){
        Random rand = new Random();
        for (int i = 0; i < 10; i++){
            int randInt = rand.nextInt(50);
            System.out.println("Random Number: " + randInt);
            if (randInt % 2 == 0) {
                Square s = new Square(randInt);
                s.start();
            } else {
                Cube c = new Cube(randInt);
                c.start();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

}

class Square extends Thread {
    int x;
    public Square(int n) {
        x = n;
    }

    @Override
    public void run() {
        int square = x * x;
        System.out.println("Square: " + square);
    }
}

class Cube extends Thread implements Runnable {
    int x;
    public Cube(int n) {
        x = n;
    }

    @Override
    public void run() {
        int cube = x * x * x;
        System.out.println("Cube: " + cube);
    }
}