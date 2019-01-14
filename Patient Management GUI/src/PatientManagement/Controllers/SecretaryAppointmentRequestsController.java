/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryAppointmentRequestsView;
import PatientManagement.Model.Accounts.Secretary;
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
public class SecretaryAppointmentRequestsController 
{
    private SecretaryAppointmentRequestsView view;
    private Secretary model;
    
    public SecretaryAppointmentRequestsController(SecretaryAppointmentRequestsView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addApproveAppointmentListener(new ApproveAppointmentListener());
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

        return appointments.getStateList(Appointment.AppointmentState.REQUESTED);
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

        view.setLstAppointmentRequests(model);
    }
    
    private int getSelectedAppointmentId()
    {
        String details = view.getLstAppointmentRequests().getSelectedValue();

        String appointmentId;
        int x = details.indexOf("Doctor");

        appointmentId = details.substring(0, x-1);
        
        return Integer.parseInt(appointmentId);
    }
    
    public class ApproveAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                int appointmentId = getSelectedAppointmentId();
                model.processAppointmentRequest(appointmentId);
                JOptionPane.showMessageDialog(null, "Appointment has been approved!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Cannot approve the appointment!");
            }
        }
        
    }
}
