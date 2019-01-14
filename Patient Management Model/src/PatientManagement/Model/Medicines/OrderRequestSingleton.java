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
    
    public void processRequest(MedicineOrder order)
    {
        medicineToOrder.remove(order);
    }
    
    public ArrayList<MedicineOrder> getOrderList()
    {
        return medicineToOrder;
    }
    
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
