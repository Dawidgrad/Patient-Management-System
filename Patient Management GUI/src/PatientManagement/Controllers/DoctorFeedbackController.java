/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorFeedbackView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Accounts.Administrator;
import PatientManagement.Model.Accounts.Doctor;
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
public class DoctorFeedbackController 
{
    private DoctorFeedbackView view;
    private Administrator model;
    private Doctor selectedDoctor = null;
    
    public DoctorFeedbackController(DoctorFeedbackView view, Administrator model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.AddSelectDoctorListener(new SelectDoctor());
        this.view.AddSubmitFeebackListener(new SubmitFeedback());
        
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

        doctorList.addAll(accounts.getAccountTypeList(AccountType.DOCTOR));

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
            try
            {
                UpdateSelectedDoctor();
                UpdateDoctorReviews();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not select the doctor!");
            }
        }
        
        private void UpdateSelectedDoctor()
        {
            String details = view.getLstDoctors().getSelectedValue();
            AccountListSingleton accountList = AccountListSingleton.getInstance();
            
            String idNumber;
            int x = details.indexOf("Name:");

            idNumber = details.substring(0, x-1);
            selectedDoctor = (Doctor)accountList.getAccount(idNumber);
        }
        
        private void UpdateDoctorReviews()
        {
            ReviewListSingleton reviewList = ReviewListSingleton.getInstance();
            
            // Updates feedback for selected doctor
            DoctorFeedback feedback = reviewList.GetFeedback(selectedDoctor.getIdNumber());
            
            // Update UI
            view.setLblRating(Double.toString(feedback.getAverageRating()));
            
            String patientComments = "";

            for (String element : feedback.getComments())
            {
                patientComments += element + System.lineSeparator() + System.lineSeparator();
            }

            view.setTxtComments(patientComments);
        }
        
    }
    
    public class SubmitFeedback implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String administratorFeedback = view.getTxtFeedback();
                model.GiveDoctorFeedback(selectedDoctor, administratorFeedback);
                JOptionPane.showMessageDialog(null, "Feedback submitted successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not submit the feedback!");
            }
        }
        
    }
    
}
