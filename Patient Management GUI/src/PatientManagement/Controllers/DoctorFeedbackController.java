/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorFeedbackView;
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
public class DoctorFeedbackController 
{
    private DoctorFeedbackView view;
    private Doctor model;
    
    public DoctorFeedbackController(DoctorFeedbackView view, Doctor model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        this.view.setTxtFeedback(model.getFeedback().getAdministratorFeedback());
    }
    
    	
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DoctorMenuView newView = new DoctorMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorMenuController menuController = new DoctorMenuController(newView, model);
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
