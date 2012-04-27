/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.util.HashMap;
import mocks.INullable;

/**
 *
 * @author Jeka
 */
public interface IConverter {
    public INullable toObject(HashMap<String,String> dictionary) throws ConverterException;
    public HashMap<String,String> toDictionary(INullable mock) throws ConverterException;
}
