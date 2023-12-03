package Work3.ReaderFileThread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;

import Work3.Car.Car;
import Work3.Vehicle.Vehicle;

public class ReaderFileThread implements Runnable{
    String str;
    ArrayBlockingQueue<Vehicle> abq;
    public ReaderFileThread(String str, ArrayBlockingQueue<Vehicle> abq){
        this.str = str;
        this.abq = abq;
    }

    public void run() {
        try
        {
            FileReader reader = new FileReader(str);
            BufferedReader bReader = new BufferedReader(reader);
            Vehicle tr = new Car(bReader.readLine(), 0);
            abq.put(tr);
        }
        catch(IOException | InterruptedException ex){System.out.println(ex.getMessage());}
    }
}
