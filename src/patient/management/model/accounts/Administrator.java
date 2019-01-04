/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.model.accounts;

/**
 *
 * @author Davio
 */
public class Administrator extends Account
{
    public void CreateNewAccount(String type)
    {
        ConcreteAccountFactory accountFactory = new ConcreteAccountFactory();
        Account newAccount = accountFactory.CreateAccount(type);
        
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.AddAccount(newAccount);
    }
}
