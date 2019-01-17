/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.Patient.Sex;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.Notes;
import PatientManagement.Model.Appointments.PrescriptionMedicine;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.Medicines.TabletMedicine;
import PatientManagement.Model.Reviews.DoctorFeedback;
import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Davio
 */
public class DoctorTest {
    private Doctor doctor;
    
    public DoctorTest() {
        doctor = new Doctor("test", "test", "test", "test", "test");
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
    public void testUpdateAdministratorFeedback() {
        try
        {
            doctor.updateAdministratorFeedback(new DoctorFeedback(new ArrayList<>()));
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }    
    }

    @Test
    public void testGetFeedback() {
        try
        {
            DoctorFeedback expected = new DoctorFeedback(new ArrayList<>());
            doctor.updateAdministratorFeedback(expected);
            DoctorFeedback result = doctor.getFeedback();
            assertEquals(result, expected);
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
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Sex.MALE);
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            doctor.createAppointment(doctor, patient, date, time);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }   
    }

    @Test
    public void testRequestMedicineOrder() {
        try
        {
            Medicine medicine = new TabletMedicine(1, "test", "test", 1, 1, 1);
            doctor.requestMedicineOrder(medicine, 0);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }  
    }

    @Test
    public void testCompleteAppointment() {
        try
        {
            Patient patient = new Patient("test", "test", "test", "test", "test", 20, Sex.MALE);
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            Appointment appointment = new Appointment(1, patient, date, doctor, time);
            
            Notes notes = new Notes("test notes");
            
            ArrayList<PrescriptionMedicine> prescription = new ArrayList<>();
            
            doctor.completeAppointment(appointment, notes, prescription);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }  
    }

    @Test
    public void testCreateNewMedicine() {
        try
        {
            doctor.createNewMedicine("name", "description", 0, 0, 0, StockSingleton.MedicineType.TABLET);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }  
    }
    
}
