/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.PatientAppointmentView;
import PatientManagement.GuiViews.PatientAppointmentRequestView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
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
public class PatientAppointmentRequestController 
{
    private PatientAppointmentRequestView view;
    private Patient model;
    
    public PatientAppointmentRequestController(PatientAppointmentRequestView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addRequestAppointmentListener(new RequestAppointmentListener());
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshDoctorsJList();
    }
    
    private void refreshDoctorsJList()
    {
        ArrayList<Account> doctorList = getDoctorList();
        populateDoctorJList(doctorList);
    }

    private ArrayList<Account> getDoctorList()
    {
        AccountListSingleton accounts = AccountListSingleton.getInstance();

        ArrayList<Account> doctorList = new ArrayList<Account>();

        doctorList.addAll(accounts.getAccountTypeList(AccountListSingleton.AccountType.DOCTOR));

        return doctorList;
    }

    private void populateDoctorJList(ArrayList<Account> doctorList)
    {
        ArrayList<String> doctorStringList = new ArrayList<String>();

        for (Account doctor : doctorList)
        {
            doctorStringList.add(doctor.getIdNumber() + " Name: " + doctor.getName() + " " + doctor.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : doctorStringList)
        {
            model.addElement(element);
        }

        view.setLstDoctors(model);
    }
    
    private Doctor getSelectedDoctor()
    {
        String details = view.getLstDoctors().getSelectedValue();
        AccountListSingleton accountList = AccountListSingleton.getInstance();

        String idNumber;
        int x = details.indexOf("Name:");

        idNumber = details.substring(0, x-1);
        Doctor selectedDoctor = (Doctor)accountList.getAccount(idNumber);
        return selectedDoctor;
    }
        
    
    
    public class RequestAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                if (model.getScheduledAppointment() == null)
                {
                    Doctor doctor = getSelectedDoctor();
                    Date date = view.getDatAppointmentDate();
                    String time = view.getCmbAppointmentTime();

                    model.requestAppointment(date, doctor, time);
                    JOptionPane.showMessageDialog(null, "Appointment requested!\n"
                            + "You will receive notification when one of the Secretaries approves it.");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "You already have the appointment booked!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not request the appointment!");
            }
        }

    }
    	
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            PatientAppointmentView newView = new PatientAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            PatientAppointmentController appointmentController = new PatientAppointmentController(newView, model);
        }

    }
    
    public class LogOutListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            LoginSystemSingleton login = LoginSystemSingleton.getInstance();
            login.logOut();
            
            LoginView newView = new LoginView();
            newView.setLocation(view.getLocation());
            view.dispose();
            LoginController loginController = new LoginController(newView, login);
        }
        
    }
}
