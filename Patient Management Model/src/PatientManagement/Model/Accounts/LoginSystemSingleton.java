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
    
    /**
     * Gets the instance of the existing singleton to prevent from creating multiple instances of it.
     * @return Instance of current login system singleton
     */
    public static LoginSystemSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new LoginSystemSingleton();
        }
        
        return uniqueInstance;
    }
    
    /**
     * Gets the account instance that is currently logged in.
     * @return Currently logged in account
     */
    public Account getLoggedInAccount() {
        return loggedInAccount;
    }
        
    /**
     * Logs in the account after validating its information.
     * @param idNumber ID number of the account - Login
     * @param password Password of the account
     * @return True / False value indicating if the account details were correct
     */
    public boolean logIn(String idNumber, String password)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        Account targetAccount = accountList.getAccount(idNumber);
        boolean correctInfo = false;
                
        if (targetAccount != null)
        {
            correctInfo = validatieLoginInfo(targetAccount, password);

            if (correctInfo)
            {
                loggedInAccount = targetAccount;
            }
        }
        
        return correctInfo;
    }
    
    /**
     * Validates provided log in information based on existing account.
     * @param account Account instance that is getting accessed
     * @param password Password of the account instance
     * @return True / False value indicating if the information provided was
     */
    private boolean validatieLoginInfo(Account account, String password)
    {
        boolean result = false;
        
        if (account.getPassword().equals(password))
        {
            result = true;
        }
        
        return result;
    }
    
    /**
     * Logs out the currently logged in account.
     */
    public void logOut()
    {
        loggedInAccount = null;
    }
    
}
