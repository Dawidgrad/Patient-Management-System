/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.AdministratorAccountView;
import PatientManagement.GuiViews.AdministratorMenuView;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Accounts.Administrator;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;


/**
 *
 * @author Davio
 */
public class AdministratorAccountController
{
    private AdministratorAccountView view;
    private Administrator model;
    
    public AdministratorAccountController(AdministratorAccountView view, Administrator model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addCreateAccountListener(new CreateAccountListener());
        this.view.addDeleteAccountListener(new DeleteAccountListener());
        this.view.addRefreshListListener(new RefreshListListener());
        this.view.addBackListener(new BackListener());
        this.view.addLogOutListener(new LogOutListener());
        
        refreshAccountJList();
    }

    private void refreshAccountJList()
    {
        ArrayList<Account> accountList = getAccountList();
        populateAccountJList(accountList);
    }

    private ArrayList<Account> getAccountList()
    {
        AccountListSingleton accounts = AccountListSingleton.getInstance();

        ArrayList<Account> accountList = new ArrayList<Account>();

        accountList.addAll(accounts.getAccountTypeList(AccountType.DOCTOR));
        accountList.addAll(accounts.getAccountTypeList(AccountType.SECRETARY));

        return accountList;
    }

    private void populateAccountJList(ArrayList<Account> accountList)
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
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String firstName = view.getTxtFirstName();
                String lastName = view.getTxtLastName();
                String address = view.getTxtAddress();
                String password = view.getTxtPassword();
                AccountType type = getSelectedType();

                AccountListSingleton accountList = AccountListSingleton.getInstance();
                String idNumber = accountList.getNextIdNumber(type);
            
                boolean result = model.createNewAccount(firstName, lastName, address, idNumber, password, type);
                
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
                JOptionPane.showMessageDialog(null, "Could not add account!");
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
                model.removeAccount(targetAccount);
                
                refreshAccountJList();
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
                refreshAccountJList();
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not refresh the list!");
            }
        }
        
    }
    
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AdministratorMenuView newView = new AdministratorMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            AdministratorMenuController menuController = new AdministratorMenuController(newView, model);
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
