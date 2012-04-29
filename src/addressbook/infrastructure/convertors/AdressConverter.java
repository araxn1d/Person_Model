/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

import java.util.HashMap;
import addressbook.infrastructure.interfaces.IConvertable;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.mocks.profile.AddressMock;

/**
 *
 * @author Jeka
 */
public class AdressConverter implements IConvertable {

    @Override
    public INullable toObject(HashMap<String, String> dictionary) throws ConverterException {
        AddressMock mock = new AddressMock();
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
                AddressMock amock = (AddressMock) mock;
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
