/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryAppointmentRequestsView;
import PatientManagement.Model.Accounts.Secretary;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        
        this.view.AddApproveAppointmentListener(new ApproveAppointmentListener());
    }
    
    private int GetSelectedAppointmentId()
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
                int appointmentId = GetSelectedAppointmentId();
                model.ProcessAppointmentRequest(appointmentId);
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Cannot approve the appointment!");
            }
        }
        
    }
}
