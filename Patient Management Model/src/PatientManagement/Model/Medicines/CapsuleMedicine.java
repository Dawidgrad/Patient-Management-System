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
public class CapsuleMedicine extends Medicine
{
    
    /**
     * Creates capsule medicine instance.
     * @param medicineId Capsule medicine ID number
     * @param name Medicine name
     * @param description Medicine description
     * @param quantity Capsule medicine quantity in a pack
     * @param price Medicine price in GBP
     * @param amount Amount of medicine in stock
     */
    public CapsuleMedicine(int medicineId, String name, String description, int quantity, float price, int amount)
    {
        super(medicineId, name, description, quantity, price, amount);
    }

    /**
     * Gets the quantity units information
     * @return Quantity unit text information
     */
    @Override
    public String getQuantityInformation() 
    {
        return " capsules";
    }
    
}
