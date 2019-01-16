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
    
    /**
     * Creates instance of the feedback for the doctor.
     * @param reviews List of patient reviews
     */
    public DoctorFeedback(ArrayList<Review> reviews)
    {
        averageRating = calculateAverageRating(reviews);
        comments = new ArrayList<String>();
        
        for (Review element : reviews)
        {
            comments.add(element.getComment());
        }
    }

    /**
     * Gets the average rating from the user reviews
     * @return Average rating between 1 to 10
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * Gets patient comments
     * @return List of patient comments
     */
    public ArrayList<String> getComments() {
        return comments;
    }

    /**
     * Gets feedback provided by an administrator for the doctor.
     * @return Text feedback for the doctor
     */
    public String getAdministratorFeedback() {
        return administratorFeedback;
    }

    /**
     * Sets feedback provided by an administrator for the doctor.
     * @param administratorFeedback Text feedback for the doctor
     */
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
