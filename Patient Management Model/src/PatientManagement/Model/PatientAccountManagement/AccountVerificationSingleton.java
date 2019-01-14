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
    
    public ArrayList<Patient> getVerificationRequests()
    {
        return accountsToVerify;
    }
    
    public void addVerificationRequest(Patient patient)
    {
        accountsToVerify.add(patient);
    }
    
    public void verifyAccount(Patient patient)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.addAccount(patient);
        accountsToVerify.remove(patient);
    }
    
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
