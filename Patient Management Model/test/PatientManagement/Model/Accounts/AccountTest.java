/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

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
public class AccountTest {
    private String name;
    private String surname;
    private String address;
    private String idNumber;
    private String password;
    private AccountListSingleton.AccountType accountType;
    private AccountImpl account;
    
    public AccountTest() {
        name = "test name";
        surname = "test surname";
        address = "test address";
        idNumber = "test idNumber";
        password = "test password";
        accountType = AccountListSingleton.AccountType.SECRETARY;
        
        account = new AccountImpl();
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
        String result = account.getName();
        
        assertEquals(expected, result);
    }

    @Test
    public void testSetName() {
        try
        {
            account.setName("New name");
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }   
    }

    @Test
    public void testGetSurname() {
        String expected = surname;
        String result = account.getSurname();
        
        assertEquals(expected, result);
    }

    @Test
    public void testSetSurname() {
        try
        {
            account.setSurname("New surname");
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }   
    }

    @Test
    public void testGetAddress() {
        String expected = address;
        String result = account.getAddress();
        
        assertEquals(expected, result);
    }

    @Test
    public void testSetAddress() {
        try
        {
            account.setAddress("New address");
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testGetIdNumber() {
        String expected = idNumber;
        String result = account.getIdNumber();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetPassword() {
        String expected = password;
        String result = account.getPassword();
        
        assertEquals(expected, result);
    }

    @Test
    public void testSetPassword() {
        try
        {
            account.setPassword("New password");
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        } 
    }

    @Test
    public void testGetAccountType() {
        AccountListSingleton.AccountType expected = accountType;
        AccountListSingleton.AccountType result = account.getAccountType();
        
        assertEquals(expected, result);
    }

    public class AccountImpl extends Account {

        public AccountImpl() {
            super(name, surname, address, idNumber, password, accountType);
        }
    }
    
}
