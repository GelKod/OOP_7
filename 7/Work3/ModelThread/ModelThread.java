package Work3.ModelThread;

import Work3.Vehicle.Vehicle;

public class ModelThread extends Thread {
    private final Vehicle vehicle;

    public ModelThread(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        String[] models = vehicle.getModelNames();
        for (String model : models) {
            System.out.println(model);
        }
    }
}
