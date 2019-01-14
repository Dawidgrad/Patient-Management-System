/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class DoctorFeedback implements Serializable
{
    private double averageRating;
    private ArrayList<String> comments;
    private String administratorFeedback;
    
    public DoctorFeedback(ArrayList<Review> reviews)
    {
        averageRating = calculateAverageRating(reviews);
        comments = new ArrayList<String>();
        
        for (Review element : reviews)
        {
            comments.add(element.getComment());
        }
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    public void setAdministratorFeedback(String administratorFeedback) {
        this.administratorFeedback = administratorFeedback;
    }
    
    private double calculateAverageRating(ArrayList<Review> reviews)
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
