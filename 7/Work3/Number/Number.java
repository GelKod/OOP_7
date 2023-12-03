package Work3.Number;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Work3.Car.Car;
import Work3.ClientHandler.ClientHandler;
import Work3.LockModelThread.LockModelThread;
import Work3.LockPriceThread.LockPriceThread;
import Work3.MarkThread.MarkThread;
import Work3.ModelThread.ModelThread;
import Work3.Moped.Moped;
import Work3.Motoricle.Motoricle;
import Work3.PriceThread.PriceThread;
import Work3.Qwadracikle.Qwadracikle;
import Work3.ReaderFileThread.ReaderFileThread;
import Work3.Scuter.Scuter;
import Work3.SynchronizerModelThread.SynchronizerModelThread;
import Work3.SynchronizerPriceThread.SynchronizerPriceThread;
import Work3.TransportSynchronizer.TransportSynchronizer;
import Work3.VehicleUtils.VehicleUtils;
import Work3.Vehicle.Vehicle;

public class Number {
    public static void main(String[] args) throws Exception{
        
        Vehicle c = new Car("Pezho", 5);
        Vehicle m = new Motoricle("BMW", 500);
        Vehicle sc = new Scuter("Yamaha", 3);
        Vehicle mop = new Moped("Tesla", 3);

        ServerSocket serverSocket = new ServerSocket(1234);

        while (true) {
            // Ожидание входящего соединения
            Socket socket = serverSocket.accept();

            // Создание нового потока для обработки запросов клиента
            Thread thread = new Thread(new ClientHandler(socket));
            thread.start();
        }

        //6 laba
        
        //1

        // PriceThread priceThread = new PriceThread(m);
        // ModelThread modelThread = new ModelThread(m);
        // priceThread.setPriority(Thread.MAX_PRIORITY);
        // modelThread.setPriority(Thread.MIN_PRIORITY);

        // modelThread.start();
        // priceThread.start();

        //2
       
        // TransportSynchronizer synchronizer = new TransportSynchronizer(m);
        // Thread t = new Thread(new SynchronizerModelThread(synchronizer, m.getModelCount()));
        // Thread t1 = new Thread(new SynchronizerPriceThread(synchronizer, m.getModelCount()));
        // t.start();
        // t1.start();

        //3

        // Lock chekLock = new ReentrantLock();
        // Thread t2 = new Thread(new LockModelThread(c, chekLock));
        // Thread t3 = new Thread(new LockPriceThread(c, chekLock));
        // t2.start();
        // t3.start();

        //4

        // ExecutorService executorService = Executors.newFixedThreadPool(2);
        // executorService.submit(new MarkThread(c));
        // executorService.submit(new MarkThread(sc));
        // executorService.submit(new MarkThread(m));
        // executorService.submit(new MarkThread(mop));
        // executorService.shutdown();

        //5

        // ArrayBlockingQueue<Vehicle> bq = new ArrayBlockingQueue<Vehicle>(2);
        // String[] brandNamesArr = {"BMW","Toyota","Mers","Fiat","Ferrari"};
        // for(int i =0; i<brandNamesArr.length; i++){
        //     ReaderFileThread frth = new ReaderFileThread(brandNamesArr[i]+".txt", bq);
        //     Thread s = new Thread(frth);
        //     s.start(); 
        // }
        // for(int i =0; i<brandNamesArr.length; i++){
        //     System.out.println(bq.take().getMake());
        // }
    }
}