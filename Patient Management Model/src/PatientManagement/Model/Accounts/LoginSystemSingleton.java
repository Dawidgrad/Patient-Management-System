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
public class LoginSystemSingleton {
    public static enum AccountType {ADMINISTRATOR, DOCTOR, SECRETARY, PATIENT}
    
    private static String idNumber;
    private static AccountType accountType;
    private static LoginSystemSingleton uniqueInstance = null;
    
    private LoginSystemSingleton() {}
    
    public static LoginSystemSingleton getInstance()
    {
        if (uniqueInstance == null)
        {
            uniqueInstance = new LoginSystemSingleton();
        }
        
        return uniqueInstance;
    }
    
    public static String getIdNumber() {
        return idNumber;
    }

    public static AccountType getAccountType() {
        return accountType;
    }
        
    public boolean LogIn(String idNumber, String password)
    {
        return true;
    }
    
    public boolean LogOut()
    {
        return false;
    }
    
}
