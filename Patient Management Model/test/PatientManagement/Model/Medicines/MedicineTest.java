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
public class MedicineTest {
    private int medicineId;
    private String name;
    private String description;
    private int quantity;
    private float price;
    private int amountInStock;
    private String quantityInf;
    private MedicineImpl medicine;
    
    public MedicineTest() {
        medicineId = 0;
        name = "test name";
        description = "test description";
        quantity = 10;
        price = 5.5F;
        amountInStock = 20;
        quantityInf = "test quantity information";
        
        medicine = new MedicineImpl();
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
    public void testGetName() {
        String expected = name;
        String result = medicine.getName();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetDescription() {
        String expected = description;
        String result = medicine.getDescription();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetAmountInStock() {
        int expected = amountInStock;
        int result = medicine.getAmountInStock();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetQuantity() {
        int expected = quantity;
        int result = medicine.getQuantity();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetPrice() {
        double expected = price;
        double result = medicine.getPrice();
        
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testSetDescription() {
        try
        {
            medicine.setDescription("new description");
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }    
    }

    @Test
    public void testSetPrice() {
        try
        {
            medicine.setPrice(3.5F);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }    
    }

    @Test
    public void testGetMedicineId() {
        int expected = medicineId;
        int result = medicine.getMedicineId();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetQuantityInformation() {
        String expected = quantityInf;
        String result = medicine.getQuantityInformation();
        
        assertEquals(expected, result);
    }

    @Test
    public void testAddToStock() {
        int initialAmount = medicine.getAmountInStock();
        int amountToAdd = 5;
        
        medicine.addToStock(amountToAdd);
        
        assertEquals(amountToAdd + initialAmount, medicine.getAmountInStock());
    }

    @Test
    public void testGetFromStock() {
        int initialAmount = medicine.getAmountInStock();
        int amountToTakeAway = 500;
        
        medicine.getFromStock(amountToTakeAway);
        
        if (initialAmount - amountToTakeAway < 0)
        {
            assertEquals(initialAmount, medicine.getAmountInStock());
        }
        else
        {
            assertEquals(initialAmount - amountToTakeAway, medicine.getAmountInStock());
        }
        
    }

    public class MedicineImpl extends Medicine {

        public MedicineImpl() {
            super(medicineId, name, description, quantity, price, amountInStock);
        }

        public String getQuantityInformation() {
            return quantityInf;
        }
    }
    
}
