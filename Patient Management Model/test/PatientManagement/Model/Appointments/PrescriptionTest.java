/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import java.util.ArrayList;
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
public class PrescriptionTest {
    private Prescription prescription;
    private Notes notes;
    private ArrayList<PrescriptionMedicine> medicine;
    
    public PrescriptionTest() {
        notes = new Notes("test notes");
        medicine = new ArrayList<>();
        
        prescription = new Prescription(notes, medicine);
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
    public void testGetNotes() {
        Notes expected = notes;
        Notes result = prescription.getNotes();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetMedicine() {
        ArrayList<PrescriptionMedicine> expected = medicine;
        ArrayList<PrescriptionMedicine> result = prescription.getMedicine();
        
        assertEquals(result, expected);
    }
    
}
