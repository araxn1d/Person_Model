/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertor;

import java.util.HashMap;
import mocks.INullable;

/**
 *
 * @author Jeka
 */
public class PersonConvertor implements IConvertor{

    @Override
    public INullable toObject(HashMap<String, String> dictionary) throws ConverterException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<String, String> toDictionary(INullable mock) throws ConverterException{
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
