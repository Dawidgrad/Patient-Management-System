/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorAppointmentView;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.PatientAppointmentView;
import PatientManagement.GuiViews.PatientHistoryView;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Appointments.PrescriptionMedicine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class PatientHistoryController 
{
    private PatientHistoryView view;
    private Patient model;
    private Doctor doctorAccess = null;
    
    public PatientHistoryController(PatientHistoryView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addSelectAppointmentListener(new SelectAppointmentListener());
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshAppointmentJList();
    }
    
    public PatientHistoryController(PatientHistoryView view, Patient model, Doctor doctorAccess)
    {
        this.view = view;
        this.model = model;
        this.doctorAccess = doctorAccess;
        
        this.view.setVisible(true);
        
        this.view.addSelectAppointmentListener(new SelectAppointmentListener());
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshAppointmentJList();
    }
    
    private void refreshAppointmentJList()
    {
        ArrayList<Appointment> appointmentList = getAppointmentList();
        populateAppointmentJList(appointmentList);
    }

    private ArrayList<Appointment> getAppointmentList()
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();

        return appointments.getPatientHistory(model);
    }

    private void populateAppointmentJList(ArrayList<Appointment> appointmentList)
    {
        ArrayList<String> appointmentStringList = new ArrayList<String>();

        for (Appointment appointment : appointmentList)
        {
            appointmentStringList.add(appointment.getAppointmentId() 
                    + " Doctor: " + appointment.getDoctorName() 
                    + " Patient: " + appointment.getPatientName() 
                    + " Date: " + appointment.getDate() + " " + appointment.getTime());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : appointmentStringList)
        {
            model.addElement(element);
        }

        view.setLstAppointments(model);
    }
    
    private int getSelectedAppointmentId()
    {
        String details = view.getLstAppointments().getSelectedValue();

        String appointmentId;
        int index = details.indexOf(" Doctor");

        appointmentId = details.substring(0, index);
        
        return Integer.parseInt(appointmentId);
    }
    
    public class SelectAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
                int appointmentId = getSelectedAppointmentId();

                Appointment selectedAppointment = appointmentList.getAppointment(appointmentId);

                view.setLblDoctorName(selectedAppointment.getDoctorName());
                view.setLblDoctorAddress(selectedAppointment.getDoctor().getAddress());
                view.setLblPatientName(selectedAppointment.getPatientName());
                view.setLblPatientAddress(selectedAppointment.getPatient().getAddress());
                view.setLblSex(selectedAppointment.getPatient().getSex().toString());
                view.setLblAge(String.valueOf(selectedAppointment.getPatient().getAge()));
                view.setLblDate(selectedAppointment.getDate() + " " + selectedAppointment.getTime());

                ArrayList<PrescriptionMedicine> prescribedMedicine = selectedAppointment.getPrescription().getMedicine();

                String prescription = "";
                for (PrescriptionMedicine element : prescribedMedicine)
                {
                    prescription += element.getDetails() + System.lineSeparator();
                }
                
                prescription += System.lineSeparator() + "Notes:" + System.lineSeparator();
                prescription += selectedAppointment.getPrescription().getNotes().getNote();

                view.setTxtPrescription(prescription); 
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not select the appointment!");
            }
        }
        
    }
    	
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (doctorAccess == null)
            {
                PatientAppointmentView newView = new PatientAppointmentView();
                newView.setLocation(view.getLocation());
                view.dispose();
                PatientAppointmentController appointmentController = new PatientAppointmentController(newView, model);
            }
            else
            {
                DoctorAppointmentView newView = new DoctorAppointmentView();
                newView.setLocation(view.getLocation());
                view.dispose();
                DoctorAppointmentController appointmentController = new DoctorAppointmentController(newView, doctorAccess);
            }
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
