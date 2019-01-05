/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class LoginController {
    private LoginView view;
    private LoginSystemSingleton model;
    
    public LoginController(LoginView view, LoginSystemSingleton model)
    {
        this.view = view;
        this.model = model;
        
        this.view.addSubmitListener(new SubmitListener());
    }
    
    class SubmitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            String idNumber = view.getTxtIdNumber().getText();
            String password = view.getTxtPassword().getText();
            
            boolean result = model.LogIn(idNumber, password);
            
            if (result)
            {
                view.setLblResult("Success!");
            }
            else
            {
                view.setLblResult("Could not log in!");
            }
        }
        
    }
}
