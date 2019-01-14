/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.PatientAccountView;
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
    }
    
    public class ChangePasswordListener implements ActionListener 
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String currentPassword = view.getTxtCurrentPassword();
                String newPassword = view.getTxtNewPassword();
                String newPasswordConfirm = view.getTxtNewPasswordConfirm();
                
                model.changePassword(currentPassword, newPassword, newPasswordConfirm);
                JOptionPane.showMessageDialog(null, "Password changed successfully!");
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
}
