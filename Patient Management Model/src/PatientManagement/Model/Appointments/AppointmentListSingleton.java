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
    private int nextAppointmentId;
    
    private AppointmentListSingleton()
    {
        appointmentList = new ArrayList<Appointment>();
    }
    
    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param patient
     * @param date
     * @param doctor
     * @param time
     */
    public void addRequest(Patient patient, Date date, Doctor doctor, String time)
    {
        int highest = 0;
        
        for (Appointment appointment : appointmentList)
        {
            if (appointment.getAppointmentId() > highest)
            {
                highest = appointment.getAppointmentId();
            }
        }
        
        Appointment request = new Appointment(highest + 1, patient, date, doctor, time);
        appointmentList.add(request);
    }
    
    /**
     *
     * @param state
     * @return
     */
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
    
    /**
     *
     * @param appointmentId
     */
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
        
        targetAppointment.processRequest();
    }
    
    /**
     *
     * @param appointmentId
     */
    public void removeRequest(int appointmentId)
    {        
        for (Appointment appointment : appointmentList)
        {
            if (appointment.getAppointmentId() == appointmentId)
            {
                appointmentList.remove(appointment);
                break;
            }
        }
    }
    
    /**
     *
     * @param doctor
     * @param patient
     * @param date
     * @param time
     */
    public void addAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        Appointment appointment = new Appointment(nextAppointmentId, patient, date, doctor, time, AppointmentState.APPROVED);
        appointmentList.add(appointment);
        
        nextAppointmentId++;
    }
    
    /**
     *
     * @param patient
     * @return
     */
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
    
    /**
     *
     * @param doctor
     * @return
     */
    public ArrayList<Appointment> getScheduledAppointments(Doctor doctor)
    {
        ArrayList<Appointment> targetAppointments = new ArrayList<Appointment>();
        
        for (Appointment element : appointmentList)
        {
            if (element.getState() == AppointmentState.APPROVED && element.getDoctor() == doctor)
            {
                targetAppointments.add(element);
            }
        }
        
        return targetAppointments;
    }
    
    /**
     *
     * @param appointment
     */
    public void completeAppointment(Appointment appointment)
    {
        appointment.setState(AppointmentState.ARCHIVED);
        appointment.getPatient().completeAppointment();
    }
    
    /**
     *
     * @param appointmentId
     * @return
     */
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
