/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryNewAppointmentView;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Secretary;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class SecretaryNewAppointmentController 
{
    private SecretaryNewAppointmentView view;
    private Secretary model;
    
    public SecretaryNewAppointmentController(SecretaryNewAppointmentView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.AddCreateAppointmentListener(new CreateAppointmentListener());
    }
    
    private Doctor GetSelectedDoctor()
    {
        String details = view.getLstDoctors().getSelectedValue();
        AccountListSingleton accountList = AccountListSingleton.getInstance();

        String idNumber;
        int x = details.indexOf("Name:");

        idNumber = details.substring(0, x-1);
        Doctor selectedDoctor = (Doctor)accountList.GetAccount(idNumber);
        return selectedDoctor;
    }
    
    private Patient GetSelectedPatient()
    {
        String details = view.getLstPatients().getSelectedValue();
        AccountListSingleton accountList = AccountListSingleton.getInstance();

        String idNumber;
        int x = details.indexOf("Name:");

        idNumber = details.substring(0, x-1);
        Patient selectedPatient = (Patient)accountList.GetAccount(idNumber);
        return selectedPatient;
    }
    
    public class CreateAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                Doctor doctor = GetSelectedDoctor();
                Patient patient = GetSelectedPatient();
                Date date = view.getDatAppointmentDate();
                String time = view.getCmbAppointmentTime();
            
                model.CreateAppointment(doctor, patient, date, time);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not create the appointment!");
            }
        }
        
    }
}
