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
        
    public Account(String name, String surname, String address, String idNumber, String password, AccountType type)
    {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.idNumber = idNumber;
        this.password = password;
        this.accountType = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdNumber() {
        return idNumber;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
