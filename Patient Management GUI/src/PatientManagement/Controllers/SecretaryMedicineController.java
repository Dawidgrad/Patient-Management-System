/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.LoginView;
import PatientManagement.GuiViews.SecretaryMedicineView;
import PatientManagement.GuiViews.SecretaryMenuView;
import PatientManagement.Model.Accounts.Account;
import PatientManagement.Model.Accounts.AccountListSingleton;
import PatientManagement.Model.Accounts.LoginSystemSingleton;
import PatientManagement.Model.Accounts.Secretary;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.MedicineOrder;
import PatientManagement.Model.Medicines.OrderRequestSingleton;
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
       
        this.view.addGiveMedicineListener(new GiveMedicineListener());
        this.view.addOrderMedicineListener(new OrderMedicineListener());
	this.view.addBackListener(new BackListener());
	this.view.addLogOutListener(new LogOutListener());
        
        refreshMedicineJList();
        refreshPatientJList();
        refreshOrderRequestJList();
    }
    
    private void refreshPatientJList()
    {
        ArrayList<Account> patientList = getPatientList();
        populatePatientJList(patientList);
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
            medicineStringList.add(medicine.getMedicineId() + " Name: " + medicine.getName() + " Quantity: " 
                    + medicine.getQuantity() + medicine.getQuantityInformation() + " Price unit: " + medicine.getPrice() + " Stock: " + medicine.getAmountInStock());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : medicineStringList)
        {
            model.addElement(element);
        }

        view.setLstMedicines(model);
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
    
    private int getSelectedMedicineId()
    {
        String details = view.getLstMedicines().getSelectedValue();

        String medicineId;
        int x = details.indexOf("Name:");

        medicineId = details.substring(0, x-1);
        
        return Integer.parseInt(medicineId);
    }
    
    private void refreshOrderRequestJList()
    {
        ArrayList<MedicineOrder> orderList = getOrderList();
        populateOrderRequestJList(orderList);
    }
    
    private ArrayList<MedicineOrder> getOrderList()
    {
        OrderRequestSingleton orders = OrderRequestSingleton.getInstance();
        
        return orders.getOrderList();
    }
    
    private void populateOrderRequestJList(ArrayList<MedicineOrder> orderList)
    {
        ArrayList<String> orderStringList = new ArrayList<String>();
        
        for (MedicineOrder order : orderList)
        {
            orderStringList.add(order.getOrderId() + " Name: " 
                    + order.getMedicine().getName() + " Amount to order: " + order.getAmountToOrder());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : orderStringList)
        {
            model.addElement(element);
        }
        
        view.setLstOrderRequests(model);
    }
    
    private MedicineOrder getSelectedOrderRequest()
    {
        String details = view.getLstOrderRequests().getSelectedValue();

        String medicineId;
        int index = details.indexOf(" Name:");
        medicineId = details.substring(0, index);
        
        OrderRequestSingleton orders = OrderRequestSingleton.getInstance();
        MedicineOrder selectedOrder = orders.getOrder(Integer.parseInt(medicineId));
        
        return selectedOrder;
    }
    
    public class GiveMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                StockSingleton medicineList = StockSingleton.getInstance();
                int amountToGive = view.getSpnAmountToGive();
                int medicineId = getSelectedMedicineId();
            
                boolean success = medicineList.giveMedicine(medicineId, amountToGive);  
                
                if (success)
                {
                    JOptionPane.showMessageDialog(null, "The medicine has been given to the patient!");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "There is not enough medicine in the stock!");
                }
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not give the medicine to patient! Check if medicine is selected.");
            }
            
            refreshMedicineJList();
        }
        
    }
    
    public class OrderMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            MedicineOrder order = getSelectedOrderRequest();
            
            try
            {
                model.orderMedicine(order);
                JOptionPane.showMessageDialog(null, "The medicine has been ordered!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not order the medicine!");
            }
            
            refreshMedicineJList();
            refreshOrderRequestJList();
        }
        
    }	
	
    public class BackListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            SecretaryMenuView newView = new SecretaryMenuView();
            newView.setLocation(view.getLocation());
            view.dispose();
            SecretaryMenuController menuController = new SecretaryMenuController(newView, model);
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
