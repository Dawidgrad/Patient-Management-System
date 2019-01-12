/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Main;
import PatientManagement.Controllers.LoginController;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Patient.Gender;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.Reviews.ReviewListSingleton;

/**
 *
 * @author Davio
 */
public class PatientManagementGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ReadSerialisedObjects();
        
        AccountListSingleton list = AccountListSingleton.getInstance();
        list.AddAccount(new Patient("Test", "test", "test", "PTest", "test", 31, Gender.FEMALE));
        
        LoginView view = new LoginView();
        LoginSystemSingleton model = LoginSystemSingleton.getInstance();
        LoginController controller = new LoginController(view, model);
        
        view.setVisible(true);
        
        Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public void run()
                {
                    PatientManagementGUI.SerialiseObjects();
                }
            }
        );
    }
    
    private static void SerialiseObjects()
    {
        Serialiser serialiser = new Serialiser("account_list.ser");
        serialiser.writeObject(AccountListSingleton.getInstance());
        
        serialiser.setName("appointment_list.ser");
        serialiser.writeObject(AppointmentListSingleton.getInstance());
        
        serialiser.setName("stock.ser");
        serialiser.writeObject(StockSingleton.getInstance());
        
        serialiser.setName("review_list.ser");
        serialiser.writeObject(ReviewListSingleton.getInstance());
        
    }
    
    private static void ReadSerialisedObjects()
    {
        Serialiser serialiser = new Serialiser("account_list.ser");
        serialiser.readObject();
                
        serialiser.setName("appointment_list.ser");
        serialiser.readObject();
        
        serialiser.setName("stock.ser");
        serialiser.readObject();
        
        serialiser.setName("review_list.ser");
        serialiser.readObject();
    }
    
}
