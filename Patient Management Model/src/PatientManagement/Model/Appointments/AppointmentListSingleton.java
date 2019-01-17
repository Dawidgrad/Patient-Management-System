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
     * Method to get the instance of the singleton to prevent from creating multiple instances.
     * @return Returns instance of existing appointment list singleton
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
     * Adds the appointment with REQUEST state to the appointment list.
     * @param patient Patient account instance that has requested the appointment
     * @param date Requested date of the appointment
     * @param doctor Doctor account instance assigned to the appointment
     * @param time Requested time of the appointment
     */
    public void addRequest(Patient patient, Date date, Doctor doctor, String time)
    {        
        Appointment request = new Appointment(nextAppointmentId, patient, date, doctor, time);
        appointmentList.add(request);
        
        nextAppointmentId++;
    }
    
    /**
     * Gets the appointment list that have a provided state.
     * @param state State of the appointment (REQUEST, APPROVED, ARCHIVED)
     * @return List of the appointments with a given state
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
     * Approves the appointment request, consequently changing its state to APPROVED.
     * @param appointmentId ID number of the appointment to approve
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
     * Removes the appointment request from the list.
     * @param appointmentId ID number of the appointment to remove
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
     * Adds the appointment with APPROVED state to the appointment list.
     * @param doctor Doctor account instance assigned to the appointment
     * @param patient Patient account instance assigned to the appointment
     * @param date Scheduled date of the appointment
     * @param time Scheduled time of the appointment
     */
    public void addAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        Appointment appointment = new Appointment(nextAppointmentId, patient, date, doctor, time, AppointmentState.APPROVED);
        appointmentList.add(appointment);
        
        nextAppointmentId++;
    }
    
    /**
     * Gets the appointments with ARCHIVED state assigned to a specific patient.
     * @param patient Patient account instance that the appointments are assigned to
     * @return Complete list of appointments that have taken place involving specified patient
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
     * Gets the list of the scheduled appointments for a specific doctor.
     * @param doctor Doctor account instance that the appointments are assigned to
     * @return Complete list of upcoming appointments assigned to the specified doctor
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
     * Completes the appointment, setting its state to ARCHIVED.
     * @param appointment Appointment instance to complete
     */
    public void completeAppointment(Appointment appointment)
    {
        appointment.setState(AppointmentState.ARCHIVED);
        appointment.getPatient().completeAppointment();
    }
    
    /**
     * Gets the appointment with given ID number
     * @param appointmentId ID number of the appointment
     * @return Appointment instance with ID number provided in parameter
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
