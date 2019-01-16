/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import java.io.Serializable;

/**
 *
 * @author Davio
 */
public abstract class Account implements Serializable
{
    private String name;
    private String surname;
    private String address;
    private String idNumber;
    private String password;
    private AccountType accountType;
        
    /**
     * Creates instance of the account.
     * @param name Account holder's first name
     * @param surname Account holder's last name
     * @param address Account holder's address
     * @param idNumber Account holder's ID number
     * @param password Account holder's password
     * @param type Type of the account to be created
     */
    public Account(String name, String surname, String address, String idNumber, String password, AccountType type)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.idNumber = idNumber;
        this.password = password;
        this.accountType = type;
    }

    /**
     * Returns the name of the account holder.
     * @return Name of the account holder
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the account holder.
     * @param name Name of the account holder
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the surname of the account holder.
     * @return Surname of the account holder
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the surname of the account holder.
     * @param surname Surname of the account holder
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Returns the address of the account holder.
     * @return Address of the account holder
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the account holder.
     * @param address Address of the account holder
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the ID number of the account holder.
     * @return ID number of the account holder
     */
    public String getIdNumber() {
        return idNumber;
    }
    
    /**
     * Returns the password of the account holder.
     * @return Password of the account holder
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the account holder.
     * @param password Password of the account holder
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the account type of the account holder.
     * @return Account type of the account holder
     */
    public AccountType getAccountType() {
        return accountType;
    }
}
