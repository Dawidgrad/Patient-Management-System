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

    /**
     * Type of the medicine
     */
    public static enum MedicineType { 

        /**
         * Tablet medicine
         */
        TABLET, 

        /**
         * Liquid medicine
         */
        LIQUID, 

        /**
         * Capsule medicine
         */
        CAPSULE}
    
    private ArrayList<Medicine> medicineList;
    private static StockSingleton uniqueInstance = null;
    
    private StockSingleton()
    {
        medicineList = new ArrayList<Medicine>();
    }
    
    /**
     * Method to get the instance of the singleton to prevent from creating multiple instances.
     * @return Returns instance of existing order request list singleton
     */
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
    
    /**
     * Method utilising factory to create new instance of medicine.
     * @param name Medicine name
     * @param description Medicine description
     * @param quantity Medicine quantity in pack / bottle
     * @param price Medicine price in GBP
     * @param amount Medicine amount in stock
     * @param type Type of the medicine
     */
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
    
    /**
     * Gets the list of medicine in the system.
     * @return Complete list of medicine in the system
     */
    public ArrayList<Medicine> getMedicineList()
    {
        return medicineList;
    }
    
    /**
     * Removes the medicine in specified amount from the stock.
     * @param medicineId ID number of the medicine
     * @param amount Amount of medicine to remove
     * @return True / False value indicating if the process was successful
     */
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
    
    /**
     * Orders the medicine and adds it to the stock.
     * @param medicineId Medicine ID number 
     * @param amount Amount of the medicine to order
     */
    public void orderMedicine(int medicineId, int amount)
    {
        Medicine medicineToOrder = getMedicine(medicineId);
        
        medicineToOrder.addToStock(amount);
    }
    
    /**
     * Gets medicine instance based on its ID number.
     * @param medicineId ID number of the medicine
     * @return Medicine instance with given ID number
     */
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
