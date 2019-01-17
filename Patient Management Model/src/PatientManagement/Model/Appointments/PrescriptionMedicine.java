/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Medicines.Medicine;
import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class PrescriptionMedicine implements Serializable
{
    private Medicine medicine;
    private int quantity;
    private String dosage;
    
    /**
     * Creates Prescription medicine instance.
     * @param medicine Medicine prescribed by the doctor
     * @param quantity Quantity of the medicine prescribed
     * @param dosage Medicine dosage information 
     */
    public PrescriptionMedicine(Medicine medicine, int quantity, String dosage)
    {
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }
    
    /**
     * Gets the details of prescription medicine.
     * @return Details including medicine name, quantity and dosage information
     */
    public String getDetails()
    {
        String details = "Medicine: " + medicine.getName() + " Quantity: " + quantity + medicine.getQuantityInformation() + " Dosage: " + dosage;
                
        return details;
    }
}
