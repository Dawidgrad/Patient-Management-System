/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Interfaces.Observable;
import PatientManagement.Model.Interfaces.Observer;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Davio
 */
public class Appointment implements Observable, Serializable
{

    public static enum AppointmentState {REQUESTED, APPROVED, ARCHIVED}
    
    private int appointmentId;
    private AppointmentState state;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String time;
    private Prescription prescription;
    
    public Appointment(int id, Patient patient, Date date, Doctor doctor, String time)
    {
        this.appointmentId = id;
        registerObserver((Observer)patient);
        this.doctor = doctor;
        this.state = AppointmentState.REQUESTED;
        
        if (validateDate(date, time))
        {
            this.date = date;
            this.time = time;
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
    public Appointment(int id, Patient patient, Date date, Doctor doctor, String time, AppointmentState state)
    {
        this.appointmentId = id;
        registerObserver((Observer)patient);
        this.doctor = doctor;
        processRequest();
        
        if (validateDate(date, time))
        {            
            this.date = date;
            this.time = time;
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
    public AppointmentState getState() {
        return state;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getPatientName() {
        return patient.getName() + " " + patient.getSurname();
    }

    public String getDoctorName() {
        return doctor.getName() + " " + doctor.getSurname();
    }
    
    public Patient getPatient()
    {
        return patient;
    }
    
    public Doctor getDoctor()
    {
        return doctor;
    }

    public String getDate() {
        String dateString = date.toString();
        int index = dateString.indexOf(" 00:00");
        dateString = dateString.substring(0, index);
        
        return dateString;
    }

    public String getTime() {
        return time;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setState(AppointmentState state) {
        this.state = state;
    }
    
    private boolean validateDate(Date newDate, String newTime)
    {
        boolean correct = false;
        LocalDateTime currentLdt = LocalDateTime.now();
        Date currentDate = Date.from(currentLdt.atZone(ZoneId.systemDefault()).toInstant());
        
        if (newDate.after(currentDate))
        {
            correct = true;
        }
        
        return correct;
    }
    
    @Override
    public void registerObserver(Observer o) 
    {
        this.patient = (Patient)o;
    }

    @Override
    public void notifyObserver() 
    {
        this.patient.update(this);
    }
    
    public void processRequest()
    {
        this.state = AppointmentState.APPROVED;
        notifyObserver();
    }
    
    public void createPrescription(Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        this.prescription = new Prescription(notes, medicine);
    }
}
