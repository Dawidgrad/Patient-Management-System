/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.SecretaryMedicineView;
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
    }
    
     
    private void RefreshMedicineJList()
    {
        ArrayList<Medicine> medicineList = GetMedicineList();
        PopulateMedicineJList(medicineList);
    }

    private ArrayList<Medicine> GetMedicineList()
    {
        StockSingleton medicines = StockSingleton.getInstance();

        ArrayList<Medicine> medicineList = new ArrayList<Medicine>();

        medicineList.addAll(medicines.GetMedicineList());

        return medicineList;
    }

    private void PopulateMedicineJList(ArrayList<Medicine> medicineList)
    {
        ArrayList<String> medicineStringList = new ArrayList<String>();

        for (Medicine medicine : medicineList)
        {
            medicineStringList.add(medicine.getMedicineId() + " Name: " + medicine.getName() + "\t Amount: " + medicine.getAmount() + "\t Price: " + medicine.getPrice());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : medicineStringList)
        {
            model.addElement(element);
        }

        view.setLstMedicines(model);
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
            int amountToGive = view.getSpnAmountToOrder();
            int medicineId = GetSelectedMedicineId();
            
            try
            {
                medicineList.OrderMedicine(medicineId, amountToGive);  
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not give the medicine to patient!");
            }
            
            RefreshMedicineJList();
        }
        
    }
}
