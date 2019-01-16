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
public interface Observable 
{

    /**
     * Observer pattern. Saves the observer instance.
     * @param o Observer instance
     */
    public void registerObserver(Observer o);

    /**
     * Observer pattern. Notifies the observers about the change with the Observable object.
     */
    public void notifyObserver();
}
