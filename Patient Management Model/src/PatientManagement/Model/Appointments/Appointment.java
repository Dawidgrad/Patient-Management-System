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

    /**
     * State of the appointment.
     */
    public static enum AppointmentState {

        /**
         * The appointment has been requested and need approval from Secretary
         */
        REQUESTED, 

        /**
         * The appointment has been approved and the Doctor needs to complete it
         */
        APPROVED, 

        /**
         * Old appointments serving as a history records
         */
        ARCHIVED}
    
    private int appointmentId;
    private AppointmentState state;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String time;
    private Prescription prescription;
    
    /**
     *
     * @param id
     * @param patient
     * @param date
     * @param doctor
     * @param time
     */
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
    
    /**
     *
     * @param id
     * @param patient
     * @param date
     * @param doctor
     * @param time
     * @param state
     */
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
    
    /**
     *
     * @return
     */
    public AppointmentState getState() {
        return state;
    }

    /**
     *
     * @return
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     *
     * @return
     */
    public String getPatientName() {
        return patient.getName() + " " + patient.getSurname();
    }

    /**
     *
     * @return
     */
    public String getDoctorName() {
        return doctor.getName() + " " + doctor.getSurname();
    }
    
    /**
     *
     * @return
     */
    public Patient getPatient()
    {
        return patient;
    }
    
    /**
     *
     * @return
     */
    public Doctor getDoctor()
    {
        return doctor;
    }

    /**
     *
     * @return
     */
    public String getDate() {
        String dateString = date.toString();
        int index = dateString.indexOf(" 00:00");
        dateString = dateString.substring(0, index);
        
        return dateString;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @return
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     *
     * @param state
     */
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
    
    /**
     *
     * @param o
     */
    @Override
    public void registerObserver(Observer o) 
    {
        this.patient = (Patient)o;
    }

    /**
     *
     */
    @Override
    public void notifyObserver() 
    {
        this.patient.update(this);
    }
    
    /**
     *
     */
    public void processRequest()
    {
        this.state = AppointmentState.APPROVED;
        notifyObserver();
    }
    
    /**
     *
     * @param notes
     * @param medicine
     */
    public void createPrescription(Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        this.prescription = new Prescription(notes, medicine);
    }
}
