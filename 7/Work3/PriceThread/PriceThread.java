package Work3.PriceThread;

import Work3.Vehicle.Vehicle;

public class PriceThread extends Thread {
    private final Vehicle vehicle;

    public PriceThread(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void run() {
        double[] prices = vehicle.getModelPrices();
        for (double price : prices) {
            System.out.println(price);
        }
    }
}