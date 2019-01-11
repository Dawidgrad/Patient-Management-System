/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryAppointmentRequestsView;
import PatientManagement.GuiViews.SecretaryAppointmentView;
import PatientManagement.GuiViews.SecretaryNewAppointmentView;
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
        
        this.view.AddCreateNewListener(new CreateNewListener());
        this.view.AddRequestListener(new RequestListener());
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
}
