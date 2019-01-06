/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.*;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class LoginController {
    private LoginView view;
    private LoginSystemSingleton model;
    
    public LoginController(LoginView view, LoginSystemSingleton model)
    {
        this.view = view;
        this.model = model;
        
        this.view.addSubmitListener(new SubmitListener());
    }
    
    class SubmitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            String idNumber = view.getTxtIdNumber().getText();
            String password = new String(view.getTxtPassword().getPassword());
            
            boolean result = model.LogIn(idNumber, password);
            
            if (result)
            {
                DetermineAccountType();
            }
            else
            {
                view.setLblResult("Could not log in!");
            }
        }
        
        private void DetermineAccountType()
        {
            switch (model.getAccountType())
            {
                case DOCTOR:
                    DoctorMenuView doctorView = new DoctorMenuView();
//                    view.setVisible(false);
//                    doctorView.setLocation(view.getLocation());
//                    DoctorMenuController doctorController = new DoctorMenuController(doctorView, );
                    break;
                case SECRETARY:
                    SecretaryMenuView secretaryView = new SecretaryMenuView();
                    break;
                case ADMINISTRATOR:
                    AdministratorMenuView administratorView = new AdministratorMenuView();
                    break;
                case PATIENT:
                    PatientMenuView patientView = new PatientMenuView();
                    break;
            }
        }
        
    }
}
