/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryAppointmentView;
import PatientManagement.GuiViews.SecretaryMedicineView;
import PatientManagement.GuiViews.SecretaryMenuView;
import PatientManagement.Model.Accounts.Secretary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class SecretaryMenuController {
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
    }
    
    public class PatientAccountsListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            SecretaryAppointmentController secretaryAppointment = new SecretaryAppointmentController(newView, model);
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
            SecretaryMedicineController secretaryMedicine = new SecretaryMedicineController(newView, model);
        }
        
    }
}
