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
        
        this.view.AddSelectDoctorListener(new SelectDoctor());
        this.view.AddSubmitReviewListener(new SubmitReview());
        
        RefreshDoctorsJList();
    }
    
        
    private void RefreshDoctorsJList()
    {
        ArrayList<Account> doctorList = GetDoctorList();
        PopulateDoctorJList(doctorList);
    }

    private ArrayList<Account> GetDoctorList()
    {
        AccountListSingleton accounts = AccountListSingleton.getInstance();

        ArrayList<Account> doctorList = new ArrayList<Account>();

        doctorList.addAll(accounts.GetAccountTypeList(AccountListSingleton.AccountType.DOCTOR));

        return doctorList;
    }

    private void PopulateDoctorJList(ArrayList<Account> doctorList)
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
            UpdateSelectedDoctor();
            UpdateDoctorReviews();
        }
        
        private void UpdateSelectedDoctor()
        {
            String details = view.getLstDoctors().getSelectedValue();
            AccountListSingleton accountList = AccountListSingleton.getInstance();
            
            String idNumber;
            int x = details.indexOf("Name:");

            idNumber = details.substring(0, x-1);
            selectedDoctor = (Doctor)accountList.GetAccount(idNumber);
        }
        
        private void UpdateDoctorReviews()
        {
            ReviewListSingleton reviewList = ReviewListSingleton.getInstance();
            
            // Updates feedback for selected doctor
            DoctorFeedback feedback = reviewList.GetFeedback(selectedDoctor.getIdNumber());
            
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
            
            RefreshDoctorsJList();
        }
        
    }
}
