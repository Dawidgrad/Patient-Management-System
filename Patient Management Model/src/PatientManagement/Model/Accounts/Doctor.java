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
            
    public Doctor(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.DOCTOR);
    }
    
    public void updateAdministratorFeedback(DoctorFeedback feedback)
    {
        this.feedback = feedback;
    }
    
    public void createAppointment(Doctor doctor, Patient patient, Date date, String time)
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.addAppointment(doctor, patient, date, time);
    }
    
    public void requestMedicineOrder(Medicine medicine, int amountToOrder)
    {
        OrderRequestSingleton orders = OrderRequestSingleton.getInstance();
        orders.addOrderRequest(medicine, amountToOrder);
    }
    
    public void completeAppointment(Appointment appointment, Notes notes, ArrayList<PrescriptionMedicine> medicine)
    {
        appointment.createPrescription(notes, medicine);
        
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        appointments.completeAppointment(appointment);
    }
}
