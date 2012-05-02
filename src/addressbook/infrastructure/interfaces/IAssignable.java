/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.interfaces;

/**
 *
 * @author Jeka
 */
public interface IAssignable {

    /**
     * Copy the data of current object to the target object
     *
     * @param obj - the object that will contains the copies of the current
     * object fields.
     * @return true if the method was successful , false if method failed
     */
    public boolean AssignTo(Object obj);
}
