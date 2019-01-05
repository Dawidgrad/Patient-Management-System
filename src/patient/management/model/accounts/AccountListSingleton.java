/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.model.accounts;

import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class AccountListSingleton {
    private static AccountListSingleton uniqueInstance = null;
    private static ArrayList<Account> accountList;
    
    private AccountListSingleton()
    {
        accountList = new ArrayList<Account>();
    }
    
    public static AccountListSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new AccountListSingleton();
        }
        
        return uniqueInstance;
    }
    
    public void AddAccount(Account newAccount)
    {
        accountList.add(newAccount);
    }
    
    public void RemoveAccount(String idNumber)
    {
        Account accountToRemove = null;
        
        for (Account account : accountList)
        {
            if (account.getIdNumber().equals(idNumber))
            {
                accountToRemove = account;
            } 
        }
        
        if (accountToRemove != null)
        {
            accountList.remove(accountToRemove);
        }
    }
}
