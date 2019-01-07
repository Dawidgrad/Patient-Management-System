/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.*;
import PatientManagement.Model.Accounts.*;
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
            switch (model.getLoggedInAccount().getAccountType())
            {
                case DOCTOR:
                    DoctorMenuView doctorView = new DoctorMenuView();
                    view.dispose();
                    doctorView.setLocation(view.getLocation());
                    DoctorMenuController doctorController = 
                            new DoctorMenuController(doctorView, ((Doctor)model.getLoggedInAccount()));
                    break;
                case SECRETARY:
                    SecretaryMenuView secretaryView = new SecretaryMenuView();
                    view.dispose();
                    secretaryView.setLocation(view.getLocation());
                    SecretaryMenuController secretaryController = 
                            new SecretaryMenuController(secretaryView, ((Secretary)model.getLoggedInAccount()));
                    break;
                case ADMINISTRATOR:
                    AdministratorMenuView administratorView = new AdministratorMenuView();
                    view.dispose();
                    administratorView.setLocation(view.getLocation());
                    AdministratorMenuController administratorController = 
                            new AdministratorMenuController(administratorView, ((Administrator)model.getLoggedInAccount()));
                    break;
                case PATIENT:
                    PatientMenuView patientView = new PatientMenuView();
                    view.dispose();
                    patientView.setLocation(view.getLocation());
                    PatientMenuController patientController = 
                            new PatientMenuController(patientView, ((Patient)model.getLoggedInAccount()));
                    break;
            }
        }
        
    }
}
