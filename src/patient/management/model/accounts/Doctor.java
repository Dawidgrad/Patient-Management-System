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
public class Doctor extends Account 
{
    public Doctor(String name, String surname, String address, String idNumber)
    {
        super(name, surname, address, idNumber);
    }
}
