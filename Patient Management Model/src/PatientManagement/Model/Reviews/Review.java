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
    
    public Review(String comment, double rating, String doctorId, String patientId)
    {
        this.comment = new Comment(comment);
        this.rating = new Rating(rating);
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public String getComment() {
        return comment.getCommentText();
    }

    public double getRating() {
        return rating.getRating();
    }

    public String getDoctorId() {
        return doctorId;
    }

    public String getPatientId() {
        return patientId;
    }
}
