/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class ReviewListSingleton implements Serializable
{
    private static ReviewListSingleton uniqueInstance = null;
    private ArrayList<Review> reviewList;
    
    private ReviewListSingleton()
    {
        this.reviewList = new ArrayList<Review>();
    }
    
    /**
     * Method to get the instance of the singleton to prevent from creating multiple instances.
     * @return Returns instance of existing review list singleton
     */
    public static ReviewListSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ReviewListSingleton();
        }
        
        return uniqueInstance;
    }    
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException 
    {
        ois.defaultReadObject();
        uniqueInstance = this;
    }   
    
    private Object readResolve()
    {
        return uniqueInstance;
    }
    
    /**
     * Add a new review to the list of reviews.
     * @param patient Account instance of the patient that provided the review
     * @param doctor Account instance of the doctor that the review was provided for
     * @param comment Patient's comment in the review
     * @param rating Patient's rating of the doctor from 1 to 10
     */
    public void addReview(Patient patient, Doctor doctor, String comment, int rating)
    {
        Review review = new Review(comment, rating, doctor.getIdNumber(), patient.getIdNumber());
        
        reviewList.add(review);
    }
    
    /**
     * Gets the feedback for the doctor, gathering patient's review and creating new DoctorFeedback object. 
     * @param doctorId ID number of the doctor to provide feedback to
     * @return DoctorFeedback object instance with all patient comments and the average rating
     */
    public DoctorFeedback getFeedback(String doctorId)
    {
        ArrayList<Review> doctorReviews = new ArrayList<Review>();
        
        for (Review element : reviewList)
        {
            if (element.getDoctorId() == doctorId)
            {
                doctorReviews.add(element);
            }
        }
        
        DoctorFeedback feedback = new DoctorFeedback(doctorReviews);
        
        return feedback;
    }
}
