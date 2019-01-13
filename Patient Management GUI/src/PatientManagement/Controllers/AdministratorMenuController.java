/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.AccountManagementView;
import PatientManagement.GuiViews.AdministratorMenuView;
import PatientManagement.GuiViews.DoctorFeedbackView;
import PatientManagement.Model.Accounts.Administrator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class AdministratorMenuController {
    private AdministratorMenuView view;
    private Administrator model;
    
    public AdministratorMenuController(AdministratorMenuView view, Administrator model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);

        this.view.addAccountListener(new AddAccountListener());
        this.view.addDoctorListListener(new DoctorListListener());
    }
    
    class AddAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AccountManagementView newView = new AccountManagementView();
            newView.setLocation(view.getLocation());
            view.dispose();
            AccountManagementController accountController = new AccountManagementController(newView, model);
        }
        
    }
    
    class DoctorListListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            DoctorFeedbackView newView = new DoctorFeedbackView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorFeedbackController feedbackController = new DoctorFeedbackController(newView, model);
        }
        
    }
}
