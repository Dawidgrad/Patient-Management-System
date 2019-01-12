/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Reviews.DoctorFeedback;
import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class Doctor extends Account implements Serializable
{
    private DoctorFeedback feedback;
            
    public Doctor(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.DOCTOR);
    }
    
    public void UpdateAdministratorFeedback(String feedback)
    {
        this.feedback.setAdministratorFeedback(feedback);
    }
}
