package Work3.Motoricle;

import java.io.Serializable;



import Work3.DuplicateModelNameException;
import Work3.NoSuchModelNameException;
import Work3.Vehicle.Vehicle;

public class Motoricle implements Vehicle {
    private int size = 0;
    private Model head;
    private transient long lastModif;
    private String make;
    final String type = "Motoricle";

    public Motoricle(String make, int numModels) {
        this.make = make;

        head = new Model();
        head.next=head;
        head.prev=head;

        Model k;
        for (int i = 0; i < numModels; i++) {
            k = new Model("Model" + (i + 1), 0);
            k.next=head;
            k.prev=head.prev;
            head.prev.next=k;
            head.prev=k;
        }
        size = numModels;
    }

    @Override
    public String toString(){
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("\nКоличество " + make + ": " + size);
        Model tmp = head.next;
        while (tmp!=head) {
            strBuffer.append("\n"+tmp.name+ ": "+tmp.price);
            tmp=tmp.next;
        }
        return strBuffer.toString();
    }

    @Override
    public boolean equals(Object obj){
        if(obj==this){ return true; }
        boolean tmp = false;   
        Model temp = head.next;
        if(obj instanceof Vehicle){
            Vehicle vehicle = (Vehicle) obj;
            if(make.equals(vehicle.getMake()) && size==vehicle.getModelCount()){
                for(int i = 0; i<size; i++){
                    while(temp != head && temp.name.equals(vehicle.getModelNames()[i]) && temp.price==vehicle.getModelPrices()[i]){
                        temp = temp.next;
                    }
                    if(temp == head){
                        tmp = true;
                    }
                }
            }
        }
        return tmp;
    }

    @Override
    public int hashCode(){
        Model temp = head.next;
        int tmp = make.hashCode();
        tmp = 29*tmp + size;
        while (temp!=head) {
            tmp += temp.name.hashCode();
            tmp = 29*tmp + (int)temp.price;
            temp = temp.next;
        }
        return tmp;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        Motoricle cloning = (Motoricle)super.clone();
        cloning.head = new Model();
        Model q = head.next;
        Model p = cloning.head;
        while (q != head){
            p.next = new Model();
            p.next.prev = p;
            p.next = (Model)q.clone();
            p = p.next;
            q = q.next;
        }
        p.next = cloning.head;
        cloning.head.prev = p;
        return cloning;
    }

    public String getTypeClass() {
        return type;
    }
    //метод для получения марки автомобиля,
    public String getMake() {
        return this.make;
    }

    public Long GetLastModif(){
        return this.lastModif;
    }

    //метод для модификации марки автомобиля,
    public void setMake(String make) {
        this.make = make;
        lastModif=System.currentTimeMillis();
    }

    public void setModelName(int index, String name) throws DuplicateModelNameException{
        if(head!=null){
            Model tmp = head.next;
            while(tmp!=head){
                if(tmp.name.equals(name)){
                    throw new DuplicateModelNameException(name);
                }
                tmp=tmp.next;
            }
            tmp=head.next;
            int i =0;
            while((tmp!=head)&&(i!=index)){
                tmp=tmp.next;
                i++;
            }
            if(tmp!=head){
                tmp.name=name;
            }
        }
        lastModif=System.currentTimeMillis();
    }

    public void ModifName(String name, String oldName) throws NoSuchModelNameException, DuplicateModelNameException{
        if(head!=null){
            Model tmp = head.next;
            Model tmp1 = null;
            while(tmp!=head){
                if(tmp.name.equals(name)){
                    throw new DuplicateModelNameException(name);
                }
                else if(tmp.name.equals(oldName)){
                    tmp1=tmp;
                }
                tmp=tmp.next;
            }
            if(tmp1==null){
                throw new NoSuchModelNameException(oldName);
            }
            tmp1.name=name;
            lastModif = System.currentTimeMillis();
        }
    }

    public String[] getModelNames() {
        Model tmp = head.next;
        String[] names = new String[getModelCount()];
        for (int i = 0; i < getModelCount(); i++) {
            names[i] = tmp.name;
            tmp=tmp.next;
        }
        return names;
    }

    public double getModelPrice(String name) throws NoSuchModelNameException {
        Model tmp = head.next;
        if(head!=null){
            while(tmp!=head && !(tmp.name.equals(name))){
                tmp=tmp.next;
            }
            if(tmp==head){
                throw new NoSuchModelNameException(name);
            }
        }
        return tmp.price;
    }

    public void setModelPrice(String name, double price) throws NoSuchModelNameException {
        if(head!=null){
            Model tmp = head.next;
            while(tmp!=head){
                if (tmp.name.equals(name)) {
                    tmp.setPrice(price);
                    break;
                }
                tmp = tmp.next;
            }
            if(tmp==head){
                throw new NoSuchModelNameException(name);
            }
        }
        
        lastModif=System.currentTimeMillis();
    }

    public double[] getModelPrices() {
        double[] prices = new double[getModelCount()];
        Model k = head.next;
        for (int i = 0; i < getModelCount(); i++) {
            prices[i] = k.getPrice();
            k=k.next;
        }
        return prices;
    }

    public void addModel(String name, double price) throws DuplicateModelNameException{
        if(head!=null){
            Model tmp = head.next;
            while(tmp!=head){
                if(tmp.name.equals(name)){
                    throw new DuplicateModelNameException(name);
                }
                tmp=tmp.next;
            }
            Model k;
            k = new Model(name, price);
            k.next=head;
            k.prev=head.prev;
            head.prev.next=k;
            head.prev=k;
        }
    }

    public void removeModel(String name) throws NoSuchModelNameException{
        if(head!=null){
            Model tmp = head.next;
            while((tmp!=head)&&(!(tmp.name.equals(name)))){
                tmp=tmp.next;
            }
            if(tmp!=head){
                tmp.prev.next=tmp.next;
                tmp.next.prev=tmp.prev;
            }
            else{
                throw new NoSuchModelNameException(name);
            }
        }
    }
 
    public int getModelCount() {
        int k = 0;
        if(head!=null){
            Model tmp = head.next;
            while(tmp!=head){
                k++;
                tmp=tmp.next;
            }
        }
        return k;
    }
    private class Model implements Serializable, Cloneable {

        //полe название модели
        private String name;

        //поле цены
        private double price;

        Model prev = null; 
        Model next = null;

        public Model(){

        }
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