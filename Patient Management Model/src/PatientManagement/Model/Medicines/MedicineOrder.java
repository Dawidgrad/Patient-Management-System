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
    
    /**
     * Creates medicine order.
     * @param orderId ID of the order
     * @param medicine Medicine instance to order
     * @param amountToOrder Amount of the medicine to order
     */
    public MedicineOrder(int orderId, Medicine medicine, int amountToOrder)
    {
        this.medicine = medicine;
        this.amountToOrder = amountToOrder;
        this.orderId = orderId;
    }

    /**
     * Gets the medicine instance to order
     * @return Medicine to order
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * Gets the amount of medicine that needs to be ordered
     * @return Amount of the medicine to order
     */
    public int getAmountToOrder() {
        return amountToOrder;
    }

    /**
     * Gets the ID number of the order
     * @return ID number of the order
     */
    public int getOrderId() {
        return orderId;
    }
    
}
