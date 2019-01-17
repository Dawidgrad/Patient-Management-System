/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Appointments;

import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.TabletMedicine;
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
public class PrescriptionMedicineTest {
    private Medicine medicine;
    private int quantity;
    private String dosage;
    private PrescriptionMedicine prescriptionMedicine;
    
    public PrescriptionMedicineTest() {
        medicine = new TabletMedicine(1, "test", "test", 1, 1, 1);
        quantity = 10;
        dosage = "test dosage";
        
        prescriptionMedicine = new PrescriptionMedicine(medicine, quantity, dosage);
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
    public void testGetDetails() {
        String expected = "Medicine: test Quantity: 10 tablets Dosage: test dosage";
        String result = prescriptionMedicine.getDetails();
        
        assertEquals(result, expected);
    }
    
}
