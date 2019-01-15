/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.PatientAccountView;
import PatientManagement.GuiViews.PatientMenuView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class PatientAccountController 
{
    private PatientAccountView view;
    private Patient model;
    
    public PatientAccountController(PatientAccountView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addChangePasswordListener(new ChangePasswordListener());
        this.view.addTerminateAccountListener(new TerminateAccountListener());	
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
    }
    
    public class ChangePasswordListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                if (model.getPassword().equals(view.getTxtCurrentPassword()))
                {
                    String currentPassword = view.getTxtCurrentPassword();
                    String newPassword = view.getTxtNewPassword();
                    String newPasswordConfirm = view.getTxtNewPasswordConfirm();

                    if (newPassword.equals(model.getPassword()) == false 
                            && newPasswordConfirm.equals(newPassword))
                    {
                        model.changePassword(currentPassword, newPassword, newPasswordConfirm);
                        JOptionPane.showMessageDialog(null, "Password changed successfully!");
                    }
                    else if (newPassword.equals(model.getPassword()) == true)
                    {
                        JOptionPane.showMessageDialog(null, "New password is the same as the old one!");
                    }
                    else if (!newPasswordConfirm.equals(newPassword))
                    {
                        JOptionPane.showMessageDialog(null, "Confirmed password do not match");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect password!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not change the password!");
            }
        }
        
    }
    
    public class TerminateAccountListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                if (model.getPassword().equals(view.getTxtPasswordTermination()))
                {
                    int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to terminate your account?");  
                    if (dialogResult == JOptionPane.YES_OPTION)
                    {
                        model.requestAccountTermination();
                        JOptionPane.showMessageDialog(null, "Account termination has been successfully requested!");
                    } 
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Incorrect password!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not request the account termination!");
            }
        }
        
    }
    
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            PatientMenuView newView = new PatientMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            PatientMenuController menuController = new PatientMenuController(newView, model);
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
