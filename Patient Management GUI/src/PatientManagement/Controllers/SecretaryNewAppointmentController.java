/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.SecretaryAppointmentView;
import PatientManagement.GuiViews.SecretaryNewAppointmentView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Accounts.Secretary;
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
public class SecretaryNewAppointmentController 
{
    private SecretaryNewAppointmentView view;
    private Secretary model;
    
    public SecretaryNewAppointmentController(SecretaryNewAppointmentView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addCreateAppointmentListener(new CreateAppointmentListener());
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshPatientJList();
        refreshDoctorsJList();
    }
    
    private void refreshPatientJList()
    {
        ArrayList<Account> patientList = getPatientList();
        populatePatientJList(patientList);
    }
    
    private void refreshDoctorsJList()
    {
        ArrayList<Account> doctorList = getDoctorList();
        populateDoctorJList(doctorList);
    }
    
    private ArrayList<Account> getPatientList()
    {
        AccountListSingleton patients = AccountListSingleton.getInstance();

        ArrayList<Account> patientList = new ArrayList<Account>();

        patientList.addAll(patients.getAccountTypeList(AccountListSingleton.AccountType.PATIENT));

        return patientList;
    }

    private void populatePatientJList(ArrayList<Account> patientList)
    {
        ArrayList<String> patientStringList = new ArrayList<String>();

        for (Account patient : patientList)
        {
            patientStringList.add(patient.getIdNumber()+ " Name: " + patient.getName() + " " + patient.getSurname());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : patientStringList)
        {
            model.addElement(element);
        }

        view.setLstPatients(model);
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
            doctorStringList.add(doctor.getIdNumber() + " Name:" + doctor.getName() + " " + doctor.getSurname());
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
    
    private Patient getSelectedPatient()
    {
        String details = view.getLstPatients().getSelectedValue();
        AccountListSingleton accountList = AccountListSingleton.getInstance();

        String idNumber;
        int x = details.indexOf("Name:");

        idNumber = details.substring(0, x-1);
        Patient selectedPatient = (Patient)accountList.getAccount(idNumber);
        return selectedPatient;
    }
    
    public class CreateAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                Doctor doctor = getSelectedDoctor();
                Patient patient = getSelectedPatient();
                Date date = view.getDatAppointmentDate();
                String time = view.getCmbAppointmentTime();
            
                model.createAppointment(doctor, patient, date, time);
                JOptionPane.showMessageDialog(null, "Appointment created successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not create the appointment!");
            }
        }
        
    }
    	
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryAppointmentView newView = new SecretaryAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryAppointmentController appointmentController = new SecretaryAppointmentController(newView, model);
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
