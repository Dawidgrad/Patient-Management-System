/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

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
public class CapsuleMedicineTest {
    private CapsuleMedicine medicine;
    
    public CapsuleMedicineTest() {
        medicine = new CapsuleMedicine(1, "name", "description", 10, 10, 10);
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
    public void testGetQuantityInformation() {
        String expected = " capsules";
        String result = medicine.getQuantityInformation();
        
        assertEquals(result, expected);
    }
    
}
