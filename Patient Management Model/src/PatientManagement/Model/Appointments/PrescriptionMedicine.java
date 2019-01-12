/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Medicines.Medicine;

/**
 *
 * @author Davio
 */
public class PrescriptionMedicine 
{
    private Medicine medicine;
    private int quantity;
    private String dosage;
    
    public PrescriptionMedicine(Medicine medicine, int quantity, String dosage)
    {
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }
    
    public String getDetails()
    {
        String details = "Medicine: " + medicine.getName() + " Quantity: " + quantity + " Dosage: " + dosage;
                
        return details;
    }
}
