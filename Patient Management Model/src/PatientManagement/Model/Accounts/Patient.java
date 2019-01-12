/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Interfaces.Observable;
import PatientManagement.Model.Interfaces.Observer;
import PatientManagement.Model.Reviews.ReviewListSingleton;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Davio
 */
public class Patient extends Account implements Observer, Serializable
{
    public enum Gender { MALE, FEMALE }

    private Gender gender;
    private int age;
    private Appointment scheduledAppointment;
    private boolean appointmentJustApproved;
    
    public Patient(String name, String surname, String address, String idNumber, String password, int age, Gender gender)
    {
        super(name, surname, address, idNumber, password, AccountType.PATIENT);
        
        this.age = age;
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public boolean isAppointmentJustApproved() {
        return appointmentJustApproved;
    }

    public void setAppointmentJustApproved(boolean appointmentJustApproved) {
        this.appointmentJustApproved = appointmentJustApproved;
    }
    
    @Override
    public void update(Observable o) 
    {
        this.scheduledAppointment = (Appointment)o;
        appointmentJustApproved = true;
    }
    
    public void provideReview(Doctor doctor, String comment, int rating)
    {
        ReviewListSingleton reviewList = ReviewListSingleton.getInstance();
        reviewList.AddReview(this, doctor, comment, rating);
    }
    
    public void requestAppointment(Date date, Doctor doctor, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        
        appointments.addRequest(this, date, doctor, time);
    }
    
    public void completeAppointment()
    {
        scheduledAppointment = null;
        setAppointmentJustApproved(false);
    }
}
