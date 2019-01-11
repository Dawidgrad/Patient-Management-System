/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Appointments.Appointment.AppointmentState;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Davio
 */
public class AppointmentListSingleton 
{
    private static AppointmentListSingleton uniqueInstance = null;
    private static ArrayList<Appointment> appointmentList = null;
    
    private AppointmentListSingleton()
    {
        appointmentList = new ArrayList<Appointment>();
    }
    
    public static AppointmentListSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new AppointmentListSingleton();
        }
        
        return uniqueInstance;
    }
    
    public void AddRequest(Patient patient, Date date, Doctor doctor, String time)
    {
        Appointment request = new Appointment(patient, date, doctor, time);
        appointmentList.add(request);
    }
    
    public ArrayList<Appointment> GetStateList(AppointmentState state)
    {
        ArrayList<Appointment> targetAppointments = new ArrayList<Appointment>();
        
        for (Appointment element : appointmentList)
        {
            if (element.getState() == state)
            {
                targetAppointments.add(element);
            }
        }
        
        return targetAppointments;
    }
    
    public void ApproveRequest(Appointment appointment, Doctor doctor)
    {
        appointment.ProcessRequest(doctor);
    }
}
