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
    public Medicine CreateNewMedicine(int medicineId, String name, String description, int amount, float price, MedicineType type) 
    {
        Medicine newMedicine;
        
        switch (type)
        {
            case TABLET:
                newMedicine = new TabletMedicine(medicineId, name, description, amount, price);
                break;
            case LIQUID:
                newMedicine = new LiquidMedicine(medicineId, name, description, amount, price);
                break;
            case CAPSULE:
                newMedicine = new CapsuleMedicine(medicineId, name, description, amount, price);
                break;
            default:
                newMedicine = null;
        }
        
        return newMedicine;
    }
    
}
