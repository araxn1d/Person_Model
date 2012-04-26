/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertor;

import java.lang.reflect.Method;
import java.util.HashMap;
import mocks.AdressMock;
import mocks.INullable;

/**
 *
 * @author Jeka
 */
public class AdressConvertor implements IConvertor {

    @Override
    public INullable toObject(HashMap<String, String> dictionary) throws ConverterException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public HashMap<String, String> toDictionary(INullable mock) throws  ConverterException{
        HashMap<String, String> map = new HashMap<String, String>();
        AdressMock amock = (AdressMock) mock;
        
        map.put("ID", String.valueOf(amock.getId()));
        map.put("Person_ID", String.valueOf(amock.getPerson_id()));
        map.put("Adress", amock.getAdress());        
        
        return map;
    }
}
