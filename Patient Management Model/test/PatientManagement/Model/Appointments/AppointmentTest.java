/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Appointments.Appointment.AppointmentState;
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
public class AppointmentTest {
    private Appointment appointment;
    private Doctor doctor;
    private Patient patient;
    private Date date;
    private String time;
    private Prescription prescription;
    
    public AppointmentTest() {
        doctor = new Doctor("test", "test", "test", "test", "test");
        patient = new Patient("test", "test", "test", "test", "test", 20, Patient.Sex.MALE);
        date = new Date(2019, 1, 17);
        time = "10:30 AM";
        
        appointment = new Appointment(1, patient, date, doctor, time, AppointmentState.APPROVED);
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
    public void testGetState() {
        AppointmentState expected = AppointmentState.APPROVED;
        AppointmentState result = appointment.getState();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetAppointmentId() {
        int expected = 1;
        int result = appointment.getAppointmentId();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetPatientName() {
        String expected = "test test";
        String result = appointment.getPatientName();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetDoctorName() {
        String expected = "test test";
        String result = appointment.getDoctorName();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetPatient() {
        Patient expected = patient;
        Patient result = appointment.getPatient();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetDoctor() {
        Doctor expected = doctor;
        Doctor result = appointment.getDoctor();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetDate() {
        String expected = "Mon Feb 17";
        String result = appointment.getDate();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetTime() {
        String expected = "10:30 AM";
        String result = appointment.getTime();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetPrescription() {
        try
        {
            appointment.getPrescription();
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testSetState() {
        try
        {
            appointment.setState(AppointmentState.ARCHIVED);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testRegisterObserver() {
        try
        {
            appointment.registerObserver(patient);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testNotifyObserver() {
        try
        {
            appointment.notifyObserver();
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testProcessRequest() {
        try
        {
            appointment.processRequest();
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }

    @Test
    public void testCreatePrescription() {
        try
        {
            appointment.createPrescription(new Notes("notes"), new ArrayList<PrescriptionMedicine>());
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }
    
}
