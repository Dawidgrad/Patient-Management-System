/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Appointments.Appointment.AppointmentState;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class AppointmentListSingleton implements Serializable
{
    private static AppointmentListSingleton uniqueInstance = null;
    private ArrayList<Appointment> appointmentList = null;
    
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
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException 
    {
        ois.defaultReadObject();
        uniqueInstance = this;
    }   
    
    private Object readResolve()
    {
        return uniqueInstance;
    }
    
    public void addRequest(Patient patient, Date date, Doctor doctor, String time)
    {
        int highest = 1;
        
        for (Appointment appointment : appointmentList)
        {
            if (appointment.getAppointmentId() > highest)
            {
                highest = appointment.getAppointmentId();
            }
        }
        
        Appointment request = new Appointment(highest, patient, date, doctor, time);
        appointmentList.add(request);
    }
    
    public ArrayList<Appointment> getStateList(AppointmentState state)
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
    
    public void approveRequest(int appointmentId)
    {
        Appointment targetAppointment = null;
        
        for (Appointment appointment : appointmentList)
        {
            if (appointment.getAppointmentId() == appointmentId)
            {
                targetAppointment = appointment;
                break;
            }
        }
        
        targetAppointment.ProcessRequest();
    }
    
    public void addAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        int highest = 1;
        
        for (Appointment appointment : appointmentList)
        {
            if (appointment.getAppointmentId() > highest)
            {
                highest = appointment.getAppointmentId();
            }
        }
        
        Appointment appointment = new Appointment(highest, patient, date, doctor, time, AppointmentState.APPROVED);
        appointmentList.add(appointment);
    }
    
    public ArrayList<Appointment> getPatientHistory(Patient patient)
    {
        ArrayList<Appointment> targetAppointments = new ArrayList<Appointment>();
        
        for (Appointment element : appointmentList)
        {
            if (element.getState() == AppointmentState.ARCHIVED && element.getPatient() == patient)
            {
                targetAppointments.add(element);
            }
        }
        
        return targetAppointments;
    }
    
    public void completeAppointment(Appointment appointment)
    {
        appointment.setState(AppointmentState.ARCHIVED);
        appointment.getPatient().completeAppointment();
        
    }
    
    public Appointment getAppointment(int appointmentId)
    {
        Appointment targetAppointment = null;
        
        for (Appointment appointment : appointmentList)
        {
            if (appointment.getAppointmentId() == appointmentId)
            {
                targetAppointment = appointment;
                break;
            }
        }
        
        return targetAppointment;
    }
}
