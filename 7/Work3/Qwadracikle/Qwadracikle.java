package Work3.Qwadracikle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import Work3.DuplicateModelNameException;
import Work3.ModelPriceOutOfBoundException;
import Work3.NoSuchModelNameException;
import Work3.Car.Car;
import Work3.Vehicle.Vehicle;

public class Qwadracikle implements Vehicle {
        //поле типа String, хранящее марку автомобиля,
    private String make;
    final String type = "Qwadracikle";

    //класс Автомобиль хранит массив Моделей
    private ArrayList<Model> models = new ArrayList<Model>();

    public Qwadracikle(String make, int numModels) {
        this.make = make;
        for(int i=0; i<numModels;i++){
            models.add(new Model("Model "+i, i));
        }
    }

    @Override
    public String toString(){
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("\nКоличество " + make + ": " + models.size());
        for(int i = 0; i<models.size(); i++){
            strBuffer.append("\n"+models.get(i).name+ ": "+models.get(i).price);
        }
        return strBuffer.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj==this){ return true; }
        boolean tmp = false;
        if(obj instanceof Vehicle){
            Vehicle vehicle = (Vehicle) obj;
            if(make.equals(vehicle.getMake()) && models.size()==vehicle.getModelCount()){
                for(int i = 0; i<models.size(); i++){
                    tmp = true;
                    if(!(models.get(i).name.equals(vehicle.getModelNames()[i]) && models.get(i).price==vehicle.getModelPrices()[i])){
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
        tmp = 29*tmp + models.size();
        for(int i = 0; i<models.size(); i++){
            tmp =tmp + models.get(i).name.hashCode();
            tmp = 29*tmp + (int)models.get(i).price;
        }
        return tmp;
    }

    public String getTypeClass() {
        return type;
    }

    //метод для получения марки автомобиля,
    public String getMake() {
        return this.make;
    }
    //метод для модификации марки автомобиля,
    public void setMake(String make) {
        this.make = make;
    }

    public void ModifName (String name, String Oldname) throws NoSuchModelNameException, DuplicateModelNameException{
        int i=0;
        int k = 0;
        while(i<models.size()){
            if(models.get(i).name.equals(Oldname)){
                k=i;
            }
            else if(models.get(i).name.equals(name)){
                throw new DuplicateModelNameException(name);
            }
            i++;
        }
        if(i!=models.size()){
            throw new NoSuchModelNameException(Oldname);
        }
        models.get(k).name=name;
    }

    public void setModelName(int index, String name) throws DuplicateModelNameException {
        for(int i = 0; i<getModelCount();i++ ){
            if(models.get(i).name.equals(name)){
                throw new DuplicateModelNameException(name);
            }
        }
        this.models.get(index).setName(name);
    }

    public String[] getModelNames() {
        String[] names = new String[this.models.size()];
        for (int i = 0; i < this.models.size(); i++) {
            names[i] = this.models.get(i).getName();
        }
        return names;
    }

    public double getModelPrice(String name) throws NoSuchModelNameException{
        double chek=-1;
        for (Model model : this.models) {
            if (model.getName().equals(name)) {
                chek = model.getPrice();
            }
        }
        if(chek==-1){
            throw new NoSuchModelNameException(name);
        }
        return chek;
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundException {
        double chek=-1;
        if(price>0){
            for (Model model : this.models) {
                if (model.getName().equals(name)) {
                    model.setPrice(price);
                    chek=2;
                }
            }
        }
        else{
            throw new ModelPriceOutOfBoundException();
        }
        if(chek==-1){
            throw new NoSuchModelNameException(name);
        }
    }

    public double[] getModelPrices() {
        double[] prices = new double[this.models.size()];
        for (int i = 0; i < this.models.size(); i++) {
            prices[i] = this.models.get(i).getPrice();
        }
        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundException{
        if(price<0){
            throw new ModelPriceOutOfBoundException();
        }
        for(int i = 0; i<getModelCount();i++ ){
            if(models.get(i).name.equals(name)){
                throw new DuplicateModelNameException(name);
            }
        }
        this.models.add(new Model(name, price));
    }

    public void removeModel(String name) throws NoSuchModelNameException {
        int index = -1;
        for (int i = 0; i < this.models.size(); i++) {
            if (this.models.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchModelNameException(name);
        }
        else{
            this.models.remove(index);
        }
    }


    public int getModelCount() {
        return this.models.size();
    }

    private class Model implements Serializable, Cloneable{

        //полe название модели
        private String name;

        //поле цены
        private double price;

        //конструктор
        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }

        //получение названия
        public String getName() {
            return this.name;
        }

        //изменение названия
        public void setName(String name) {
            this.name = name;
        }

        //получение цены
        public double getPrice() {
            return this.price;
        }

        //изменение цены
        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public Model clone() throws CloneNotSupportedException {
            return (Model) super.clone();
        }
    }
}
