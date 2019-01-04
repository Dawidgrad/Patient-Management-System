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
public class ConcreteAccountFactory extends AccountFactory
{
    @Override
    public Account CreateAccount(String type)
    {
        Account newAccount;
        
        switch (type)
        {
            case "Doctor":
                newAccount = new Doctor();
                break;
            case "Secretary":
                newAccount = new Secretary();
                break;
            case "Administrator":
                newAccount = new Administrator();
                break;
            default:
                newAccount = null;
        }
        
        return newAccount;
    }
}
