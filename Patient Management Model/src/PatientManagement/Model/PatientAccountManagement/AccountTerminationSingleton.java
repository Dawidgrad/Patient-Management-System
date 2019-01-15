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
    
    public ArrayList<Patient> getTerminationRequests()
    {
        return accountsToTerminate;
    }
    
    public void addTerminationRequest(Patient patient)
    {
        accountsToTerminate.add(patient);
    }
    
    public void removeAccount(Patient patient)
    {
        accountsToTerminate.remove(patient);
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.removeAccount(patient);
    }
    
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