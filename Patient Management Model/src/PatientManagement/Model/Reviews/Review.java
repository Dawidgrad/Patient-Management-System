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
public class Review implements Serializable
{
    private Comment comment;
    private Rating rating;
    private String doctorId;
    private String patientId;
    
    /**
     * Creates instance of the review.
     * @param comment Patient comment
     * @param rating Patient rating score from 1 to 10
     * @param doctorId ID number of the doctor that the review was provided for
     * @param patientId ID number of the patient providing the review
     */
    public Review(String comment, double rating, String doctorId, String patientId)
    {
        this.comment = new Comment(comment);
        this.rating = new Rating(rating);
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    /**
     * Gets the patient's comment.
     * @return Patient's text comment
     */
    public String getComment() {
        return comment.getCommentText();
    }

    /**
     * Gets the patient's rating score.
     * @return Patient's rating score from 1 to 10
     */
    public double getRating() {
        return rating.getRating();
    }

    /**
     * Gets the ID number of a doctor that the review was provided for.
     * @return Doctor's ID number
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Gets the ID number of a patient that wrote the review.
     * @return Patient's ID number
     */
    public String getPatientId() {
        return patientId;
    }
}
