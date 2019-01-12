/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Davio
 */
public class AccountListSingleton implements Serializable
{
    public static enum AccountType implements Serializable {ADMINISTRATOR, DOCTOR, SECRETARY, PATIENT}
    
    private static AccountListSingleton uniqueInstance = null;
    private ArrayList<Account> accountList;
    
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
    
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException 
    {
        ois.defaultReadObject();
        uniqueInstance = this;
    }   
    
    private Object readResolve()
    {
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
    
    public Account GetAccount(String idNumber)
    {
        Account targetAccount = null;
        
        for (Account account : accountList)
        {
            if (account.getIdNumber() == idNumber)
            {
                targetAccount = account;
                break;
            }
        }
        
        return targetAccount;
    }
}
