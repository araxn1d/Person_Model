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
    public INullable toObject(HashMap<String,Object> dictionary) throws ConverterException;
    public HashMap<String,Object> toDictionary(INullable mock) throws ConverterException;
}