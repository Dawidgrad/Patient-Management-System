/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.RequestAppointmentView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.Patient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class RequestAppointmentController 
{
    private RequestAppointmentView view;
    private Patient model;
    
    public RequestAppointmentController(RequestAppointmentView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.AddRequestAppointmentListener(new RequestAppointmentListener());
        
        RefreshDoctorsJList();
    }
    
    private void RefreshDoctorsJList()
    {
        ArrayList<Account> doctorList = GetDoctorList();
        PopulateDoctorJList(doctorList);
    }

    private ArrayList<Account> GetDoctorList()
    {
        AccountListSingleton accounts = AccountListSingleton.getInstance();

        ArrayList<Account> doctorList = new ArrayList<Account>();

        doctorList.addAll(accounts.GetAccountTypeList(AccountListSingleton.AccountType.DOCTOR));

        return doctorList;
    }

    private void PopulateDoctorJList(ArrayList<Account> doctorList)
    {
        ArrayList<String> doctorStringList = new ArrayList<String>();

        for (Account doctor : doctorList)
        {
            doctorStringList.add("ID Number: " + doctor.getIdNumber() + " Name:" + doctor.getName() + " " + doctor.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : doctorStringList)
        {
            model.addElement(element);
        }

        view.setLstDoctors(model);
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
        
    
    
    public class RequestAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                Doctor doctor = GetSelectedDoctor();
                Date date = view.getDatAppointmentDate();
                String time = view.getCmbAppointmentTime();
            
                model.RequestAppointment(date, doctor, time);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not request the appointment!");
            }
        }

    }
}
