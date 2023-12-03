package Work3.LockPriceThread;

import java.util.concurrent.locks.Lock;

import Work3.Vehicle.Vehicle;

public class LockPriceThread implements Runnable{
    private Vehicle v;
    private Lock lock;
    public LockPriceThread(Vehicle v, Lock lock){
        this.v = v;
        this.lock = lock;
    }
    public void run(){
        try {
            lock.lock();
            double[] prices = v.getModelPrices();
            for(int i=0;i<v.getModelCount();i++){
                System.out.println(prices[i]);
            }
        } finally{lock.unlock();}
    }
}
