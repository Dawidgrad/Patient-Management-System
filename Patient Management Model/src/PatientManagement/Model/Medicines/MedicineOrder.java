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
public class MedicineOrder 
{
    private Medicine medicine;
    private int amountToOrder;
    
    public MedicineOrder(Medicine medicine, int amountToOrder)
    {
        this.medicine = medicine;
        this.amountToOrder = amountToOrder;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }
}
