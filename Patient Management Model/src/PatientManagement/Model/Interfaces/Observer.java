/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Model.Interfaces;

/**
 *
 * @author Davio
 */
public interface Observer 
{

    /**
     * Observer pattern. Updates the Observer object after the change to Observable object.
     * @param o Observable object
     */
    public void update(Observable o);
}
