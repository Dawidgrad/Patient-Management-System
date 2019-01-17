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
public class MedicineOrderTest {
    private int orderId;
    private Medicine medicine;
    private int amountToOrder;
    private MedicineOrder order;
    
    public MedicineOrderTest() {
        orderId = 1;
        medicine = new TabletMedicine(1, "name", "description", 0, 0, 0);
        amountToOrder = 10;
        
        order = new MedicineOrder(orderId, medicine, amountToOrder);
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
    public void testGetMedicine() {
        Medicine expected = medicine;
        Medicine result = order.getMedicine();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetAmountToOrder() {
        int expected = amountToOrder;
        int result = order.getAmountToOrder();
        
        assertEquals(result, expected);
    }

    @Test
    public void testGetOrderId() {
        int expected = orderId;
        int result = order.getOrderId();
        
        assertEquals(result, expected);
    }
    
}
