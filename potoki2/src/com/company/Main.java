package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Синхронизация потока
        ThreadExample te = new ThreadExample();

        Thread t1 = new Thread(te :: run1);
        Thread t2 = new Thread(te :: run2);

        t1.start();
        t2.start();
        System.out.println(t1.isAlive());

        t1.join();
        t2.join();
        System.out.println(t1.isAlive());

        System.out.println(te.value);


        // объекты потока ангел и демон
        Thread deamon = new Thread(te::daemon);
        Thread angel = new Thread(te::angel);
        deamon.start();
        angel.start();
        Thread breakThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            deamon.interrupt();
            angel.interrupt();
        });
        breakThread.start();

        deamon.join();
        angel.join();
        // прерванный поток != закончить выполнение потока
        angel.interrupt();


    }
}
