/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.AccountManagementView;
import PatientManagement.Main.PatientManagementGUI;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Accounts.Administrator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
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
        this.view.AddRefreshListListener(new RefreshListListener());
        
        RefreshAccountJList();
    }

    private void RefreshAccountJList()
    {
        ArrayList<Account> accountList = GetAccountList();
        PopulateAccountJList(accountList);
    }

    private ArrayList<Account> GetAccountList()
    {
        AccountListSingleton accounts = AccountListSingleton.getInstance();

        ArrayList<Account> accountList = new ArrayList<Account>();

        accountList.addAll(accounts.getAccountTypeList(AccountType.DOCTOR));
        accountList.addAll(accounts.getAccountTypeList(AccountType.SECRETARY));

        return accountList;
    }

    private void PopulateAccountJList(ArrayList<Account> accountList)
    {
        ArrayList<String> accountStringList = new ArrayList<String>();

        for (Account account : accountList)
        {
            accountStringList.add(account.getIdNumber() + " Name: " + account.getName() + " " + account.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : accountStringList)
        {
            model.addElement(element);
        }

        view.getAccountList().setModel(model);
    }
    
    public class CreateAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String firstName = view.getTxtFirstName().getText();
            String lastName = view.getTxtLastName().getText();
            String address = view.getTxtAddress().getText();
            String password = new String(view.getTxtPassword().getPassword());
            AccountType type = getSelectedType();
            
            AccountListSingleton accountList = AccountListSingleton.getInstance();
            String idNumber = accountList.getNextIdNumber(type);
            
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
        
        private AccountType getSelectedType()
        {
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
            
            return type;
        }
    }
    
    public class DeleteAccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String accountToRemove = view.getAccountList().getSelectedValue();
                String idNumber;

                int x = accountToRemove.indexOf("Name:");
                idNumber = accountToRemove.substring(0, x-1);
                
                AccountListSingleton accountList = AccountListSingleton.getInstance();
                Account targetAccount = accountList.getAccount(idNumber);
                model.RemoveAccount(targetAccount);
                
                RefreshAccountJList();
                JOptionPane.showMessageDialog(null, "The account has been deleted!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not delete the account!");
            }
        }
        
    }
    
    public class RefreshListListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                RefreshAccountJList();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not refresh the list!");
            }
        }
        
    }
}
