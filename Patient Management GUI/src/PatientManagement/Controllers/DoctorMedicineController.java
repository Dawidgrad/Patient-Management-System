/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorMedicineView;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Medicines.Medicine;
import PatientManagement.Model.Medicines.MedicineOrder;
import PatientManagement.Model.Medicines.OrderRequestSingleton;
import PatientManagement.Model.Medicines.StockSingleton;
import PatientManagement.Model.Medicines.StockSingleton.MedicineType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Davio
 */
public class DoctorMedicineController 
{
    private DoctorMedicineView view;
    private Doctor model;
    
    public DoctorMedicineController(DoctorMedicineView view, Doctor model)
    {
        this.view = view;
        this.model = model;
        
        this.view.setVisible(true);
        
        this.view.addNewMedicineListener(new NewMedicineListener());
        this.view.addRequestOrderListener(new RequestOrderListener());
        
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
                    + "\t Quantity: " + medicine.getQuantity() + "\t Price: " + medicine.getPrice() + " Stock: " + medicine.getAmountInStock());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : medicineStringList)
        {
            model.addElement(element);
        }

        view.setLstMedicines(model);
    }
            
    private MedicineType getMedicineTypeSelection()
    {

        String typeText = "";

        for (Enumeration<AbstractButton> buttons = view.getGrpMedicineType().getElements(); buttons.hasMoreElements();) 
        {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                   typeText = button.getText();
            }
        }      

        MedicineType type;

        switch(typeText)
        {
            case "Tablet":
                type = MedicineType.TABLET;
                break;
            case "Liquid":
                type = MedicineType.TABLET;
                break;
            case "Capsule":
                type = MedicineType.TABLET;
                break;
            default:
                type = null;
        }

        return type;
    }
    
    private Medicine getSelectedMedicine()
    {
        String details = view.getLstMedicines().getSelectedValue();

        String medicineId;
        int index = details.indexOf(" Name:");

        medicineId = details.substring(0, index);
        
        StockSingleton stock = StockSingleton.getInstance();
        Medicine selectedMedicine = stock.getMedicine(Integer.parseInt(medicineId));
                
        return selectedMedicine;
    }
    
    public class NewMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String name = view.getTxtName();
                String description = view.getTxtDescription();
                int quantity = view.getSpnAmount();
                float price = view.getSpnPrice();
                MedicineType type = getMedicineTypeSelection();

                StockSingleton stock = StockSingleton.getInstance();
                stock.createNewMedicine(name, description, quantity, price, 0, type);
                JOptionPane.showMessageDialog(null, "Medicine added successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not add the medicine!");
            }
            
            refreshMedicineJList();
        }
        
    }
    
    public class RequestOrderListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                Medicine selectedMedicine = getSelectedMedicine();
                int amountToOrder = view.getSpnAmountToOrder();
                
                model.requestMedicineOrder(selectedMedicine, amountToOrder);
                JOptionPane.showMessageDialog(null, "Successfully requested Secretary to order the medicine!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not request Secretary to order the medicine!");
            }
            
            refreshMedicineJList();
        }
        
    }
}
