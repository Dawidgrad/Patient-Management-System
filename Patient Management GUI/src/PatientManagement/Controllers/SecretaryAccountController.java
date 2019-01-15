/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.SecretaryAccountView;
import PatientManagement.GuiViews.SecretaryMenuView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Secretary;
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class SecretaryAccountController 
{
    private SecretaryAccountView view;
    private Secretary model;
    private Patient selectedVerification = null;
    private Patient selectedTermination = null;
    
    public SecretaryAccountController(SecretaryAccountView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addVerificationListener(new VerificationListener());
        this.view.addTerminationListener(new TerminationListener());	
        this.view.addSelectVerificationListener(new SelectVerificationListener());
        this.view.addSelectTerminationListener(new SelectTerminationListener());
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshVerificationJList();
        refreshTerminationJList();
    }
    
    private void refreshVerificationJList()
    {
        ArrayList<Patient> accountList = getVerificationList();
        populateVerificationJList(accountList);
    }

    private ArrayList<Patient> getVerificationList()
    {
        AccountVerificationSingleton accounts = AccountVerificationSingleton.getInstance();

        return accounts.getVerificationRequests();
    }

    private void populateVerificationJList(ArrayList<Patient> accountList)
    {
        ArrayList<String> accountStringList = new ArrayList<String>();

        for (Patient account : accountList)
        {
            accountStringList.add(account.getIdNumber() + " Name: " + account.getName() + " " + account.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : accountStringList)
        {
            model.addElement(element);
        }

        view.setLstAccountsToVerify(model);
    }
    
    private void refreshTerminationJList()
    {
        ArrayList<Patient> accountList = getTerminationList();
        populateTerminationJList(accountList);
    }

    private ArrayList<Patient> getTerminationList()
    {
        AccountTerminationSingleton accounts = AccountTerminationSingleton.getInstance();

        return accounts.getTerminationRequests();
    }

    private void populateTerminationJList(ArrayList<Patient> accountList)
    {
        ArrayList<String> accountStringList = new ArrayList<String>();

        for (Patient account : accountList)
        {
            accountStringList.add(account.getIdNumber() + " Name: " + account.getName() + " " + account.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : accountStringList)
        {
            model.addElement(element);
        }

        view.setLstAccountsToTerminate(model);
    }
    
    private Patient getSelectedAccountToVerify()
    {
        String details = view.getLstAccountsToVerify().getSelectedValue();
        AccountVerificationSingleton accountList = AccountVerificationSingleton.getInstance();

        String idNumber;
        int index = details.indexOf(" Name:");

        idNumber = details.substring(0, index);
        Patient selectedPatient = accountList.getPatient(idNumber);
        return selectedPatient;
    }
    
    private Patient getSelectedAccountToTerminate()
    {
        String details = view.getLstAccountsToTerminate().getSelectedValue();
        AccountTerminationSingleton accountList = AccountTerminationSingleton.getInstance();

        String idNumber;
        int index = details.indexOf(" Name:");

        idNumber = details.substring(0, index);
        Patient selectedPatient = accountList.getPatient(idNumber);
        return selectedPatient;
    }
    
    public class VerificationListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                model.verifyAccount(selectedVerification);
                JOptionPane.showMessageDialog(null, "Account verified successfully!");
                selectedVerification = null;
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not verify the account!");
            }
            
            refreshVerificationJList();
        }
        
    }
    
    public class TerminationListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
               model.terminateAccount(selectedTermination);
               JOptionPane.showMessageDialog(null, "Account terminated successfully!");
               selectedTermination = null;
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not terminate the account!");
            }
            
            refreshTerminationJList();
        }
        
    }
    
    public class SelectVerificationListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try 
            {
                selectedVerification = getSelectedAccountToVerify();
                view.setLblAgeV(String.valueOf(selectedVerification.getAge()));
                view.setLblPatientAddressV(selectedVerification.getAddress());
                view.setLblPatientNameV(selectedVerification.getName() + " " + selectedVerification.getSurname());
                view.setLblSexV(selectedVerification.getSex().toString());
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not select the request!");
            }
        }
        
    }
    
    public class SelectTerminationListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try 
            {
                selectedTermination = getSelectedAccountToTerminate();
                view.setLblAgeR(String.valueOf(selectedTermination.getAge()));
                view.setLblPatientAddressR(selectedTermination.getAddress());
                view.setLblPatientNameR(selectedTermination.getName() + " " + selectedTermination.getSurname());
                view.setLblSexR(selectedTermination.getSex().toString());
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not select the request!");
            }
        }
        
    }
    
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryMenuView newView = new SecretaryMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryMenuController menuController = new SecretaryMenuController(newView, model);
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
