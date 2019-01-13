/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Reviews.DoctorFeedback;
import java.io.Serializable;
import java.util.Date;

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
    
    public void CreateAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.addAppointment(doctor, patient, date, time);
    }
}
