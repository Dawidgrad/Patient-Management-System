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

    public AppointmentState getState() {
        return state;
    }
    
    public Appointment(Patient patient, Date date)
    {
        RegisterObserver((Observer)patient);
        this.date = date;
        this.state = AppointmentState.REQUESTED;
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
