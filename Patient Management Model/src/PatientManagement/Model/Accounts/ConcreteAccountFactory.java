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
public class ConcreteAccountFactory extends AccountFactory
{
    @Override
    public Account CreateAccount(String type, String name, String surname, String address, String idNumber)
    {
        Account newAccount;
        
        switch (type)
        {
            case "Doctor":
                newAccount = new Doctor(name, surname, address, idNumber);
                break;
            case "Secretary":
                newAccount = new Secretary(name, surname, address, idNumber);
                break;
            case "Administrator":
                newAccount = new Administrator(name, surname, address, idNumber);
                break;
            default:
                newAccount = null;
        }
        
        return newAccount;
    }
}
