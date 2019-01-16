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
public class Comment implements Serializable
{
    private String commentText;

    /**
     * Creates instance of the review comment.
     * @param comment Text of the comment
     */
    public Comment(String comment)
    {
        this.commentText = comment;
    }
    
    /**
     * Gets the comment text
     * @return Comment text 
     */
    public String getCommentText() {
        return commentText;
    }
}
