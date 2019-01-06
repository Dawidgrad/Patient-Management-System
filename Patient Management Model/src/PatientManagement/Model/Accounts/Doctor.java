/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Reviews.DoctorFeedback;

/**
 *
 * @author Davio
 */
public class Doctor extends Account 
{
    private DoctorFeedback feedback;
            
    public Doctor(String name, String surname, String address, String idNumber)
    {
        super(name, surname, address, idNumber, LoginSystemSingleton.AccountType.DOCTOR);
    }
    
    public void UpdateFeedback(DoctorFeedback feedback)
    {
        this.feedback = feedback;
    }
}
