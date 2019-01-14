/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryAccountView;
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
    
    public SecretaryAccountController(SecretaryAccountView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addVerificationListener(new VerificationListener());
        this.view.addTerminationListener(new TerminationListener());
        
        refreshVerificationJList();
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
        AccountVerificationSingleton accountList = AccountVerificationSingleton.getInstance();

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
                Patient patient = getSelectedAccountToVerify();
                model.verifyAccount(patient);
                JOptionPane.showMessageDialog(null, "Account verified successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not verify the account!");
            }
        }
        
    }
    
    public class TerminationListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
               Patient patient = getSelectedAccountToTerminate();
               model.terminateAccount(patient);
               JOptionPane.showMessageDialog(null, "Account terminated successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not terminate the account!");
            }
        }
        
    }
}
