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
import java.util.Date;

/**
 *
 * @author Davio
 */
public class Appointment implements Observable, Serializable
{

    public static enum AppointmentState {REQUESTED, APPROVED}
    
    private int appointmentId;
    private AppointmentState state;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String time;

    public AppointmentState getState() {
        return state;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public String getPatientName() {
        return patient.getName();
    }

    public String getDoctorName() {
        return doctor.getName();
    }

    public String getDate() {
        return date.toString();
    }

    public String getTime() {
        return time;
    }
    
    public Appointment(int id, Patient patient, Date date, Doctor doctor, String time)
    {
        this.appointmentId = id;
        RegisterObserver((Observer)patient);
        this.doctor = doctor;
        this.state = AppointmentState.REQUESTED;
        
        if (ValidateDate(date, time))
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
        RegisterObserver((Observer)patient);
        this.doctor = doctor;
        ProcessRequest();
        
        if (ValidateDate(date, time))
        {
            this.date = date;
            this.time = time;
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
    private boolean ValidateDate(Date newDate, String newTime)
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
    public void RegisterObserver(Observer o) 
    {
        this.patient = (Patient)o;
    }

    @Override
    public void NotifyObserver() 
    {
        this.patient.Update(this);
    }
    
    public void ProcessRequest()
    {
        this.state = AppointmentState.APPROVED;
        NotifyObserver();
    }
}
