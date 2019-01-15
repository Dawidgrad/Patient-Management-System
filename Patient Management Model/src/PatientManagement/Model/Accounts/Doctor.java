/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.OrderRequestSingleton;
import PatientManagement.Model.Reviews.DoctorFeedback;
import PatientManagement.Model.Reviews.ReviewListSingleton;
import java.io.Serializable;
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
}
