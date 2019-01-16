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
     * Creates new instance of Appointment.
     * @param id ID of the appointment
     * @param patient Instance of Patient account assigned to appointment
     * @param date Date of the appointment
     * @param doctor Instance of Doctor account assigned to appointment
     * @param time Time of the appointment
     * @param state State of the appointment
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
     * Gets the state of the appointment.
     * @return Current state of the appointment
     */
    public AppointmentState getState() {
        return state;
    }

    /**
     * Gets the ID of the appointment.
     * @return ID of the appointment
     */
    public int getAppointmentId() {
        return appointmentId;
    }

    /**
     * Gets the name of the patient assigned to this instance of appointment.
     * @return Name of the patient
     */
    public String getPatientName() {
        return patient.getName() + " " + patient.getSurname();
    }

    /**
     * Gets the name of the doctor assigned to this instance of appointment.
     * @return Name of the doctor
     */
    public String getDoctorName() {
        return doctor.getName() + " " + doctor.getSurname();
    }
    
    /**
     * Gets the instance of the patient account assigned to this appointment.
     * @return Instance of the patient account
     */
    public Patient getPatient()
    {
        return patient;
    }
    
    /**
     * Gets the instance of the patient account assigned to this appointment.
     * @return Instance of the doctor account
     */
    public Doctor getDoctor()
    {
        return doctor;
    }

    /**
     * Gets the date of the appointment
     * @return Scheduled date of the appointment
     */
    public String getDate() {
        String dateString = date.toString();
        int index = dateString.indexOf(" 00:00");
        dateString = dateString.substring(0, index);
        
        return dateString;
    }

    /**
     * Gets the time of the appointment
     * @return Scheduled time of the appointment
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the doctor's prescription assigned to the appointment
     * @return Doctor's prescription including Medicine, dosages and notes
     */
    public Prescription getPrescription() {
        return prescription;
    }

    /**
     * Sets the state of the appointment
     * @param state State to which appointment will be changed
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
     * Observer pattern method, used for notification purposes. 
     * Saves the instance of Patient account assigned to the appointment.
     * @param o Patient account instance
     */
    @Override
    public void registerObserver(Observer o) 
    {
        this.patient = (Patient)o;
    }

    /**
     * Observer pattern method, used for notification purposes. 
     * Informs the Patient about the approval of the appointment.
     */
    @Override
    public void notifyObserver() 
    {
        this.patient.update(this);
    }
    
    /**
     * Changes the state of appointment to APPROVED, at the same time notifying the user about that change.
     */
    public void processRequest()
    {
        this.state = AppointmentState.APPROVED;
        notifyObserver();
    }
    
    /**
     * Creates prescription for the appointment.
     * @param notes Doctor's notes written at the appointment
     * @param medicine Medicine prescribed by doctor with dosage information
     */
    public void createPrescription(Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        this.prescription = new Prescription(notes, medicine);
    }
}
