/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import java.util.ArrayList;


/**
 *
 * @author Davio
 */
public class Secretary extends Account
{
    public Secretary(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.SECRETARY);
    }
    
    public ArrayList<Appointment> GetAppointmentRequests()
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        
        return appointments.GetStateList(Appointment.AppointmentState.REQUESTED);
    }
    
    public void ProcessAppointmentRequest(Appointment appointment, Doctor doctor)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.ApproveRequest(appointment, doctor);
    }
}
