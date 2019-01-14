/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Main;
import PatientManagement.Controllers.LoginController;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Administrator;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Patient.Sex;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Medicines.OrderRequestSingleton;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountTerminationSingleton;
import PatientManagement.Model.PatientAccountManagement.AccountVerificationSingleton;
import PatientManagement.Model.Reviews.ReviewListSingleton;
import java.io.Serializable;
import java.util.ArrayList;

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
//        list.addAccount(new Patient("Test", "test", "test", "PTest", "test", 31, Sex.FEMALE));
//       list.addAccount(new Administrator("root", "root", "root", "root", "root"));
        
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
        ArrayList<Serializable> singletonList = new ArrayList<Serializable>();
        singletonList.add(AccountListSingleton.getInstance());
        singletonList.add(AppointmentListSingleton.getInstance());
        singletonList.add(StockSingleton.getInstance());
        singletonList.add(ReviewListSingleton.getInstance());
        singletonList.add(AccountVerificationSingleton.getInstance());
        singletonList.add(AccountTerminationSingleton.getInstance());
        singletonList.add(OrderRequestSingleton.getInstance());
        
        Serialiser serialiser = new Serialiser("data.ser");
        serialiser.writeObject(singletonList);
        
    }
    
    private static void ReadSerialisedObjects()
    {
        Serialiser serialiser = new Serialiser("data.ser");
        serialiser.readObject();
    }
    
}
