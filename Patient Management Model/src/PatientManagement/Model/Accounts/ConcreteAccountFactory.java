/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;

/**
 *
 * @author Davio
 */
public class ConcreteAccountFactory extends AccountFactory
{

    /**
     * Creates the instance of an account with the type specified at run time.
     * @param name Account holder's first name
     * @param surname Account holder's last name
     * @param address Account holder's address
     * @param idNumber Account holder's ID number
     * @param password Account holder's password
     * @param type Type of the account to be created
     * @return Account instance created by the factory
     */
    @Override
    public Account createAccount(String name, String surname, String address, String idNumber, String password, AccountType type)
    {
        Account newAccount;
        
        switch (type)
        {
            case DOCTOR:
                newAccount = new Doctor(name, surname, address, idNumber, password);
                break;
            case SECRETARY:
                newAccount = new Secretary(name, surname, address, idNumber, password);
                break;
            case ADMINISTRATOR:
                newAccount = new Administrator(name, surname, address, idNumber, password);
                break;
            default:
                newAccount = null;
        }
        
        return newAccount;
    }
}
