/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Medicines.MedicineOrder;
import PatientManagement.Model.Medicines.OrderRequestSingleton;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Davio
 */
public class Secretary extends Account implements Serializable
{

    /**
     * Constructor creating Secretary instance.
     * @param name Secretary's first name
     * @param surname Secretary's last name
     * @param address Secretary's address
     * @param idNumber Secretary's ID number
     * @param password Secretary's password
     */
    public Secretary(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.SECRETARY);
    }
    
    /**
     * Provides the list of appointment requests submitted by the patients.
     * @return List of appointment requests
     */
    public ArrayList<Appointment> getAppointmentRequests()
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        
        return appointments.getStateList(Appointment.AppointmentState.REQUESTED);
    }
    
    /**
     * Confirms appointment request by changing the state of the appointment between patient and doctor to Approved.
     * @param appointmentId ID of an Appointment instance to confirm
     */
    public void processAppointmentRequest(int appointmentId)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.approveRequest(appointmentId);
    }
    
    /**
     * Removes appointment request from the appointment list.
     * @param appointmentId ID of an Appointment instance to remove
     */
    public void removeAppointmentRequest(int appointmentId)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.removeRequest(appointmentId);
    }
    
    /**
     * Creates an appointment between patient and doctor at provided date and time.
     * @param doctor Instance of the Doctor account to link appointment to
     * @param patient Instance of the Patient account to link appointment to
     * @param date Date of the appointment
     * @param time Time of the appointment
     */
    public void createAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.addAppointment(doctor, patient, date, time);
    }
    
    /**
     * Verifies the account request submitted by new patient, consequently creating a new entry in account list.
     * @param patient Instance of the patient account to verify and add to account list.
     */
    public void verifyAccount(Patient patient)
    {
        AccountVerificationSingleton verificationList = AccountVerificationSingleton.getInstance();
        verificationList.verifyAccount(patient);
    }
     
    /**
     * Terminates the user account, consequently removing them from account list.
     * @param patient Instance of the patient account to remove
     */
    public void terminateAccount(Patient patient)
    {
        AccountTerminationSingleton terminationList = AccountTerminationSingleton.getInstance();
        terminationList.removeAccount(patient);
    }
    
    /**
     * Orders a specified medicine in specified amount.
     * @param order Object holding both instance of medicine to order and the amount that needs to be ordered
     */
    public void orderMedicine(MedicineOrder order)
    {
        StockSingleton medicineList = StockSingleton.getInstance();
        OrderRequestSingleton orderRequests = OrderRequestSingleton.getInstance();
        
        medicineList.orderMedicine(order.getMedicine().getMedicineId(), order.getAmountToOrder());
        orderRequests.processRequest(order);
    }
}
