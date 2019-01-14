/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.PatientAppointmentView;
import PatientManagement.GuiViews.RequestAppointmentView;
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
    }
    
    public class AppointmentHistoryListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

}
