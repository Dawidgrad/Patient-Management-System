/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryNewAppointmentView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Doctor;
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
        
        this.view.AddCreateAppointmentListener(new CreateAppointmentListener());
        
        RefreshPatientJList();
        RefreshDoctorsJList();
    }
    
    private void RefreshPatientJList()
    {
        ArrayList<Account> patientList = GetPatientList();
        PopulatePatientJList(patientList);
    }
    
    private void RefreshDoctorsJList()
    {
        ArrayList<Account> doctorList = GetDoctorList();
        PopulateDoctorJList(doctorList);
    }
    
    private ArrayList<Account> GetPatientList()
    {
        AccountListSingleton patients = AccountListSingleton.getInstance();

        ArrayList<Account> patientList = new ArrayList<Account>();

        patientList.addAll(patients.GetAccountTypeList(AccountListSingleton.AccountType.PATIENT));

        return patientList;
    }

    private void PopulatePatientJList(ArrayList<Account> patientList)
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
            doctorStringList.add(doctor.getIdNumber() + " Name:" + doctor.getName() + " " + doctor.getSurname());
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
                JOptionPane.showMessageDialog(null, "Appointment created successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not create the appointment!");
            }
        }
        
    }
}
