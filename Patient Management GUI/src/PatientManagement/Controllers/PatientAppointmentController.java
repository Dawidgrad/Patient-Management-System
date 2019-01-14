/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.PatientAppointmentView;
import PatientManagement.GuiViews.PatientHistoryView;
import PatientManagement.GuiViews.RequestAppointmentView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class PatientAppointmentController 
{
    private PatientAppointmentView view;
    private Patient model;
    
    public PatientAppointmentController(PatientAppointmentView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addAppointmentHistoryListener(new AppointmentHistoryListener());
        this.view.addRequestAppointmentListener(new RequestAppointmentListener());
	this.view.addLogOutListener(new LogOutListener());
    }
    
    public class AppointmentHistoryListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            PatientHistoryView newView = new PatientHistoryView();
            newView.setLocation(view.getLocation());
            view.dispose();
            PatientHistoryController historyController = new PatientHistoryController(newView, model);
        }
        
    }
    
    public class RequestAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            RequestAppointmentView newView = new RequestAppointmentView();            
            newView.setLocation(view.getLocation());
            view.dispose();
            RequestAppointmentController requestAppointment = new RequestAppointmentController(newView, model);
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
