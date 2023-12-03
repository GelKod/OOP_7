package Work3.LockModelThread;

import java.util.concurrent.locks.Lock;

import Work3.Vehicle.Vehicle;

public class LockModelThread implements Runnable{
    private Vehicle v;
    private Lock lock;
    public LockModelThread(Vehicle v, Lock lock){
        this.v = v;
        this.lock = lock;
    }
    public void run(){
        try {
            lock.lock();
            String[] names = v.getModelNames();
            for(int i=0;i<v.getModelCount();i++){
                System.out.println(names[i]);
            }
        } finally{lock.unlock();}
    }
}
