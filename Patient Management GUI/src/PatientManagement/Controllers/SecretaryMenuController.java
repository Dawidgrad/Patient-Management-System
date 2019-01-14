/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.SecretaryAccountView;
import PatientManagement.GuiViews.SecretaryAppointmentView;
import PatientManagement.GuiViews.SecretaryMedicineView;
import PatientManagement.GuiViews.SecretaryMenuView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Secretary;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class SecretaryMenuController 
{
    private SecretaryMenuView view;
    private Secretary model;
    
    public SecretaryMenuController(SecretaryMenuView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addPatientAccountsListener(new PatientAccountsListener());
        this.view.addAppointmentsListener(new AppointmentsListener());
        this.view.addMedicinesListener(new MedicinesListener());
	this.view.addLogOutListener(new LogOutListener());
        
        checkForAppointmentRequests();
        checkForAccountRequests();
        checkForAccountTerminations();
    }
    
    private void checkForAppointmentRequests()
    {
        AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
        ArrayList<Appointment> currentRequests = appointmentList.getStateList(Appointment.AppointmentState.REQUESTED);
        
        if (currentRequests.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "There are appointments awaiting approval!");
        }
    }
    
    private void checkForAccountRequests()
    {
        AccountVerificationSingleton accountRequests = AccountVerificationSingleton.getInstance();
        ArrayList<Patient> requestList = accountRequests.getVerificationRequests();
        
        if (requestList.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "There are account requests awaiting validation!");
        }
    }
    
    private void checkForAccountTerminations()
    {
        AccountTerminationSingleton accountTerminations = AccountTerminationSingleton.getInstance();
        ArrayList<Patient> terminationList = accountTerminations.getTerminationRequests();
        
        if (terminationList.size() > 0)
        {
            JOptionPane.showMessageDialog(null, "There are account terminations awaiting approval!");
        }
    }
    
    public class PatientAccountsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryAccountView newView = new SecretaryAccountView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryAccountController accountController = new SecretaryAccountController(newView, model);
        }
        
    }
    
    public class AppointmentsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryAppointmentView newView = new SecretaryAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryAppointmentController appointmentController = new SecretaryAppointmentController(newView, model);
        }
        
    }
    
    public class MedicinesListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryMedicineView newView = new SecretaryMedicineView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryMedicineController medicineController = new SecretaryMedicineController(newView, model);
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
