/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

/**
 *
 * @author Davio
 */
public class LiquidMedicine extends Medicine
{
    public LiquidMedicine(int medicineId, String name, String description, int quantity, float price, int amount)
    {
        super(medicineId, name, description, quantity, price, amount);
    }

    @Override
    public String getQuantityInformation() 
    {
       return " ml"; 
    }
    

    
}
