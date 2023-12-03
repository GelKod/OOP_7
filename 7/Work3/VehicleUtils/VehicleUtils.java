package Work3.VehicleUtils;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.lang.Class;

import Work3.DuplicateModelNameException;
import Work3.ModelPriceOutOfBoundException;
import Work3.NoSuchModelNameException;
import Work3.Car.Car;
import Work3.Moped.Moped;
import Work3.Motoricle.Motoricle;
import Work3.Qwadracikle.Qwadracikle;
import Work3.Scuter.Scuter;
import Work3.Vehicle.Vehicle;

public class VehicleUtils {
    public static double getAverageModelPrice(Vehicle vehicle) {
        double[] prices = vehicle.getModelPrices();
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    public static double getAverageClassesPrice(Vehicle ... vehicles){
        double avg=0;
        int chek = 0;
        for (Vehicle v : vehicles){
            avg += getAverageModelPrice(v);
            chek++;
        }
        if(chek!=0){
            avg=avg/chek;
            return avg;
        }
        return 0;
    }

    public static void printAllModelNames(Vehicle vehicle) {
        String[] names = vehicle.getModelNames();
        for (String name : names) {
            System.out.println(name);
        }
    }

    public static void printAllModelPrices(Vehicle vehicle) {
        String[] names = vehicle.getModelNames();
        double[] prices = vehicle.getModelPrices();
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i] + ": " + prices[i]);
        }
    }

    public static void outputVehicle(Vehicle v,OutputStream out) throws IOException, NoSuchModelNameException {
        DataOutputStream dataOutputStream = new DataOutputStream(out);

        byte[] bytes = v.getTypeClass().getBytes();
        dataOutputStream.writeInt(bytes.length);
        for (int i = 0; i < bytes.length; i++)
        {
            dataOutputStream.write(bytes[i]);
        }

        bytes = v.getMake().getBytes();
        dataOutputStream.writeInt(bytes.length);
        for (int i = 0; i < bytes.length; i++)
        {
            dataOutputStream.write(bytes[i]);
        }

        int size = v.getModelCount();
        dataOutputStream.writeInt(size);

        for (int i = 0; i < size; i++)
        {
            bytes = v.getModelNames()[i].getBytes();
            dataOutputStream.writeInt(bytes.length);
            for (int j = 0; j < bytes.length; j++)
            {
                dataOutputStream.write(bytes[j]);
            }

            dataOutputStream.writeDouble(v.getModelPrice(v.getModelNames()[i]));
        }
    }

    public static Vehicle inputVehicle(InputStream in) throws IOException, ModelPriceOutOfBoundException, NoSuchModelNameException, DuplicateModelNameException{
        DataInputStream inputStream = new DataInputStream(in);

        int size = inputStream.readInt();

        byte[] bytes = new byte[size];
        for (int i = 0; i < size; i++)
        {
            bytes[i] = inputStream.readByte();
        }
        String type = new String(bytes);

        size = inputStream.readInt();
        bytes = new byte[size];
        for (int i = 0; i < size; i++)
        {
            bytes[i] = inputStream.readByte();
        }
        String name = new String(bytes);

        int models = inputStream.readInt();
        Vehicle vehicle = null;
        switch (type) {
            case "Car":
                vehicle = new Car(name,models);
                break;
            case "Motoricle":
                vehicle = new Motoricle(name,models);
                break;
            default:
                throw new NoSuchModelNameException(type);
        }
        for (int i = 0; i < models; i++)
        {
            size = inputStream.readInt();
            bytes = new byte[size];
            for (int j = 0; j < size; j++)
            {
                bytes[j] = inputStream.readByte();
            }
            name = new String(bytes);
            double price = inputStream.readDouble();
            vehicle.ModifName(name,vehicle.getModelNames()[i]);
            vehicle.setModelPrice(name,price);
        }
        return vehicle;
    }

    public static void writeVehicle(Vehicle v, Writer out) throws IOException{
        PrintWriter pr = new PrintWriter(out);
        pr.printf("Название класса %n%s",v.getTypeClass());
        pr.printf("%nНазвание марки %n%s",v.getMake());
        pr.printf("%nКоличество моделей данной марки %n%s",v.getModelCount());
        for (int i = 0; i < v.getModelCount(); i++)
        {
            pr.printf("%n%s",v.getModelNames()[i]);
            pr.printf("%n%s",v.getModelPrices()[i]);
        }
        pr.flush();
    }

    public static Vehicle readVehicle(Reader r) throws IOException, NumberFormatException, ModelPriceOutOfBoundException, DuplicateModelNameException, NoSuchModelNameException{
        Scanner sc = new Scanner(r);
        sc.nextLine();
        String type = sc.nextLine();
        sc.nextLine();
        String name = sc.nextLine();
        sc.nextLine();
        int size = Integer.parseInt(sc.nextLine());
        Vehicle tmp = null;
        switch (type) {
            case "Car":
                tmp = new Car(name,size);
                break;
            case "Motoricle":
                tmp = new Motoricle(name,size);
                break;
            case "Scuter":
                tmp = new Scuter(name,size);
                break;
            case "Qwadracikle":
                tmp = new Qwadracikle(name,size);
                break;
            case "Moped":
                tmp = new Moped(name,size);
                break;
            default:
                throw new NoSuchModelNameException(type);
        }
        for (int i = 0; i < size; i++)
        {
            name = sc.nextLine();
            double price = Double.parseDouble(sc.nextLine());
            tmp.ModifName(name,tmp.getModelNames()[i]);
            tmp.setModelPrice(name,price);
        }
        sc.close();
        return tmp;
    }

    public static Vehicle createVehicle(String mark, int num, Vehicle a) throws Exception{
        Class<?> newClass = a.getClass();
        try{
            Constructor<?> tmp = newClass.getConstructor(new Class[]{String.class,int.class});
            return (Vehicle)tmp.newInstance(new Object[]{mark,num});
        }
        catch(Exception e){
            return null;
        }

    }

    
}