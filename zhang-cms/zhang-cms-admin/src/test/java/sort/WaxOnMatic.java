package sort;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Edison
 * @ClassName:
 * @Desc:
 * @date 2017/8/25
 * @history
 */
class Car{
    private boolean waxOn = false;
    public synchronized void waxed(){
        waxOn = true;
        notifyAll();
    }
    public synchronized void buffed(){
        waxOn = false;
        notifyAll();
    }
    public synchronized void waitForWaxing() throws InterruptedException{
        while(waxOn == false){
            wait();
        }
    }
    public synchronized void waitForBuffing() throws InterruptedException{
        while(waxOn == true){
            wait();
        }
    }
}
    class WaxOn implements Runnable{
        private Car car;
        public WaxOn(Car car) {
            this.car = car;
        }
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()){
                    System.out.println("Wax On!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println("car.waxed()");
                    car.waxed();
                    System.out.println("car.waitForBuffing()");
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt");
            }
            System.out.println("ending wax on task");
        }
    }

    class WaxOff implements Runnable{
        private Car car;
        public WaxOff(Car car) {
            this.car = car;
        }
        @Override
        public void run() {
            try {
                while(!Thread.interrupted()){
                    System.out.println("car.waitForWaxing()");
                    car.waitForWaxing();
                    System.out.println("Wax off!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println("car.buffed()");
                    car.buffed();
                }
            } catch (InterruptedException e) {
                System.out.println("Exiting via interrupt-waxoff");
            }
            System.out.println("ending wax off task");
        }
    }



public class WaxOnMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }

}
