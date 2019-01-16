/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class Rating implements Serializable
{
    private double rating;
    
    /**
     * Creates instance of the rating.
     * @param rating Rating score from 1 to 10
     */
    public Rating(double rating)
    {
        this.rating = rating;
    }

    /**
     * Gets the rating score.
     * @return Rating score from 1 to 10
     */
    public double getRating() {
        return rating;
    }
}
