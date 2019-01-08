/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class StockSingleton 
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
    
    public void CreateNewMedicine(String name, String description, int amount, float price, MedicineType type) 
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
        
        Medicine newMedicine = factory.CreateNewMedicine(highest + 1, name, description, amount, price, type);
        
        medicineList.add(newMedicine);
    }
    
    public ArrayList<Medicine> GetMedicineList()
    {
        return medicineList;
    }
    
    public boolean GiveMedicine(int medicineId, int amount)
    {
        Medicine medicineToGive = GetMedicine(medicineId);
        int medicineAmount = medicineToGive.getAmount();
        
        boolean result;
        if (medicineAmount >= amount)
        {
            medicineToGive.setAmount(medicineAmount - amount);
            result = true;
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
    public void OrderMedicine(int medicineId, int amount)
    {
        Medicine medicineToOrder = GetMedicine(medicineId);
        int medicineAmount = medicineToOrder.getAmount();
        
        medicineToOrder.setAmount(medicineAmount + amount);
    }
    
    public Medicine GetMedicine(int medicineId)
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
