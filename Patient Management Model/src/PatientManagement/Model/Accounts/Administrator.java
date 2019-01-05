/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

/**
 *
 * @author Davio
 */
public class Administrator extends Account
{
    public Administrator(String name, String surname, String address, String idNumber)
    {
        super(name, surname, address, idNumber, LoginSystemSingleton.AccountType.ADMINISTRATOR);
    }
    
    public void CreateNewAccount(String type, String name, String surname, String address, String idNumber)
    {
        ConcreteAccountFactory accountFactory = new ConcreteAccountFactory();
        Account newAccount = accountFactory.CreateAccount(type, name, surname, address, idNumber);
        
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.AddAccount(newAccount);
    }
    
    public void RemoveAccount(String idNumber)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.RemoveAccount(idNumber);
    }
}
