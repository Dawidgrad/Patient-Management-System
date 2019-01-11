/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorReviewView;
import PatientManagement.GuiViews.PatientAppointmentView;
import PatientManagement.GuiViews.PatientMenuView;
import PatientManagement.Model.Accounts.Patient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    }
    
    public class AccountSettingsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    public class BrowseDoctorsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DoctorReviewView newView = new DoctorReviewView();            
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorReviewController doctorReview = new DoctorReviewController(newView, model);
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
}
