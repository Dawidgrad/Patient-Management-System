/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class Notes implements Serializable
{
    private String note;
    
    /**
     * Creates instance of the Notes class.
     * @param note Text notes written by the doctor during the appointment
     */
    public Notes(String note)
    {
        this.note = note;
    }

    /**
     * Gets the text note.
     * @return Text notes written by the doctor during the appointment
     */
    public String getNote() 
    {
        return note;
    }
    
}
