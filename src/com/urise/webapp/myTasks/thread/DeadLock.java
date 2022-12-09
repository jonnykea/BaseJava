package com.urise.webapp.myTasks.thread;

public class DeadLock {
    public static void main(String[] args) throws InterruptedException {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        resourceA.resourceB = resourceB;
        resourceB.resourceA = resourceA;

        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

        thread1.resourceA = resourceA;
        thread2.resourceB = resourceB;

        thread1.start();
        Thread.sleep(100);
        thread2.start();
    }
}

class Thread1 extends Thread {
    ResourceA resourceA;

    @Override
    public void run(){
        System.out.println(resourceA.getI());
    }
}

class Thread2 extends Thread {
    ResourceB resourceB;

    @Override
    public void run(){
        System.out.println(resourceB.getI());
    }
}

class ResourceA {
    ResourceB resourceB;

    public synchronized int getI() {
        return resourceB.returnI();
    }

    public synchronized int returnI() {
        return 1;
    }
}

class ResourceB {
    ResourceA resourceA;

    public synchronized int getI() {
        return resourceA.returnI();
    }

    public synchronized int returnI() {
        return 2;
    }
}