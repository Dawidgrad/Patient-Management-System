/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.AdministratorMenuView;
import PatientManagement.Model.Accounts.Administrator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Davio
 */
public class AdministratorMenuController {
    private AdministratorMenuView view;
    private Administrator model;
    
    public AdministratorMenuController(AdministratorMenuView view, Administrator model)
    {
        this.view = view;
        this.model = model;
        
        this.view.addAccountListener(new AccountListener());
        this.view.addDoctorListListener(new DoctorListListener());
    }
    
    class AccountListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    class DoctorListListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
