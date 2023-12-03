package Work3.SynchronizerPriceThread;

import Work3.TransportSynchronizer.TransportSynchronizer;

public class SynchronizerPriceThread implements Runnable {
    private TransportSynchronizer synchronizer;
    int size;
    public SynchronizerPriceThread(TransportSynchronizer synchronizer, int size){
        this.synchronizer = synchronizer;
        this.size = size;
    }

    public void run() {
        try {
            for(int i=0;i<size;i++){
               synchronizer.printPrice(); 
            }
        }
        catch (InterruptedException e) {e.printStackTrace();}
    }
}
