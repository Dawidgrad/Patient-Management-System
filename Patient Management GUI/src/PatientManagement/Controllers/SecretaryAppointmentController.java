/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.SecretaryAppointmentRequestsView;
import PatientManagement.GuiViews.SecretaryAppointmentView;
import PatientManagement.GuiViews.SecretaryMenuView;
import PatientManagement.GuiViews.SecretaryNewAppointmentView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Secretary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class SecretaryAppointmentController 
{
    private SecretaryAppointmentView view;
    private Secretary model;
    
    public SecretaryAppointmentController(SecretaryAppointmentView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addCreateNewListener(new CreateNewListener());
        this.view.addRequestListener(new RequestListener());	
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
    }
    
    public class CreateNewListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            SecretaryNewAppointmentView newView = new SecretaryNewAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryNewAppointmentController newAppointment = new SecretaryNewAppointmentController(newView, model);
        }
        
    }
    
    public class RequestListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryAppointmentRequestsView newView = new SecretaryAppointmentRequestsView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryAppointmentRequestsController appointmentRequests = new SecretaryAppointmentRequestsController(newView, model);
        }
    } 
    
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryMenuView newView = new SecretaryMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryMenuController menuController = new SecretaryMenuController(newView, model);
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
