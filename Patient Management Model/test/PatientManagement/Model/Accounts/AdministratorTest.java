/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Reviews.DoctorFeedback;
import PatientManagement.Model.Reviews.Review;
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
public class AdministratorTest {
    private Administrator admin;
    
    public AdministratorTest() {
        admin = new Administrator("test", "test", "test", "test", "test");
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
    public void testCreateNewAccount()
    {
        boolean expected = true;
        boolean result = admin.createNewAccount("test", "test", "test", "test", "test", AccountListSingleton.AccountType.DOCTOR);
        assertEquals(expected, result);
    }

    @Test
    public void testRemoveAccount() 
    {
        try
        {
            Administrator dummy = new Administrator("dummy", "dummy", "dummy", "dummy", "dummy");
            admin.removeAccount(dummy);
        }
        catch (Exception ex)
        {
            fail("The method have thrown exception!");
        }
    }

    @Test
    public void testGiveDoctorFeedback() 
    {
        try
        {
            Doctor doctor = new Doctor("dummy", "dummy", "dummy", "dummy", "dummy");
            String feedback = "";
            admin.giveDoctorFeedback(doctor, feedback);
        }
        catch (Exception ex)
        {
            fail("The method have thrown exception!");
        }
    }
    
}
