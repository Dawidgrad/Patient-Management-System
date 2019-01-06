/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Reviews;

/**
 *
 * @author Davio
 */
public class Comment 
{
    private String commentText;

    public Comment(String comment)
    {
        this.commentText = comment;
    }
    
    public String getCommentText() {
        return commentText;
    }
}
