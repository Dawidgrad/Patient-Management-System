/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

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
public class ReviewTest {
    private String comment;
    private double rating;
    private String doctorId;
    private String patientId;
    private Review review;
    
    public ReviewTest() {
        comment = "test comment";
        rating = 8;
        doctorId = "test";
        patientId = "test";
        
        review = new Review(comment, rating, doctorId, patientId);
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
    public void testGetComment() {
        String expected = comment;
        String result = review.getComment();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetRating() {
        double expected = rating;
        double result = review.getRating();
        
        assertEquals(expected, result, 0.1);
    }

    @Test
    public void testGetDoctorId() {
        String expected = doctorId;
        String result = review.getDoctorId();
        
        assertEquals(expected, result);
    }

    @Test
    public void testGetPatientId() {
        String expected = patientId;
        String result = review.getPatientId();
        
        assertEquals(expected, result);
    }
    
}
