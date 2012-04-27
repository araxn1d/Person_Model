/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.lang.reflect.Method;
import java.util.HashMap;
import mocks.AdressMock;
import mocks.INullable;

/**
 *
 * @author Jeka
 */
public class AdressConverter implements IConverter {

    @Override
    public INullable toObject(HashMap<String, String> dictionary) throws ConverterException {
        AdressMock mock = new AdressMock();
        if (dictionary != null) {
            try {
                mock.setId(Integer.valueOf(dictionary.get("ID")));
                mock.setPerson_id(Integer.valueOf(dictionary.get("Person_ID")));
                mock.setAdress(dictionary.get("Adress"));
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return mock;
    }

    @Override
    public HashMap<String, String> toDictionary(INullable mock) throws ConverterException {
        HashMap<String, String> map = new HashMap<String, String>();
        if (mock != null) {
            try {
                AdressMock amock = (AdressMock) mock;
                map.put("ID", String.valueOf(amock.getId()));
                map.put("Person_ID", String.valueOf(amock.getPerson_id()));
                map.put("Adress", amock.getAdress());
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
