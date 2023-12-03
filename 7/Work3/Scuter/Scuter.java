package Work3.Scuter;

import java.util.HashMap;

import Work3.DuplicateModelNameException;
import Work3.ModelPriceOutOfBoundException;
import Work3.NoSuchModelNameException;
import Work3.Car.Car;
import Work3.Vehicle.Vehicle;

public class Scuter implements Vehicle {
    private String make;
    final String type = "Scuter";
    private HashMap<String,Double> hashMap = new HashMap<String,Double>();

    public Scuter(String make, int num){
        this.make = make;
        for (int i = 0; i < num; i++) {
            hashMap.put("Model " + i, 2.0);
        }
    }

    public String getTypeClass() {
        return type;
    }

    public String getMake() {
        return make;    
    }

    public void ModifName(String name, String Oldname) throws NoSuchModelNameException, DuplicateModelNameException {
        if(hashMap.containsKey(name)){
            if(!hashMap.containsKey(Oldname)){
                hashMap.put(Oldname, hashMap.get(name));
                hashMap.remove(name);
            }
            else{throw new DuplicateModelNameException(Oldname);}
        }
        else{throw new NoSuchModelNameException(name);}
    }

    public void setMake(String make) {
        this.make=make;
    }

    public String[] getModelNames() {
        String[] names=new String[hashMap.size()];
        int i=0;
        for(String j : hashMap.keySet()){
            names[i] = j;
            i++;
        }
        return names;
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {
        if(!hashMap.containsKey(name)){
            throw new NoSuchModelNameException(name);
        }
        return hashMap.get(name);
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundException {
        if(!hashMap.containsKey(name)){throw new NoSuchModelNameException(name);}
        if(price<0){throw new ModelPriceOutOfBoundException();}
        hashMap.replace(name,price);
    }

    public double[] getModelPrices() {
        double[] prices=new double[hashMap.size()];
        int i=0;
        for(String j : hashMap.keySet()){
            prices[i] = hashMap.get(j);
            i++;
        }
        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundException {
        if(hashMap.containsKey(name)){throw new DuplicateModelNameException(name);}
        if(price<0){throw new ModelPriceOutOfBoundException();}
        hashMap.put(name, price);
    }

    public void removeModel(String name) throws NoSuchModelNameException {
        if(!hashMap.containsKey(name)){throw new NoSuchModelNameException(name);}
        hashMap.remove(name);
    }

    public int getModelCount(){return hashMap.size();}    

    @Override
    public String toString(){
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("\nКоличество " + make + ": " + hashMap.size());
        for(String j : hashMap.keySet()){
            strBuffer.append("\n"+j+ ": "+hashMap.get(j));
        }
        return strBuffer.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj==this){ return true; }
        boolean tmp = false;
        if(obj instanceof Vehicle){
            Vehicle vehicle = (Vehicle) obj;
            if(make.equals(vehicle.getMake()) && hashMap.size()==vehicle.getModelCount()){
                for(int i = 0; i<hashMap.size(); i++){
                    tmp = true;
                    if(!(getModelNames()[i].equals(vehicle.getModelNames()[i]) && getModelPrices()[i]==vehicle.getModelPrices()[i])){
                        tmp = false;
                        break;
                    }
                }
            }
        }
        return tmp;
    }

    @Override
    public int hashCode(){
        int tmp = make.hashCode();
        tmp = 29*tmp + hashMap.size();
        for(String j : hashMap.keySet()){
            tmp =tmp + j.hashCode();
            tmp = (int)(29*tmp + hashMap.get(j));
        }
        return tmp;
    }
}
