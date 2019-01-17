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
public class RatingTest {
    private double rating;
    private Rating ratingObject;
    
    public RatingTest() {
        rating = 8;
        ratingObject = new Rating(rating);
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
    public void testGetRating() {
        double expected = rating;
        double result = ratingObject.getRating();
        
        assertEquals(expected, result, 0.1);
    }
    
}
