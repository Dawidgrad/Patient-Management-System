/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

import PatientManagement.Model.Medicines.StockSingleton.MedicineType;

/**
 *
 * @author Davio
 */
public class ConcreteMedicineFactory extends MedicineFactory
{

    /**
     * Creates the instance of the medicine with the type specified at run time.
     * @param medicineId ID number of the medicine
     * @param name Medicine name
     * @param description Description of the medicine
     * @param quantity Medicine quantity
     * @param price Medicine price
     * @param amount Amount of medicine in stock
     * @param type Type of the medicine (Tablet, Liquid, Capsule)
     * @return New instance of the medicine of specified type
     */
    @Override
    public Medicine createNewMedicine(int medicineId, String name, String description, int quantity, float price, int amount, MedicineType type) 
    {
        Medicine newMedicine;
        
        switch (type)
        {
            case TABLET:
                newMedicine = new TabletMedicine(medicineId, name, description, quantity, price, amount);
                break;
            case LIQUID:
                newMedicine = new LiquidMedicine(medicineId, name, description, quantity, price, amount);
                break;
            case CAPSULE:
                newMedicine = new CapsuleMedicine(medicineId, name, description, quantity, price, amount);
                break;
            default:
                newMedicine = null;
        }
        
        return newMedicine;
    }
    
}
