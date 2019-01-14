/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Davio
 */
public class Secretary extends Account implements Serializable
{
    public Secretary(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.SECRETARY);
    }
    
    public ArrayList<Appointment> getAppointmentRequests()
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        
        return appointments.getStateList(Appointment.AppointmentState.REQUESTED);
    }
    
    public void processAppointmentRequest(int appointmentId)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.approveRequest(appointmentId);
    }
    
    public void createAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.addAppointment(doctor, patient, date, time);
    }
    
    public void verifyAccount(Patient patient)
    {
        AccountVerificationSingleton verificationList = AccountVerificationSingleton.getInstance();
        verificationList.verifyAccount(patient);
    }
     
    public void terminateAccount(Patient patient)
    {
        AccountTerminationSingleton terminationList = AccountTerminationSingleton.getInstance();
        terminationList.removeAccount(patient);
    }
}
