/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.PatientReviewView;
import PatientManagement.GuiViews.PatientAccountView;
import PatientManagement.GuiViews.PatientAppointmentView;
import PatientManagement.GuiViews.PatientMenuView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class PatientMenuController {
    private PatientMenuView view;
    private Patient model;
    
    public PatientMenuController(PatientMenuView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addAccountSettingsListener(new AccountSettingsListener());
        this.view.addBrowseDoctorsListener(new BrowseDoctorsListener());
        this.view.addAppointmentsListener(new AppointmentsListener());
	this.view.addLogOutListener(new LogOutListener());
        
        checkIfAppointmentApproved();
    }
    
    private void checkIfAppointmentApproved()
    {
        if (model.isAppointmentJustApproved() == true)
        {
            JOptionPane.showMessageDialog(null, "Your appointment has been approved by the Secretary!");
            model.setAppointmentJustApproved(false);
        }
    }
    
    public class AccountSettingsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            PatientAccountView newView = new PatientAccountView();
            newView.setLocation(view.getLocation());
            view.dispose();
            PatientAccountController accountController = new PatientAccountController(newView, model);
        }
        
    }
    
    public class BrowseDoctorsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            PatientReviewView newView = new PatientReviewView();            
            newView.setLocation(view.getLocation());
            view.dispose();
            PatientReviewController doctorReview = new PatientReviewController(newView, model);
        }
        
    }
    
    public class AppointmentsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            PatientAppointmentView newView = new PatientAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            PatientAppointmentController patientAppointment = new PatientAppointmentController(newView, model);
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
