/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.PatientHistoryView;
import PatientManagement.Model.Accounts.Patient;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.AppointmentListSingleton;
import PatientManagement.Model.Appointments.PrescriptionMedicine;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

/**
 *
 * @author Davio
 */
public class PatientViewHistoryController 
{
    private PatientHistoryView view;
    private Patient model;
    
    public PatientViewHistoryController(PatientHistoryView view, Patient model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addSelectAppointmentListener(new SelectAppointmentListener());
        
        RefreshAppointmentJList();
    }
    
    private void RefreshAppointmentJList()
    {
        ArrayList<Appointment> appointmentList = GetAppointmentList();
        PopulateAppointmentJList(appointmentList);
    }

    private ArrayList<Appointment> GetAppointmentList()
    {
        AppointmentListSingleton appointments = AppointmentListSingleton.getInstance();

        return appointments.getPatientHistory(model);
    }

    private void PopulateAppointmentJList(ArrayList<Appointment> appointmentList)
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
    
    private int GetSelectedAppointmentId()
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
            AppointmentListSingleton appointmentList = AppointmentListSingleton.getInstance();
            int appointmentId = GetSelectedAppointmentId();
            
            Appointment selectedAppointment = appointmentList.getAppointment(appointmentId);
            
            view.setLblDoctorName(selectedAppointment.getDoctorName());
            view.setLblDoctorAddress(selectedAppointment.getDoctor().getAddress());
            view.setLblPatientName(selectedAppointment.getPatientName());
            view.setLblPatientAddress(selectedAppointment.getPatient().getAddress());
            view.setLblSex(selectedAppointment.getPatient().getGender().toString());
            view.setLblAge(String.valueOf(selectedAppointment.getPatient().getAge()));
            view.setLblDate(selectedAppointment.getDate() + " " + selectedAppointment.getTime());
            
            ArrayList<PrescriptionMedicine> prescribedMedicine = selectedAppointment.getPrescription().getMedicine();
            
            String prescription = "";
            for (PrescriptionMedicine element : prescribedMedicine)
            {
                prescription += element.getDetails() + System.lineSeparator();
            }
            
            view.setTxtPrescription(prescription);
        }
        
    }
}
