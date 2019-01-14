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
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.Reviews.ReviewListSingleton;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Davio
 */
public class Patient extends Account implements Observer, Serializable
{
    public enum Sex { MALE, FEMALE }

    private Sex sex;
    private int age;
    private Appointment scheduledAppointment;
    private boolean appointmentJustApproved;
    
    public Patient(String name, String surname, String address, String idNumber, String password, int age, Sex sex)
    {
        super(name, surname, address, idNumber, password, AccountType.PATIENT);
        
        this.age = age;
        this.sex = sex;
    }

    public Sex getSex() {
        return sex;
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
        reviewList.addReview(this, doctor, comment, rating);
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
    
    public void requestAccountTermination()
    {
        AccountTerminationSingleton accountTerminations = AccountTerminationSingleton.getInstance();
        accountTerminations.addTerminationRequest(this);
    }
    
    public boolean changePassword(String password, String newPassword, String newPasswordConfirm)
    {
        boolean changedPassword = false;
        
        if (password.equals(this.getPassword()) 
                && newPassword.equals(this.getPassword()) == false 
                && newPasswordConfirm.equals(newPassword))
        {
            this.setPassword(newPassword);
            changedPassword = true;
        }
        
        return changedPassword;
    }
}
