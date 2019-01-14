/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorReviewView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Reviews.DoctorFeedback;
import PatientManagement.Model.Reviews.ReviewListSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class DoctorReviewController 
{
    private DoctorReviewView view;
    private Patient model;
    private Doctor selectedDoctor = null;
    
    public DoctorReviewController(DoctorReviewView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addSelectDoctorListener(new SelectDoctor());
        this.view.addSubmitReviewListener(new SubmitReview());
        
        refreshDoctorsJList();
    }
    
        
    private void refreshDoctorsJList()
    {
        ArrayList<Account> doctorList = getDoctorList();
        populateDoctorJList(doctorList);
    }

    private ArrayList<Account> getDoctorList()
    {
        AccountListSingleton accounts = AccountListSingleton.getInstance();

        ArrayList<Account> doctorList = new ArrayList<Account>();

        doctorList.addAll(accounts.getAccountTypeList(AccountListSingleton.AccountType.DOCTOR));

        return doctorList;
    }

    private void populateDoctorJList(ArrayList<Account> doctorList)
    {
        ArrayList<String> doctorStringList = new ArrayList<String>();

        for (Account doctor : doctorList)
        {
            doctorStringList.add(doctor.getIdNumber() + " Name: " + doctor.getName() + " " + doctor.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : doctorStringList)
        {
            model.addElement(element);
        }

        view.setLstDoctors(model);
    }
    
    public class SelectDoctor implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            updateSelectedDoctor();
            updateDoctorReviews();
        }
        
        private void updateSelectedDoctor()
        {
            String details = view.getLstDoctors().getSelectedValue();
            AccountListSingleton accountList = AccountListSingleton.getInstance();
            
            String idNumber;
            int x = details.indexOf("Name:");

            idNumber = details.substring(0, x-1);
            selectedDoctor = (Doctor)accountList.getAccount(idNumber);
        }
        
        private void updateDoctorReviews()
        {
            ReviewListSingleton reviewList = ReviewListSingleton.getInstance();
            
            // Updates feedback for selected doctor
            DoctorFeedback feedback = reviewList.getFeedback(selectedDoctor.getIdNumber());
            
            // update UI
            view.setLblRating(Double.toString(feedback.getAverageRating()));
            
            String patientComments = "";

            for (String element : feedback.getComments())
            {
                patientComments += element + System.lineSeparator() + System.lineSeparator();
            }

            view.setTxtComments(patientComments);
        }
    }
    
    public class SubmitReview implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String comment = view.getTxtComment();
            int rating = view.getSpnRating();
            
            try
            {
                if (!comment.isEmpty())
                {
                    model.provideReview(selectedDoctor, comment, rating);
                    JOptionPane.showMessageDialog(null, "Review added successfully!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please fill in the comment field!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not add the review!");
            }
            
            refreshDoctorsJList();
        }
        
    }
}
