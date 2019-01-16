/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.PatientAccountManagement;

import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Patient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class AccountTerminationSingleton implements Serializable
{
    private static AccountTerminationSingleton uniqueInstance = null;
    private ArrayList<Patient> accountsToTerminate;
    
    private AccountTerminationSingleton()
    {
        accountsToTerminate = new ArrayList<Patient>();
    }
    
    /**
     * Method to get the instance of the singleton to prevent from creating multiple instances.
     * @return Returns instance of existing account termination request list singleton
     */
    public static AccountTerminationSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new AccountTerminationSingleton();
        }
        
        return uniqueInstance;
    }
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException 
    {
        ois.defaultReadObject();
        uniqueInstance = this;
    }   
    
    private Object readResolve()
    {
        return uniqueInstance;
    }
    
    /**
     * Gets the complete list of patient accounts which requested termination.
     * @return List of patient accounts to terminate
     */
    public ArrayList<Patient> getTerminationRequests()
    {
        return accountsToTerminate;
    }
    
    /**
     * Adds account to termination list.
     * @param patient Patient account instance to terminate
     */
    public void addTerminationRequest(Patient patient)
    {
        accountsToTerminate.add(patient);
    }
    
    /**
     * Removes the account from account list and termination list.
     * @param patient Patient account instance to terminate
     */
    public void removeAccount(Patient patient)
    {
        accountsToTerminate.remove(patient);
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.removeAccount(patient);
    }
    
    /**
     * Gets the Patient account instance based on their ID number.
     * @param idNumber ID number of the patient account
     * @return Instance of the patient account to terminate
     */
    public Patient getPatient(String idNumber)
    {
        Patient targetAccount = null;
        
        for (Patient account : accountsToTerminate)
        {
            if (account.getIdNumber().equals(idNumber))
            {
                targetAccount = account;
                break;
            }
        }
        
        return targetAccount;
    }
}