/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Medicines;

/**
 *
 * @author Davio
 */
public class TabletMedicine extends Medicine
{
    
    public TabletMedicine(int medicineId, String name, String description, int amount, float price)
    {
        super(medicineId, name, description, amount, price);
    }

    @Override
    public String GetInformation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}