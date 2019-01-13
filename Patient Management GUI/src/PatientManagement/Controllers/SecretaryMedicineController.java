/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryMedicineView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.Secretary;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.StockSingleton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class SecretaryMedicineController 
{
    private SecretaryMedicineView view;
    private Secretary model;
    
    public SecretaryMedicineController(SecretaryMedicineView view, Secretary model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
       
        this.view.AddGiveMedicineListener(new GiveMedicineListener());
        this.view.AddOrderMedicineListener(new OrderMedicineListener());
        
        RefreshMedicineJList();
        RefreshPatientJList();
    }
    
    private void RefreshPatientJList()
    {
        ArrayList<Account> patientList = GetPatientList();
        PopulatePatientJList(patientList);
    }
     
    private void RefreshMedicineJList()
    {
        ArrayList<Medicine> medicineList = GetMedicineList();
        PopulateMedicineJList(medicineList);
    }

    private ArrayList<Medicine> GetMedicineList()
    {
        StockSingleton medicines = StockSingleton.getInstance();
        
        return medicines.GetMedicineList();
    }

    private void PopulateMedicineJList(ArrayList<Medicine> medicineList)
    {
        ArrayList<String> medicineStringList = new ArrayList<String>();

        for (Medicine medicine : medicineList)
        {
            medicineStringList.add(medicine.getMedicineId() + " Name: " + medicine.getName() + "\t Quantity: " 
                    + medicine.getQuantity() + "\t Price unit: " + medicine.getPrice() + " Stock: " + medicine.getAmountInStock());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : medicineStringList)
        {
            model.addElement(element);
        }

        view.setLstMedicines(model);
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
    
    private int GetSelectedMedicineId()
    {
        String details = view.getLstMedicines().getSelectedValue();

        String medicineId;
        int x = details.indexOf("Name:");

        medicineId = details.substring(0, x-1);
        
        return Integer.parseInt(medicineId);
    }
    
    public class GiveMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            StockSingleton medicineList = StockSingleton.getInstance();
            int amountToGive = view.getSpnAmountToGive();
            int medicineId = GetSelectedMedicineId();
            
            try
            {
                medicineList.GiveMedicine(medicineId, amountToGive);  
                JOptionPane.showMessageDialog(null, "The medicine has been given to the patient!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not give the medicine to patient!");
            }
            
            RefreshMedicineJList();
        }
        
    }
    
    public class OrderMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            StockSingleton medicineList = StockSingleton.getInstance();
            int amountToOrder = view.getSpnAmountToOrder();
            int medicineId = GetSelectedMedicineId();
            
            try
            {
                medicineList.OrderMedicine(medicineId, amountToOrder);
                JOptionPane.showMessageDialog(null, "The medicine has been ordered!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not order the medicine!");
            }
            
            RefreshMedicineJList();
        }
        
    }
}
