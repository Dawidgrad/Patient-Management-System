/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.*;
import PatientManagement.Model.Accounts.*;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

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
        
        this.view.setVisible(true);
        
        this.view.addSubmitListener(new SubmitListener());
        this.view.addRequestAccountListener(new RequestAccountListener());
    }
    
    private void determineAccountType()
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
    
    class SubmitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String idNumber = view.getTxtIdNumber();
                String password = view.getTxtPassword();

                boolean result = model.logIn(idNumber, password);

                if (result)
                {
                    determineAccountType();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Could not log in! Please check ID Number and Password!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Please provide the information!");
            }
            
        }
    }
    
    public class RequestAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            RequestAccountView newView = new RequestAccountView();
            view.dispose();
            newView.setLocation(view.getLocation());
            RequestAccountController requestAccount = 
                    new RequestAccountController(newView, AccountVerificationSingleton.getInstance());
        }
        
    }
}
