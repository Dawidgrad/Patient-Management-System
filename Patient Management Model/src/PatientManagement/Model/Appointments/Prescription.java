/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class Prescription implements Serializable
{
    private Notes notes;
    private ArrayList<PrescriptionMedicine> medicine;
    
    /**
     * Creates the prescription instance used in the appointment
     * @param notes Notes taken down by the doctor during the appointment
     * @param medicine Medicine list and dosages prescribed by the doctor during the appointment
     */
    public Prescription(Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        this.notes = notes;
        this.medicine = medicine;
    }

    /**
     * Gets the Note instance 
     * @return Note instance including text note written by the doctor during the appointment
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     * Gets medicine and dosages prescribed by the doctor during the appointment
     * @return Medicine list including dosages
     */
    public ArrayList<PrescriptionMedicine> getMedicine() {
        return medicine;
    }
}
