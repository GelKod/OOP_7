package Work3.Main2;

import java.io.DataInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import Work3.Car.Car;
import Work3.Vehicle.Vehicle;

public class Main2 {
    public static void main(String[] args) throws Exception{

        Car c = new Car("Pezho", 5);
        c.setModelName(2, "Fiesta");
        c.setModelPrice("Fiesta",1000);
        c.setModelName(1, "Solyaris");
        c.setModelPrice("Solyaris",1000);
        c.setModelName(3, "Supra");
        c.setModelPrice("Supra",500);
        c.setModelName(0, "Skuline");
        c.setModelPrice("Skuline",4000);
        c.setModelName(4, "Astra");
        c.setModelPrice("Astra",100);

        Vehicle s = (Vehicle)c;

        Socket socket = new Socket("localhost", 1234);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(s);

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        double averagePrice = dis.readDouble();

        System.out.println("Среднее арифметическое значение цен моделей: " + averagePrice);

        oos.close();
        dis.close();
        socket.close();
    }
}
