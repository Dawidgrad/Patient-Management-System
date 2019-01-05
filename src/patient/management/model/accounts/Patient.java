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
public class Patient extends Account
{
    public enum Gender { MALE, FEMALE }

    private Gender gender;
    private int age;
    
    public Patient(String name, String surname, String address, String idNumber, int age, Gender gender)
    {
        super(name, surname, address, idNumber);
        
        this.age = age;
        this.gender = gender;
    }
}
