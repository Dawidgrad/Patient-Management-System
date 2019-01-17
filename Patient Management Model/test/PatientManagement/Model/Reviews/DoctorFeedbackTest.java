/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

import java.util.ArrayList;
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
public class DoctorFeedbackTest {
    private ArrayList<Review> reviews;
    private String administratorFeedback;
    private DoctorFeedback feedback;
    
    public DoctorFeedbackTest() {
        reviews = new ArrayList<>();
        reviews.add(new Review("review comment", 5, "test", "test"));
        administratorFeedback = "test feedback";
        
        feedback = new DoctorFeedback(reviews);
        feedback.setAdministratorFeedback(administratorFeedback);
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
    public void testGetAverageRating() {
        double expected = 5;
        double result = feedback.getAverageRating();
        
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testGetComments() {
        boolean equal = true;
        
        ArrayList<String> expected = new ArrayList<>();
        expected.add("review comment");
        
        ArrayList<String> result = feedback.getComments();
        
        for (int i = 0; i < result.size(); i++)
        {
            if (result.get(i) != expected.get(i))
            {
                equal = false;
            }
        }
        
        if (equal == false)
        {
            fail("The test have failed!");
        }
    }

    @Test
    public void testGetAdministratorFeedback() {
        String expected = administratorFeedback;
        String result = feedback.getAdministratorFeedback();
        
        assertEquals(expected, result);
    }

    @Test
    public void testSetAdministratorFeedback() {
        try
        {
            feedback.setAdministratorFeedback(administratorFeedback);
        }
        catch (Exception ex)
        {
            fail("The method have thrown an exception!");
        }    
    }
    
}
