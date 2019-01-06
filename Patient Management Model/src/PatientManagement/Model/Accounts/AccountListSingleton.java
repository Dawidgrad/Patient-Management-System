/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class AccountListSingleton {
    public static enum AccountType {ADMINISTRATOR, DOCTOR, SECRETARY, PATIENT}
    
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
    
    public boolean AddAccount(Account newAccount)
    {
        boolean uniqueAccount = true;
        
        for (Account account : accountList)
        {
            if (account.getIdNumber().equals(newAccount.getIdNumber()))
            {
                uniqueAccount = false;
            } 
        }
        
        if (uniqueAccount)
        {
            accountList.add(newAccount);
        }
        
        return uniqueAccount;
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
    
    public Account ValidatieLoginInfo(String idNumber, String password)
    {
        Account result = null;
        
        for (Account account : accountList)
        {
            if (account.getIdNumber() == idNumber)
            {
                if (account.getPassword() == password)
                {
                    result = account;
                    break;
                }
            }
        }
        
        return result;
    }
    
    public ArrayList<Account> GetAccountTypeList(AccountType type)
    {
        ArrayList<Account> typeList = new ArrayList<Account>();
        
        for (Account account : accountList)
        {
            if (account.getAccountType() == type)
            {
                typeList.add(account);
            }
        }
        
        return typeList;
    }
}
