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
public abstract class MedicineFactory 
{
    public abstract Medicine createNewMedicine(int medicineId, String name, String description, int quantity, float price, int amount, MedicineType type); 
}
