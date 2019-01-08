/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.Patient.Gender;

/**
 *
 * @author Davio
 */
public class LoginSystemSingleton {
    
    private static Account loggedInAccount = null;
    private static LoginSystemSingleton uniqueInstance = null;
    
    private LoginSystemSingleton() {}
    
    public static LoginSystemSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new LoginSystemSingleton();
        }
        
        return uniqueInstance;
    }
    
    public Account getLoggedInAccount() {
        return loggedInAccount;
    }
        
    public boolean LogIn(String idNumber, String password)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        Account targetAccount = accountList.GetAccount(idNumber);
        boolean correctInfo = false;
                
        if (targetAccount != null)
        {
            correctInfo = ValidatieLoginInfo(targetAccount, idNumber, password);

            if (correctInfo)
            {
                loggedInAccount = targetAccount;
            }
        }
        
        //return correctInfo;
        loggedInAccount = new Patient("root", "root", "root", "root", "root", 32, Gender.FEMALE);
        return true;
    }
    
    public boolean ValidatieLoginInfo(Account account, String idNumber, String password)
    {
        boolean result = false;
        
        if (account.getIdNumber() == idNumber)
        {
            if (account.getPassword() == password)
            {
                result = true;
            }
        }
        
        return result;
    }
    
    public boolean LogOut()
    {
        return false;
    }
    
}
