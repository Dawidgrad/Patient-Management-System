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
public class Administrator extends Account
{
    public Administrator(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.ADMINISTRATOR);
    }
    
    public boolean CreateNewAccount(String name, String surname, String address, String idNumber, String password, AccountType type)
    {
        ConcreteAccountFactory accountFactory = new ConcreteAccountFactory();
        Account newAccount = accountFactory.CreateAccount(name, surname, address, idNumber, password, type);
        
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        boolean success = accountList.addAccount(newAccount);
        
        return success;
    }
    
    public void RemoveAccount(Account account)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.removeAccount(account);
    }
    
    public void GiveDoctorFeedback(Doctor doctor, String feedback)
    {
        doctor.updateAdministratorFeedback(feedback);
    }
}
