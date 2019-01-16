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
public class AccountVerificationSingleton implements Serializable
{
    private static AccountVerificationSingleton uniqueInstance = null;
    private ArrayList<Patient> accountsToVerify;
    
    private AccountVerificationSingleton()
    {
        accountsToVerify = new ArrayList<Patient>();
    }
    
    /**
     * Method to get the instance of the singleton to prevent from creating multiple instances.
     * @return Returns instance of existing account verification request list singleton
     */
    public static AccountVerificationSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new AccountVerificationSingleton();
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
     * Gets the complete list of all newly created patient accounts to verify.
     * @return List of patient accounts to verify
     */
    public ArrayList<Patient> getVerificationRequests()
    {
        return accountsToVerify;
    }
    
    /**
     * Adds newly created account to verification list to get verified by Secretary.
     * @param patient Patient account instance to verify
     */
    public void addVerificationRequest(Patient patient)
    {
        accountsToVerify.add(patient);
    }
    
    /**
     * Verifies the patient account instance and adds it to account list
     * @param patient Patient account instance to verify
     */
    public void verifyAccount(Patient patient)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.addAccount(patient);
        accountsToVerify.remove(patient);
    }
    
    /**
     * Gets the patient account instance from verification list based on the ID number
     * @param idNumber ID number of the account to verify
     * @return Instance of the Patient account to verify
     */
    public Patient getPatient(String idNumber)
    {
        Patient targetAccount = null;
        
        for (Patient account : accountsToVerify)
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
