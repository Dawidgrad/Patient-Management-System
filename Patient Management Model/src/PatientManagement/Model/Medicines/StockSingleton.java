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
        Medicine newMedicine = factory.CreateNewMedicine(name, description, amount, price, type);
        
        medicineList.add(newMedicine);
    }
    
    public ArrayList<Medicine> GetMedicineList()
    {
        return medicineList;
    }
}
