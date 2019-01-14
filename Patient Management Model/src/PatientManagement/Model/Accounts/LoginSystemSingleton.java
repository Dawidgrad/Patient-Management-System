/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import java.io.Serializable;

/**
 *
 * @author Davio
 */
public class LoginSystemSingleton
{    
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
        
    public boolean logIn(String idNumber, String password)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        Account targetAccount = accountList.getAccount(idNumber);
        boolean correctInfo = false;
                
        if (targetAccount != null)
        {
            correctInfo = validatieLoginInfo(targetAccount, idNumber, password);

            if (correctInfo)
            {
                loggedInAccount = targetAccount;
            }
        }
        
        return correctInfo;
    }
    
    public boolean validatieLoginInfo(Account account, String idNumber, String password)
    {
        boolean result = false;
        
        if (account.getIdNumber().equals(idNumber))
        {
            if (account.getPassword().equals(password))
            {
                result = true;
            }
        }
        
        return result;
    }
    
    public boolean logOut()
    {
        return false;
    }
    
}
