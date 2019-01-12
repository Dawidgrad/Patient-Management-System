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
    
    public Rating(double rating)
    {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
