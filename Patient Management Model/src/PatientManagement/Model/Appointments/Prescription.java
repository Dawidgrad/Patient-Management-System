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
     *
     * @param notes
     * @param medicine
     */
    public Prescription(Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        this.notes = notes;
        this.medicine = medicine;
    }

    /**
     *
     * @return
     */
    public Notes getNotes() {
        return notes;
    }

    /**
     *
     * @return
     */
    public ArrayList<PrescriptionMedicine> getMedicine() {
        return medicine;
    }
}
