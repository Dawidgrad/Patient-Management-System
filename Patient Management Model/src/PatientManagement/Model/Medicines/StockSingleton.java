/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class StockSingleton implements Serializable
{
    public static enum MedicineType {TABLET, LIQUID, CAPSULE}
    
    private ArrayList<Medicine> medicineList;
    private static StockSingleton uniqueInstance = null;
    
    private StockSingleton()
    {
        medicineList = new ArrayList<Medicine>();
    }
    
    public static StockSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new StockSingleton();
        }
        
        return uniqueInstance;
    }    
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException 
    {
        ois.defaultReadObject();
        uniqueInstance = this;
    }   
    
    private Object readResolve()
    {
        return uniqueInstance;
    }
    
    public void createNewMedicine(String name, String description, int quantity, float price, int amount, MedicineType type) 
    {
        ConcreteMedicineFactory factory = new ConcreteMedicineFactory();
        
        int highest = 0;
        
        for (Medicine medicine : medicineList)
        {
            if (medicine.getMedicineId() > highest)
            {
                highest = medicine.getMedicineId();
            }
        }
        
        Medicine newMedicine = factory.createNewMedicine(highest + 1, name, description, quantity, price, amount, type);
        
        medicineList.add(newMedicine);
    }
    
    public ArrayList<Medicine> getMedicineList()
    {
        return medicineList;
    }
    
    public boolean giveMedicine(int medicineId, int amount)
    {
        Medicine medicineToGive = getMedicine(medicineId);
        int medicineAmount = medicineToGive.getAmountInStock();
        
        boolean result;
        if (medicineAmount >= amount)
        {
            medicineToGive.getFromStock(amount);
            result = true;
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    public void orderMedicine(int medicineId, int amount)
    {
        Medicine medicineToOrder = getMedicine(medicineId);
        
        medicineToOrder.addToStock(amount);
    }
    
    public Medicine getMedicine(int medicineId)
    {
        Medicine targetMedicine = null;
        
        for (Medicine medicine : medicineList)
        {
            if (medicine.getMedicineId() == medicineId)
            {
                targetMedicine = medicine;
            }
        }
        
        return targetMedicine;
    }
}
