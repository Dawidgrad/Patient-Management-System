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
public abstract class Medicine implements Serializable
{
    private int medicineId;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private int amountInStock;
    
    public Medicine(int medicineId, String name, String description, int quantity, float price, int amount)
    {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.amountInStock = amount;
    }

    /* Getters and Setters */
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountInStock() {
        return amountInStock;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMedicineId() {
        return medicineId;
    }
    
    /* Methods */ 
    
    public abstract String GetInformation();
    
    public void addToStock(int amount) 
    {
        this.amountInStock += amount;
    }
    
    public void getFromStock(int amount)
    {
        this.amountInStock -= amount;
    }
}
