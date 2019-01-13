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
    private int accountCount;
    
    private AccountListSingleton()
    {
        accountList = new ArrayList<Account>();
        accountCount = 1;
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
    
    public String getNextIdNumber(AccountType type)
    {
        String letter = "";
        
        switch (type)
        {
            case ADMINISTRATOR:
                letter = "A";
                break;
            case DOCTOR:
                letter = "D";
                break;
            case SECRETARY:
                letter = "S";
                break;
            case PATIENT:
                letter = "P";
                break;
        }
        
        String idNumber = letter + String.format("%04d", accountCount);
        accountCount++;
        
        return idNumber;
    }
    
    public boolean addAccount(Account newAccount)
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
    
    public void removeAccount(Account accountToRemove)
    {
        accountList.remove(accountToRemove);
    }
    
    public ArrayList<Account> getAccountTypeList(AccountType type)
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
    
    public Account getAccount(String idNumber)
    {
        Account targetAccount = null;
        
        for (Account account : accountList)
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
