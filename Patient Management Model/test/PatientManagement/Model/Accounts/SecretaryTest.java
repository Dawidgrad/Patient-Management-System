/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.MedicineOrder;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.Medicines.TabletMedicine;
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davio
 */
public class SecretaryTest {
    private Secretary secretary;
    
    public SecretaryTest() {
        secretary = new Secretary("test", "test", "test", "test", "test");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetAppointmentRequests() {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();
        ArrayList<Appointment> expected = appointments.getStateList(Appointment.AppointmentState.REQUESTED);
        
        ArrayList<Appointment> result = secretary.getAppointmentRequests();
        
        assertEquals(result, expected);
    }

    @Test
    public void testProcessAppointmentRequest() {
        try
        {
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            Doctor doctor = new Doctor("test", "test", "test", "test", "test");
            
            AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
            appointmentList.addAppointment(doctor, patient, date, time);
            
            secretary.processAppointmentRequest(0);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testRemoveAppointmentRequest() {
        try
        {
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            Doctor doctor = new Doctor("test", "test", "test", "test", "test");
            
            AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
            appointmentList.addAppointment(doctor, patient, date, time);
            
            secretary.removeAppointmentRequest(1);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testCreateAppointment() {
        try
        {
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            Doctor doctor = new Doctor("test", "test", "test", "test", "test");
            
            secretary.createAppointment(doctor, patient, date, time);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testVerifyAccount() {
        try
        {
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
            
            AccountVerificationSingleton verificationList = AccountVerificationSingleton.getInstance();
            verificationList.addVerificationRequest(patient);
            
            secretary.verifyAccount(patient);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testTerminateAccount() {
        try
        {
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
            
            AccountTerminationSingleton terminationList = AccountTerminationSingleton.getInstance();
            terminationList.addTerminationRequest(patient);
            
            secretary.terminateAccount(patient);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testOrderMedicine() {
        try
        {
            
            StockSingleton stock = StockSingleton.getInstance();
            stock.createNewMedicine("name", "description", 10, 10, 10, StockSingleton.MedicineType.TABLET);
            Medicine medicine = stock.getMedicine(1);
            
            MedicineOrder order = new MedicineOrder(0, medicine, 10);
            
            secretary.orderMedicine(order);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testGiveMedicine() {
        boolean expected = true;
        boolean result = secretary.giveMedicine(1, 5);
        
        assertEquals(result, expected);
    }
    
}
