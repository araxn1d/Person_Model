/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.interfaces;

import addressbook.infrastructure.convertors.ConverterException;
import java.util.HashMap;

/**
 *
 * @author Jeka
 */
public interface IConvertable {

    /**
     * Convert dictionary to new Inullable instance
     *
     * @param dictionary - variable containing the key pair (field name) and
     * value of this field
     * @return - new instance of Inullable interface
     * @throws ConverterException
     */
    public INullable ToObject(HashMap<String, Object> dictionary) throws ConverterException;

    /**
     * Convert Inullable object to new dictionary instance
     *
     * @param mock - object that should be converted to the dictionary
     * @return - new dictionary instance
     * @throws ConverterException
     */
    public HashMap<String, Object> ToDictionary(INullable mock) throws ConverterException;
}
