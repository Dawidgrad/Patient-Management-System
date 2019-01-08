/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class ReviewListSingleton 
{
    private static ReviewListSingleton uniqueInstance = null;
    private static ArrayList<Review> reviewList;
    
    private ReviewListSingleton()
    {
        this.reviewList = new ArrayList<Review>();
    }
    
    public static ReviewListSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new ReviewListSingleton();
        }
        
        return uniqueInstance;
    }
    
    public void AddReview(Patient patient, Doctor doctor, String comment, int rating)
    {
        Review review = new Review(comment, rating, doctor.getIdNumber(), patient.getIdNumber());
        
        reviewList.add(review);
    }
    
    public DoctorFeedback GetFeedback(String doctorId)
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
