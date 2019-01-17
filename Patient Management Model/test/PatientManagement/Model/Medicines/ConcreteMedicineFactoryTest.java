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
public class ConcreteMedicineFactoryTest {
    private ConcreteMedicineFactory factory;
    
    public ConcreteMedicineFactoryTest() {
        factory = new ConcreteMedicineFactory();
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
    public void testCreateNewMedicine() {
        try
        {
            factory.createNewMedicine(1, "name", "description", 0, 0, 0, StockSingleton.MedicineType.TABLET);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }
    }
    
}
