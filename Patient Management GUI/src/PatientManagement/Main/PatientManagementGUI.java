/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Main;
import PatientManagement.Controllers.LoginController;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;

/**
 *
 * @author Davio
 */
public class PatientManagementGUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LoginView view = new LoginView();
        LoginSystemSingleton model = LoginSystemSingleton.getInstance();
        LoginController controller = new LoginController(view, model);
        
        view.setVisible(true);
    }
    
}
