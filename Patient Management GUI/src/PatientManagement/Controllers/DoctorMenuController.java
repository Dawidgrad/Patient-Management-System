/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorAppointmentView;
import PatientManagement.GuiViews.DoctorFeedbackView;
import PatientManagement.GuiViews.DoctorMedicineView;
import PatientManagement.GuiViews.DoctorMenuView;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class DoctorMenuController {
    private DoctorMenuView view;
    private Doctor model;
    
    public DoctorMenuController(DoctorMenuView view, Doctor model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addAppointmentsListener(new AppointmentsListener());
        this.view.addMedicinesListener(new MedicinesListener());
        this.view.addFeedbackListener(new FeedbackListener());
	this.view.addLogOutListener(new LogOutListener());
    }
    
    public class AppointmentsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DoctorAppointmentView newView = new DoctorAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorAppointmentController appointmentController = new DoctorAppointmentController(newView, model);
        }
        
    }
    
    public class MedicinesListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DoctorMedicineView newView = new DoctorMedicineView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorMedicineController medicineController = new DoctorMedicineController(newView, model);
        }
        
    }
    
    public class FeedbackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DoctorFeedbackView newView = new DoctorFeedbackView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorFeedbackController feedbackController = new DoctorFeedbackController(newView, model);
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
