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

    /**
     * Sex of the patient, assigned at birth
     */
    public enum Sex { MALE, FEMALE }

    private Sex sex;
    private int age;
    private Appointment scheduledAppointment;
    private boolean appointmentJustApproved;
    
    /**
     * Constructor creating instance of patient account.
     * @param name Patient's first name
     * @param surname Patient's last name
     * @param address Patient's address
     * @param idNumber Patient's ID number
     * @param password Patient's password
     * @param age Patient's age
     * @param sex Patient's sex
     */
    public Patient(String name, String surname, String address, String idNumber, String password, int age, Sex sex)
    {
        super(name, surname, address, idNumber, password, AccountType.PATIENT);
        
        this.age = age;
        this.sex = sex;
    }

    /**
     * Returns patient's sex.
     * @return patient's sex
     */
    public Sex getSex() {
        return sex;
    }

    /**
     * Returns patient's age.
     * @return patient's age
     */
    public int getAge() {
        return age;
    }

    /**
     * Returns true / false value indicating if patient had his appointment just approved for notification purposes.
     * @return True / false value, true meaning that appointment just got approved by Secretary
     */
    public boolean isAppointmentJustApproved() {
        return appointmentJustApproved;
    }

    /**
     * Sets true / false value indicating if patient had his appointment just approved for notification purposes.
     * @param appointmentJustApproved Value to set. True meaning that appointment just got approved by Secretary
     */
    public void setAppointmentJustApproved(boolean appointmentJustApproved) {
        this.appointmentJustApproved = appointmentJustApproved;
    }
    
    /**
     * Observer method used for appointment notification purposes.
     * @param o Instance of the appointment that got approved
     */
    @Override
    public void update(Observable o) 
    {
        this.scheduledAppointment = (Appointment)o;
        appointmentJustApproved = true;
    }
    
    /**
     * Provides review for the doctor.
     * @param doctor Instance of the Doctor account to provide review for
     * @param comment Patient's feedback in the review
     * @param rating Patient's rating of the doctor
     */
    public void provideReview(Doctor doctor, String comment, int rating)
    {
        ReviewListSingleton reviewList = ReviewListSingleton.getInstance();
        reviewList.addReview(this, doctor, comment, rating);
    }
    
    /**
     * Creates appointment request that later needs confirmation from Secretary
     * @param date Date of the requested appointment
     * @param doctor Instance of doctor account involved in the appointment
     * @param time Time of the appointment
     */
    public void requestAppointment(Date date, Doctor doctor, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        
        appointments.addRequest(this, date, doctor, time);
    }
    
    /**
     * Processes the appointment assigned to Patient and changes its state to ARCHIVED
     */
    public void completeAppointment()
    {
        scheduledAppointment = null;
        setAppointmentJustApproved(false);
    }
    
    /**
     * Requests account termination from Secretary. 
     * The Secretary can then confirm the request and account is deleted.
     */
    public void requestAccountTermination()
    {
        AccountTerminationSingleton accountTerminations = AccountTerminationSingleton.getInstance();
        accountTerminations.addTerminationRequest(this);
    }
    
    /**
     * Changes password of the patient
     * @param password Patient's current password
     * @param newPassword Patient's new password
     * @param newPasswordConfirm Patient's new password confirmation
     * @return True / False value indicating if the password have been successfully changed
     */
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
    
    /**
     * Returns Patient's next scheduled appointment
     * @return Next scheduled appointment instance
     */
    public Appointment getScheduledAppointment()
    {
        return scheduledAppointment;
    }
}
