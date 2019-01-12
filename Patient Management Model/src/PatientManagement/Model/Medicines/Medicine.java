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
    private int amount;
    private float price;
    
    public Medicine(int medicineId, String name, String description, int amount, float price)
    {
        this.medicineId = medicineId;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.price = price;
    }

    /* Getters and Setters */
    
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
}
