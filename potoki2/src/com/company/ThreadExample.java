package com.company;

public class ThreadExample {
    public int value;
    private Boolean sincObj = true;
    private Integer dObj = 12;
    public void run1 () {
        // у каждого объекта java  есть монитор состояния
        // монитор может принимать два состояния: заблокирован и не заблокирован (locked / unlocked)

        for (int i = 0; i <1000; i++) {
            synchronized (sincObj){
            this.value++;
        }
        }
    }
    public void run2 () {

            for (int i = 0; i < 1000; i++) {
                synchronized (sincObj) {
                this.value--;
            }
        }
    }


    //run3=run4
    public synchronized void run3() {}

    public void run4() {
        synchronized (this){
            //code
        }
    }
    // пока заблокирован this потоки не имеют доступа (становятся в очередь) к другим синхронизированным
    //методам объекта (к несинхронизированным могут

    //будут заблакированы все статические методы(принадлежащие данному классу) класса с пометкой synchronized
    public static synchronized void test2 () {

    }
    public void daemon() {
        // дальнейшее выполнение кода произойдет только после того, как
        // у объекта dObj, будет вызван метод notify из другого потока
        while (true){
        //вызов методов wait notifyAll должен происходить в синхронизированном контексте
        synchronized (this.dObj) {
            try {
                this.dObj.wait();
                System.out.println("Deamon");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }
    }
    public void angel(){
        while (true){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.dObj){
                this.dObj.notify();
            }
        }
    }
    }
