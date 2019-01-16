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
    
    /**
     * Creates new instance of Medicine.
     * @param medicineId ID number of the medicine
     * @param name Medicine name
     * @param description Description of the medicine
     * @param quantity Medicine quantity
     * @param price Medicine price
     * @param amount Amount of medicine in stock
     */
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

    /**
     * Gets the medicine name.
     * @return Medicine name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the medicine.
     * @return Medicine description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the amount of the medicine in stock.
     * @return Amount of medicine in stock
     */
    public int getAmountInStock() {
        return amountInStock;
    }

    /**
     * Gets the quantity of the medicine in a pack / bottle.
     * @return Quantity of medicine
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the price of the medicine.
     * @return Price of the medicine in GBP
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets new description for the medicine.
     * @param description Text description of medicine
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets new price for the medicine.
     * @param price Price of the medicine
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets the medicine ID number.
     * @return ID number of the medicine
     */
    public int getMedicineId() {
        return medicineId;
    }
    
    /* Methods */ 

    /**
     * Abstract method to get the quantity units information, such as "ml" or "tablets".
     * @return
     */
    public abstract String getQuantityInformation();
    
    /**
     * Adds specified amount of medicine to stock
     * @param amount Amount of medicine to add
     */
    public void addToStock(int amount) 
    {
        this.amountInStock += amount;
    }
    
    /**
     * Removes specified amount of medicine from stock
     * @param amount Amount of medicine to remove
     */
    public void getFromStock(int amount)
    {
        this.amountInStock -= amount;
    }
}
