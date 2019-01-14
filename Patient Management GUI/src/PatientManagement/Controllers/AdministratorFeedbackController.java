/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.AdministratorMenuView;
import PatientManagement.GuiViews.AdministratorFeedbackView;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Accounts.Administrator;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
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
public class AdministratorFeedbackController 
{
    private AdministratorFeedbackView view;
    private Administrator model;
    private Doctor selectedDoctor = null;
    
    public AdministratorFeedbackController(AdministratorFeedbackView view, Administrator model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addSelectDoctorListener(new SelectDoctor());
        this.view.addSubmitFeebackListener(new SubmitFeedback());
        this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
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

        doctorList.addAll(accounts.getAccountTypeList(AccountType.DOCTOR));

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
            try
            {
                updateSelectedDoctor();
                updateDoctorReviews();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not select the doctor!");
            }
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
            
            DoctorFeedback feedback = reviewList.getFeedback(selectedDoctor.getIdNumber());
            
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
                model.giveDoctorFeedback(selectedDoctor, administratorFeedback);
                JOptionPane.showMessageDialog(null, "Feedback submitted successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not submit the feedback!");
            }
        }
        
    }
    
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AdministratorMenuView newView = new AdministratorMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            AdministratorMenuController menuController = new AdministratorMenuController(newView, model);
        }

    }
    
    public class LogOutListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            LoginSystemSingleton login = LoginSystemSingleton.getInstance();
            login.logOut();
            
            LoginView newView = new LoginView();
            newView.setLocation(view.getLocation());
            view.dispose();
            LoginController loginController = new LoginController(newView, login);
        }
        
    }
    
}
