/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patient.management.model;
import patient.management.model.accounts.*;
import java.util.Scanner;

/**
 *
 * @author Davio
 */
public class PatientManagementModel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean nextLoop;
        Administrator admin = new Administrator();
        Scanner scanner = new Scanner(System.in);
        do
        {
            System.out.println("List of available accounts: Doctor Administrator Secretary");
            System.out.println("Enter the name of the account you want to create: ");
            String type = scanner.nextLine();
            admin.CreateNewAccount(type);
            
            System.out.println("Do you want to create another account? Answer \"Yes\" or \"No\"");
            String loopAnswer = scanner.nextLine();
            
            if (loopAnswer.equals("Yes"))
            {
                nextLoop = true;
            }
            else if (loopAnswer.equals("No"))
            {
                nextLoop = false;
            }
            else
            {
                System.out.println("Wrong answer. Ending the program...");
                nextLoop = false;
            }
            
        } while(nextLoop == true);
        
        System.out.println("Program finishes...");
        
        AccountListSingleton x = AccountListSingleton.getInstance();
    }
    
}
