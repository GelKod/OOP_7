package Work3.Vehicle;

import java.io.Serializable;

import Work3.DuplicateModelNameException;
import Work3.ModelPriceOutOfBoundException;
import Work3.NoSuchModelNameException;

public interface Vehicle  extends Serializable, Cloneable {
    String getTypeClass();
    String getMake();
    void ModifName(String name, String Oldname ) throws NoSuchModelNameException, DuplicateModelNameException;
    void setMake(String make);
    String[] getModelNames();
    double getModelPrice(String name) throws NoSuchModelNameException;
    void setModelPrice(String name, double price) throws NoSuchModelNameException, ModelPriceOutOfBoundException;
    double[] getModelPrices();
    void addModel(String name, double price) throws DuplicateModelNameException, ModelPriceOutOfBoundException;
    void removeModel(String name) throws NoSuchModelNameException;
    int getModelCount();
    String toString();
    boolean equals(Object obj);
    int hashCode();
}