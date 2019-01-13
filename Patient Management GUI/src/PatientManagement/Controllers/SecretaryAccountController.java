/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryAccountView;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Secretary;
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
        
        RefreshVerificationJList();
    }
    
    private void RefreshVerificationJList()
    {
        ArrayList<Patient> accountList = GetVerificationList();
        PopulateVerificationJList(accountList);
    }

    private ArrayList<Patient> GetVerificationList()
    {
        AccountVerificationSingleton accounts = AccountVerificationSingleton.getInstance();

        return accounts.getVerificationRequests();
    }

    private void PopulateVerificationJList(ArrayList<Patient> accountList)
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
    
//    private void RefreshTerminationJList()
//    {
//        ArrayList<Patient> accountList = GetTerminationList();
//        PopulateTerminationJList(accountList);
//    }
//
//    private ArrayList<Patient> GetTerminationList()
//    {
//        AccountVerificationSingleton accounts = AccountVerificationSingleton.getInstance();
//
//        return accounts.getVerificationRequests();
//    }
//
//    private void PopulateTerminationJList(ArrayList<Patient> accountList)
//    {
//        ArrayList<String> accountStringList = new ArrayList<String>();
//
//        for (Patient account : accountList)
//        {
//            accountStringList.add(account.getIdNumber() + " Name: " + account.getName() + " " + account.getSurname());
//        }
//
//        DefaultListModel<String> model = new DefaultListModel<>();
//
//        for (String element : accountStringList)
//        {
//            model.addElement(element);
//        }
//
//        view.setLstAccountsToTerminate(model);
//    }
    
    private Patient getSelectedPatient()
    {
        String details = view.getLstAccountsToVerify().getSelectedValue();
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
                Patient patient = getSelectedPatient();
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
               
            }
            catch (Exception ex)
            {
                
            }
        }
        
    }
}
