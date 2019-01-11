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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author Davio
 */
public class Appointment implements Observable
{

    public static enum AppointmentState {REQUESTED, APPROVED}
    
    private AppointmentState state;
    private Patient patient;
    private Doctor doctor;
    private Date date;
    private String time;

    public AppointmentState getState() {
        return state;
    }
    
    public Appointment(Patient patient, Date date, Doctor doctor, String time)
    {
        RegisterObserver((Observer)patient);
        this.doctor = doctor;
        this.state = AppointmentState.REQUESTED;
        
        if (ValidateDateTime(date, time))
        {
            this.date = date;
            this.time = time;
        }
        else
        {
            throw new RuntimeException();
        }
    }
    
    private boolean ValidateDateTime(Date newDate, String newTime)
    {
        boolean correct = false;
        LocalDateTime currentLdt = LocalDateTime.now();
        Date currentDate = Date.from(currentLdt.atZone(ZoneId.systemDefault()).toInstant());
        
        if (newDate.before(currentDate))
        {
            String hour, minute;
            hour = newTime.substring(0, 2);
            minute = newTime.substring(3, 5);
            
            if (currentLdt.getHour() < Integer.parseInt(hour) &&
                    currentLdt.getMinute() < Integer.parseInt(minute))
            {
                correct = true;
            }
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
    
    public void ProcessRequest(Doctor doctor)
    {
        this.state = AppointmentState.APPROVED;
        this.doctor = doctor;
        NotifyObserver();
    }
}
