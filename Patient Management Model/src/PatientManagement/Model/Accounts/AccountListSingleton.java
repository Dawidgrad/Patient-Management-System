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

    /**
     * Type of the user's account. Each type provides different functionality.
     */
    public static enum AccountType implements Serializable { ADMINISTRATOR, DOCTOR, SECRETARY, PATIENT}
    
    private static AccountListSingleton uniqueInstance = null;
    private ArrayList<Account> accountList;
    private int accountCount;
    
    private AccountListSingleton()
    {
        accountList = new ArrayList<Account>();
        accountCount = 1;
    }
    
    /**
     * Method to get the instance of the singleton to prevent from creating multiple instances
     * @return Returns instance of existing singleton
     */
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
    
    /**
     * Gets next available id number that can be used in account creation. 
     * Concatenates different letter used in ID number depending on the account type passed.
     * @param type Account type used in ID number String
     * @return Returns ID number String that is appropriate for account type used
     */
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
    
    /**
     * Add new account to account list.
     * @param newAccount Account instance to add to the list
     * @return True / False value indicating if the account was added
     */
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
    
    /**
     * Removes the account from the account list
     * @param accountToRemove Account to remove
     */
    public void removeAccount(Account accountToRemove)
    {
        accountList.remove(accountToRemove);
    }
    
    /**
     * Provides a list of accounts based on account type passed.
     * E.g. By passing DOCTOR account type, the method will return accounts of that type.
     * @param type Type of the account
     * @return List of accounts of given type
     */
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
    
    /**
     * Method returns the instance of an account that has given ID number
     * @param idNumber ID number of the account instance 
     * @return Account instance with ID Number passed to the method
     */
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
