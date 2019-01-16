/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class OrderRequestSingleton implements Serializable
{
    private static OrderRequestSingleton uniqueInstance = null;
    private ArrayList<MedicineOrder> medicineToOrder;
    
    private OrderRequestSingleton()
    {
        medicineToOrder = new ArrayList<MedicineOrder>();
    }
    
    /**
     * Method to get the instance of the singleton to prevent from creating multiple instances.
     * @return Returns instance of existing order request list singleton
     */
    public static OrderRequestSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new OrderRequestSingleton();
        }
        
        return uniqueInstance;
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException 
    {
        ois.defaultReadObject();
        uniqueInstance = this;
    }   
    
    private Object readResolve()
    {
        return uniqueInstance;
    }
    
    /**
     * Adds order request to the list.
     * @param medicine Medicine instance to be ordered
     * @param amountToOrder Amount of the medicine to be ordered
     */
    public void addOrderRequest(Medicine medicine, int amountToOrder)
    {
        int highest = 0;
        
        for (MedicineOrder order : medicineToOrder)
        {
            if (order.getOrderId() > highest)
            {
                highest = order.getOrderId();
            }
        }
        
        MedicineOrder order = new MedicineOrder(highest + 1, medicine, amountToOrder);
        medicineToOrder.add(order);
    }
    
    /**
     * Processes the order request by removing it from the list.
     * @param order Medicine order instance to process
     */
    public void processRequest(MedicineOrder order)
    {
        StockSingleton medicineList = StockSingleton.getInstance();
        medicineList.orderMedicine(order.getMedicine().getMedicineId(), order.getAmountToOrder());
        medicineToOrder.remove(order);
    }
    
    /**
     * Gets the complete medicine order list.
     * @return Current list of medicines to be ordered, including their amount
     */
    public ArrayList<MedicineOrder> getOrderList()
    {
        return medicineToOrder;
    }
    
    /**
     * Gets the order instance with given order ID
     * @param orderId ID number of the order instance to return
     * @return Order instance with given ID number
     */ 
    public MedicineOrder getOrder(int orderId)
    {
        MedicineOrder targetOrder = null;
        
        for (MedicineOrder order : medicineToOrder)
        {
            if (order.getOrderId() == orderId)
            {
                targetOrder = order;
            }
        }
        
        return targetOrder;
    }
}
