/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class DoctorFeedback 
{
    private double averageRating;
    private String comments;

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    
    public DoctorFeedback(ArrayList<Review> reviews)
    {
        averageRating = CalculateAverageRating(reviews);
    }
    
    private double CalculateAverageRating(ArrayList<Review> reviews)
    {
        double counter = 0;
        double sum = 0;
        
        for (Review element : reviews)
        {
            sum += element.getRating();
            counter++;
        }
        
        double average = sum / counter;
        
        return average;
    }
    
    
}
