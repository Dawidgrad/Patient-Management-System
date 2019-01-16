/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Accounts;

import PatientManagement.Model.Accounts.AccountListSingleton.AccountType;
import PatientManagement.Model.Reviews.DoctorFeedback;
import PatientManagement.Model.Reviews.ReviewListSingleton;

/**
 *
 * @author Davio
 */
public class Administrator extends Account
{

    /**
     * Constructor of the Administrator account type. 
     * @param name Administrator's first name
     * @param surname Administrator's last name
     * @param address Administrator's address
     * @param idNumber Administrator's ID number
     * @param password Administrator's password
     */
    public Administrator(String name, String surname, String address, String idNumber, String password)
    {
        super(name, surname, address, idNumber, password, AccountType.ADMINISTRATOR);
    }
    
    /**
     * Method using factory pattern to create new account
     * @param name New user's first name
     * @param surname New user's last name 
     * @param address New user's address
     * @param idNumber New user's ID number
     * @param password New user's password
     * @param type Type of the account to be created
     * @return Boolean value determining if the account was created successfully or not
     */
    public boolean createNewAccount(String name, String surname, String address, String idNumber, String password, AccountType type)
    {
        ConcreteAccountFactory accountFactory = new ConcreteAccountFactory();
        Account newAccount = accountFactory.createAccount(name, surname, address, idNumber, password, type);
        
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        boolean success = accountList.addAccount(newAccount);
        
        return success;
    }
    
    /**
     * Removes the provided account from account list
     * @param account Account instance to remove
     */
    public void removeAccount(Account account)
    {
        AccountListSingleton accountList = AccountListSingleton.getInstance();
        accountList.removeAccount(account);
    }
    
    /**
     * Provides a feedback for the Doctor user
     * @param doctor Doctor account instance to provide feedback to 
     * @param feedback Text feedback from the administrator
     */
    public void giveDoctorFeedback(Doctor doctor, String feedback)
    {
        ReviewListSingleton reviews = ReviewListSingleton.getInstance();
        DoctorFeedback adminFeedback = reviews.getFeedback(doctor.getIdNumber());
        adminFeedback.setAdministratorFeedback(feedback);
        
        doctor.updateAdministratorFeedback(adminFeedback);
    }
}
