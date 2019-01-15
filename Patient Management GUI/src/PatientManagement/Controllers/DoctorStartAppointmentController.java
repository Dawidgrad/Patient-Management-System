/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorAppointmentView;
import PatientManagement.GuiViews.DoctorStartAppointmentView;
import PatientManagement.GuiViews.LoginView;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Appointments.Appointment;
import PatientManagement.Model.Appointments.Notes;
import PatientManagement.Model.Appointments.Prescription;
import PatientManagement.Model.Appointments.PrescriptionMedicine;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.StockSingleton;
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
public class DoctorStartAppointmentController 
{
    private DoctorStartAppointmentView view;
    private Doctor model;
    private Appointment currentAppointment;
    private ArrayList<PrescriptionMedicine> prescribedMedicine;
    private DefaultListModel<String> prescribedMedicineModel;
    private Prescription prescription;
    
    public DoctorStartAppointmentController(DoctorStartAppointmentView view, Doctor model, Appointment currentAppointment)
    {
        this.view = view;
        this.model = model;
        this.currentAppointment = currentAppointment;
        prescribedMedicine = new ArrayList<PrescriptionMedicine>();
        this.prescribedMedicineModel = new DefaultListModel<String>();
        
        this.view.setVisible(true);
        
        this.view.addMedicineAddListener(new AddMedicineListener());
        this.view.addCompleteAppointmentListener(new CompleteAppointmentListener());
        this.view.addCreateAppointmentListener(new CreateAppointmentListener());	
        this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshMedicineJList();
    }
    
    private void refreshMedicineJList()
    {
        ArrayList<Medicine> medicineList = getMedicineList();
        populateMedicineJList(medicineList);
    }

    private ArrayList<Medicine> getMedicineList()
    {
        StockSingleton medicines = StockSingleton.getInstance();

        return medicines.getMedicineList();
    }

    private void populateMedicineJList(ArrayList<Medicine> medicineList)
    {
        ArrayList<String> medicineStringList = new ArrayList<String>();

        for (Medicine medicine : medicineList)
        {
            medicineStringList.add(medicine.getMedicineId() + " Name: " + medicine.getName() 
                    + "\t Quantity: " + medicine.getQuantity() + medicine.getQuantityInformation() + "\t Price: " + medicine.getPrice() + " Stock: " + medicine.getAmountInStock());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : medicineStringList)
        {
            model.addElement(element);
        }

        view.setLstMedicines(model);
    }
    
    private Medicine getSelectedMedicine()
    {
        String details = view.getLstMedicines().getSelectedValue();
        StockSingleton stock = StockSingleton.getInstance();

        String idNumber;
        int index = details.indexOf(" Name:");

        idNumber = details.substring(0, index);
        Medicine selectedMedicine = (Medicine)stock.getMedicine(Integer.parseInt(idNumber));
        
        return selectedMedicine;
    }
    
    public class AddMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                Medicine selectedMedicine = getSelectedMedicine();
                String dosage = view.getTxtDosage();
                
                if (selectedMedicine.getAmountInStock() >= 1)
                {
                    prescribedMedicineModel.addElement(selectedMedicine.getMedicineId() + " Name: " + selectedMedicine.getName() 
                            + "\t Quantity: " + selectedMedicine.getQuantity() + selectedMedicine.getQuantityInformation());
                    view.setLstCurrentPrescription(prescribedMedicineModel);

                    prescribedMedicine.add(new PrescriptionMedicine(selectedMedicine, selectedMedicine.getQuantity(), dosage));
                    JOptionPane.showMessageDialog(null, "Medicine added successfully!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not enough medicine in stock!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not add the medicine to the prescription!");
            }
        }
        
    }
    
    public class CompleteAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            { 
                String notes = view.getTxtNotes();
                
                model.completeAppointment(currentAppointment, new Notes(notes), prescribedMedicine);
                JOptionPane.showMessageDialog(null, "Appointment completed!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not complete appointment!");
            }
        }
        
    }

    public class CreateAppointmentListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                Date date = view.getDatAppointmentDate();
                String time = view.getCmbAppointmentTime();
            
                model.createAppointment(model, currentAppointment.getPatient(), date, time);
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
            DoctorAppointmentView newView = new DoctorAppointmentView();
            newView.setLocation(view.getLocation());
            view.dispose();
            DoctorAppointmentController appointmentController = new DoctorAppointmentController(newView, model);
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
