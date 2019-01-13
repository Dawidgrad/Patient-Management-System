/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.RequestAccountView;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Patient.Sex;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class RequestAccountController 
{
    private RequestAccountView view;
    private AccountVerificationSingleton model;
    
    public RequestAccountController(RequestAccountView view, AccountVerificationSingleton model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addRequestAccountListener(new RequestAccountListener());
    }
    
    public class RequestAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            String firstName = view.getTxtFirstName();
            String lastName = view.getTxtLastName();
            String address = view.getTxtAddress();
            String password = view.getTxtPassword();
            int age = view.getSpnAge();
            Sex sex = getSelectedSex();
            
            try
            {
                AccountListSingleton accountList = AccountListSingleton.getInstance();
                String idNumber = accountList.getNextIdNumber(AccountType.PATIENT);
               
                Patient request = new Patient(firstName, lastName, address, idNumber, password, age, sex);
                model.addVerificationRequest(request);
                        
                JOptionPane.showMessageDialog(null, "Account requested successfully!" + System.lineSeparator() 
                        + "Your new ID is " + idNumber 
                        + ". Before you log in, you will need to wait for Secretary to validate your account.");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not request the account!");
            }
        }
        
        private Sex getSelectedSex()
        {
            String selectionText = "";
            
            for (Enumeration<AbstractButton> buttons = view.getGrpSex().getElements(); buttons.hasMoreElements();) 
            {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                       selectionText = button.getText();
                }
            }      
            
            Sex type = null;
            
            switch(selectionText)
            {
                case "Male":
                    type = Sex.MALE;
                    break;
                case "Female":
                    type = Sex.FEMALE;
                    break;
            }
            
            return type;
        }
        
    }
}
