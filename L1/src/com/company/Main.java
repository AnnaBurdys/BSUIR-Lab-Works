package com.company;

class CarThread implements Runnable {
    static int cargo;
    int car;
    int loadTime;
    int delTime;

    int result = 0;

    static long TIME = System.currentTimeMillis();

    public CarThread(int cargo, int car, int loadTime, int delTime) {
        CarThread.cargo = cargo;
        this.car = car;
        this.loadTime = loadTime;
        this.delTime = delTime;
    }

    public CarThread() {
    }

    @Override
    public void run() {
        while (cargo > 0) {
            try {
                cargo -= car;
                result++;
                Thread.sleep((long) loadTime * car);
                Thread.sleep(delTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " (with capacity " + car + ") has worked for " + result + " time(s)" + "\n" );
    }

    public double getTime() {
        return (double) ((System.currentTimeMillis() - TIME));
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Car obj = new Car();

        Thread car1 = new Thread(new CarThread(obj.cargo, obj.carWeight1, obj.loadTime, obj.delTime1));
        Thread car2 = new Thread(new CarThread(obj.cargo, obj.carWeight2, obj.loadTime, obj.delTime2));
        Thread car3 = new Thread(new CarThread(obj.cargo, obj.carWeight3, obj.loadTime, obj.delTime3));

        car1.start();
        car2.start();
        car3.start();

        car1.setName("Car1");
        car2.setName("Car2");
        car3.setName("Car3");

        car1.join();
        car2.join();
        car3.join();

        System.out.println("Total time: " + ((new CarThread().getTime()) / 60000 + " minutes"));
    }
}