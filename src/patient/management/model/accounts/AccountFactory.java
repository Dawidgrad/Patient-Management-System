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
public abstract class AccountFactory 
{
    public abstract Account CreateAccount(String type, String name, String surname, String address, String idNumber);
}
