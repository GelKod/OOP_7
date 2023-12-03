package Work3.ClientHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import Work3.Motoricle.Motoricle;
import Work3.Vehicle.Vehicle;
import Work3.VehicleUtils.VehicleUtils;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            // Получение потоков ввода-вывода для обмена данными с клиентом
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Vehicle vehicle = (Vehicle)ois.readObject();
            double avg = VehicleUtils.getAverageModelPrice(vehicle);
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeDouble(avg);
            ois.close();
            dos.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
