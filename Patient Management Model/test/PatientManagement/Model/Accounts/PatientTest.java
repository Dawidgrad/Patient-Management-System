/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Appointments.Appointment;
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
public class PatientTest {
    private Patient patient;
    
    public PatientTest() {
        patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
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
    public void testGetSex() {
        Patient.Sex expected = Patient.Sex.MALE;
        Patient.Sex result = patient.getSex();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetAge() {
        int expected = 20;
        int result = patient.getAge();

        assertEquals(result, expected);
    }

    @Test
    public void testIsAppointmentJustApproved() {
        boolean expected = false;
        
        patient.setAppointmentJustApproved(false);
        boolean result = patient.isAppointmentJustApproved();
        
        assertEquals(result, expected);
    }

    @Test
    public void testSetAppointmentJustApproved() {
        try
        {
            patient.setAppointmentJustApproved(false);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testUpdate() {
        try
        {
            Doctor doctor = new Doctor("test", "test", "test", "test", "test");
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            Appointment appointment = new Appointment(1, patient, date, doctor, time);
            
            patient.update(appointment);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testProvideReview() {
        try
        {
            Doctor doctor = new Doctor("test", "test", "test", "test", "test");
            patient.provideReview(doctor, "test comment", 5);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testRequestAppointment() {
        try
        {
            Doctor doctor = new Doctor("test", "test", "test", "test", "test");
            Date date = new Date(2019, 1, 17);
            String time = "10:30 AM";
            
            patient.requestAppointment(date, doctor, time);
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
            patient.completeAppointment();
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testRequestAccountTermination() {
        try
        {
            patient.requestAccountTermination();
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testChangePassword() {
        boolean expected = true;
        boolean result = patient.changePassword("test", "newTest", "newTest");
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetScheduledAppointment() {
        Doctor doctor = new Doctor("test", "test", "test", "test", "test");
        Date date = new Date(2019, 1, 17);
        String time = "10:30 AM";
        Appointment expected = new Appointment(1, patient, date, doctor, time);
        patient.update(expected);
        
        Appointment result = patient.getScheduledAppointment();
        
        assertEquals(result, expected);
    }
    
}
