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
    public Medicine CreateNewMedicine(String name, String description, int amount, float price, MedicineType type) 
    {
        Medicine newMedicine;
        
        switch (type)
        {
            case TABLET:
                newMedicine = new TabletMedicine(name, description, amount, price);
                break;
            case LIQUID:
                newMedicine = new LiquidMedicine(name, description, amount, price);
                break;
            case CAPSULE:
                newMedicine = new CapsuleMedicine(name, description, amount, price);
                break;
            default:
                newMedicine = null;
        }
        
        return newMedicine;
    }
    
}
