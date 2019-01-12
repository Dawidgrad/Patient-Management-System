/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Controllers;

import PatientManagement.GuiViews.DoctorMedicineView;
import PatientManagement.Model.Accounts.Doctor;
import PatientManagement.Model.Medicines.ConcreteMedicineFactory;
import PatientManagement.Model.Medicines.Medicine;
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
        
        this.view.AddNewMedicineListener(new NewMedicineListener());
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
            medicineStringList.add("Name: " + medicine.getName() + "\t Amount: " + medicine.getAmount() + "\t Price: " + medicine.getPrice());
        }

        DefaultListModel<String> model = new DefaultListModel<>();

        for (String element : medicineStringList)
        {
            model.addElement(element);
        }

        view.setLstMedicines(model);
    }
            
    private MedicineType GetMedicineTypeSelection()
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
    
    public class NewMedicineListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            try
            {
                String name = view.getTxtName();
                String description = view.getTxtDescription();
                int amount = view.getSpnAmount();
                float prive = view.getSpnPrice();
                MedicineType type = GetMedicineTypeSelection();

                StockSingleton stock = StockSingleton.getInstance();
                stock.CreateNewMedicine(name, description, amount, prive, type);
                JOptionPane.showMessageDialog(null, "Medicine added successfully!");
            }
            catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Could not add the medicine!");
            }
            
            RefreshMedicineJList();
        }
        
    }
}
