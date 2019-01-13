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

    @Override
    public Medicine CreateNewMedicine(int medicineId, String name, String description, int quantity, float price, int amount, MedicineType type) 
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
