package Work3.SynchronizerModelThread;

import Work3.TransportSynchronizer.TransportSynchronizer;

public class SynchronizerModelThread implements Runnable{
    private TransportSynchronizer synchronizer;
    private int size;

    public SynchronizerModelThread(TransportSynchronizer synchronizer, int size) {
        this.synchronizer = synchronizer;
        this.size = size;
    }

    public void run() {
        try {
            for (int i=0;i<size;i++) {
            synchronizer.printModel();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
