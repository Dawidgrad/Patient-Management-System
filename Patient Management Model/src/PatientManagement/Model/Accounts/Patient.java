/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Reviews.ReviewListSingleton;

/**
 *
 * @author Davio
 */
public class Patient extends Account
{
    public enum Gender { MALE, FEMALE }

    private Gender gender;
    private int age;
    
    public Patient(String name, String surname, String address, String idNumber, String password, int age, Gender gender)
    {
        super(name, surname, address, idNumber, password, AccountType.PATIENT);
        
        this.age = age;
        this.gender = gender;
    }
    
    public void ProvideReview(Doctor doctor, String comment, int rating)
    {
        ReviewListSingleton reviewList = ReviewListSingleton.getInstance();
        reviewList.AddReview(this, doctor, comment, rating);
    }
}
