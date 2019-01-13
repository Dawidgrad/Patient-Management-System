/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.PatientAccountManagement;

import PatientManagement.Model.Accounts.Patient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class AccountVerificationSingleton 
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
}
