/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class MedicineOrder implements Serializable
{
    private int orderId;
    private Medicine medicine;
    private int amountToOrder;
    
    public MedicineOrder(int orderId, Medicine medicine, int amountToOrder)
    {
        this.medicine = medicine;
        this.amountToOrder = amountToOrder;
        this.orderId = orderId;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public int getAmountToOrder() {
        return amountToOrder;
    }

    public int getOrderId() {
        return orderId;
    }
    
}
