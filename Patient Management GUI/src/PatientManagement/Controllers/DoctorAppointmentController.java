/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorAppointmentView;
import PatientManagement.GuiViews.DoctorMenuView;
import PatientManagement.GuiViews.DoctorStartAppointmentView;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.PatientHistoryView;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class DoctorAppointmentController 
{
    private DoctorAppointmentView view;
    private Doctor model;
    
    public DoctorAppointmentController(DoctorAppointmentView view, Doctor model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addInspectHistoryListener(new InspectHistoryListener());
        this.view.addStartAppointmentListener(new StartAppointmentListener());
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

        return appointments.getScheduledAppointments(model);
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
    
    public class InspectHistoryListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
                int appointmentId = getSelectedAppointmentId();

                Appointment selectedAppointment = appointmentList.getAppointment(appointmentId);

                PatientHistoryView newView = new PatientHistoryView();
                newView.setLocation(view.getLocation());
                view.dispose();
                PatientHistoryController doctorMedicine = new PatientHistoryController(newView, selectedAppointment.getPatient(), model);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not inspect patient's history!");
            }
        }
        
    }
    
    public class StartAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
                int appointmentId = getSelectedAppointmentId();

                Appointment selectedAppointment = appointmentList.getAppointment(appointmentId);

                DoctorStartAppointmentView newView = new DoctorStartAppointmentView();
                newView.setLocation(view.getLocation());
                view.dispose();
                DoctorStartAppointmentController startAppointment = new DoctorStartAppointmentController(newView, model, selectedAppointment);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not start the appointment!");
            }
        }
        
    }
    
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            DoctorMenuView newView = new DoctorMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorMenuController menuController = new DoctorMenuController(newView, model);
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
