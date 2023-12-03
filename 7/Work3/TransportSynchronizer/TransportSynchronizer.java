package Work3.TransportSynchronizer;

import Work3.Vehicle.Vehicle;

public class TransportSynchronizer {
    private Vehicle v;
    private volatile int current = 0;
    private Object lock = new Object();
    private boolean set = false;
   
    public TransportSynchronizer(Vehicle v) {
        this.v = v;
    }
   
    public void printPrice() throws InterruptedException {
        double val;
        synchronized(lock) {
            double [] p = v.getModelPrices();
            if (!canPrintPrice()) throw new InterruptedException();
            while (!set)
                lock.wait();
            val = p[current++];
            System.out.println("Print price: " + val);
            set = false;
            lock.notifyAll();
        }
    }
   
    public void printModel() throws InterruptedException {
        synchronized(lock) {
            String [] s = v.getModelNames();
            if (!canPrintModel()) throw new InterruptedException();
            while (set)
                lock.wait();
            System.out.println("Print model: " + s[current]);
            set = true;
            lock.notifyAll();
        }
    }
    
    public boolean canPrintPrice() {
        return current < v.getModelCount();
    }
    
    public boolean canPrintModel() {
        return (!set && current < v.getModelCount()) || (set && current < v.getModelCount() - 1);
    }
}
