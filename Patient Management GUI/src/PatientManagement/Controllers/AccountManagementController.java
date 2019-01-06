/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.AccountManagementView;
import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Accounts.Administrator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;


/**
 *
 * @author Davio
 */
public class AccountManagementController 
{
    private AccountManagementView view;
    private Administrator model;
    
    public AccountManagementController(AccountManagementView view, Administrator model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.AddCreateAccountListener(new CreateAccountListener());
        this.view.AddDeleteAccountListener(new DeleteAccountListener());
    }
    
    public class CreateAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String idNumber = view.getTxtIdNumber().getText();
            String firstName = view.getTxtFirstName().getText();
            String lastName = view.getTxtLastName().getText();
            String address = view.getTxtAddress().getText();
            String password = new String(view.getTxtPassword().getPassword());
            
            String typeText = "";
            
            for (Enumeration<AbstractButton> buttons = view.getBtngrpAccountType().getElements(); buttons.hasMoreElements();) 
            {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                       typeText = button.getText();
                }
            }      
            
            AccountType type = null;
            
            switch(typeText)
            {
                case "Administrator":
                    type = AccountType.ADMINISTRATOR;
                    break;
                case "Doctor":
                    type = AccountType.DOCTOR;
                    break;
                case "Secretary":
                    type = AccountType.SECRETARY;
                    break;
            }
            
            try
            {
                boolean result = model.CreateNewAccount(firstName, lastName, address, idNumber, password, type);
                
                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Account added successfully!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Account with given credentials could not be added! "
                            + "Account with that ID Number might already exist.");
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Unknown Error!");
            }
        }
        
    }
    
    public class DeleteAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
