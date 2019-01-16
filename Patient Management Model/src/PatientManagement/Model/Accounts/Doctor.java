/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Appointments.Notes;
import PatientManagement.Model.Appointments.Prescription;
import PatientManagement.Model.Appointments.PrescriptionMedicine;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.OrderRequestSingleton;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.Reviews.DoctorFeedback;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Davio
 */
public class Doctor extends Account implements Serializable
{
    private DoctorFeedback feedback;
            
    /**
     * Creates the instance of Doctor account
     * @param name Doctor's first name
     * @param surname Doctor's last name
     * @param address Doctor's address
     * @param idNumber Doctor's ID number
     * @param password Doctor's password
     */
    public Doctor(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.DOCTOR);
    }
    
    /**
     * Updates doctor's feedback from administrator.
     * @param feedback Administrator's feedback instance
     */
    public void updateAdministratorFeedback(DoctorFeedback feedback)
    {
        this.feedback = feedback;
    }

    /**
     * Returns doctor's feedback that is provided by Administrator.
     * @return Instance of doctor's feedback
     */
    public DoctorFeedback getFeedback() 
    {
        return feedback;
    }
    
    /**
     * Creates an appointment between logged in Doctor and provided patient
     * @param doctor Instance of the logged in Doctor
     * @param patient Instance of the patient involved
     * @param date Date of the appointment
     * @param time Time of the appointment
     */
    public void createAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.addAppointment(doctor, patient, date, time);
    }
    
    /**
     * Requests medicine to be ordered by one of the Secretaries
     * @param medicine Medicine to be ordered
     * @param amountToOrder Amount of the medicine to be ordered
     */
    public void requestMedicineOrder(Medicine medicine, int amountToOrder)
    {
        OrderRequestSingleton orders = OrderRequestSingleton.getInstance();
        orders.addOrderRequest(medicine, amountToOrder);
    }
    
    /**
     * Completes the appointment and changes the state to ARCHIVED
     * @param appointment Instance of the appointment to complete
     * @param notes Notes to assign to the appointment
     * @param medicine Medicine prescribed by the doctor on the appointment
     */
    public void completeAppointment(Appointment appointment, Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        appointment.createPrescription(notes, medicine);
        
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.completeAppointment(appointment);
    }
    
    /**
     * Method utilising factory to create new instance of medicine.
     * @param name Medicine name
     * @param description Medicine description
     * @param quantity Medicine quantity in pack / bottle
     * @param price Medicine price in GBP
     * @param amount Medicine amount in stock
     * @param type Type of the medicine
     */
    public void createNewMedicine(String name, String description, int quantity, float price, int amount, StockSingleton.MedicineType type) 
    {
        StockSingleton stock = StockSingleton.getInstance();
        stock.createNewMedicine(name, description, quantity, price, amount, type);
    }
}
