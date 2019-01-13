/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorAppointmentView;
import PatientManagement.GuiViews.DoctorMedicineView;
import PatientManagement.GuiViews.DoctorMenuView;
import PatientManagement.Model.Accounts.Doctor;
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
}
