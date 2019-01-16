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
     *
     * @param note
     */
    public Notes(String note)
    {
        this.note = note;
    }

    /**
     *
     * @return
     */
    public String getNote() 
    {
        return note;
    }
    
}
