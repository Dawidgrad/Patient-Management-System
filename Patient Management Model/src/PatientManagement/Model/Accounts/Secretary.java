/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.LoginSystemSingleton.AccountType;

/**
 *
 * @author Davio
 */
public class Secretary extends Account
{
    public Secretary(String name, String surname, String address, String idNumber)
    {
        super(name, surname, address, idNumber, AccountType.SECRETARY);
    }
}
