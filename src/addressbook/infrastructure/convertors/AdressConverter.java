/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

import addressbook.infrastructure.interfaces.IConvertable;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.mocks.profile.AddressMock;
import java.util.HashMap;

/**
 *
 * @author Jeka
 */
public class AdressConverter implements IConvertable {

    @Override
    public INullable toObject(HashMap<String, Object> dictionary) throws ConverterException {
        AddressMock mock = new AddressMock();
        if (dictionary != null) {
            try {
                mock.setId((Integer)dictionary.get("ID"));
                mock.setPerson_id((Integer)dictionary.get("Person_ID"));
                mock.setAdress((String)dictionary.get("Adress"));
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return mock;
    }

    @Override
    public HashMap<String, Object> toDictionary(INullable mock) throws ConverterException {
        HashMap<String, Object> map = new HashMap<>();
        if (mock != null) {
            try {
                AddressMock amock = (AddressMock) mock;
                map.put("ID",amock.getId());
                map.put("Person_ID", amock.getPerson_id());
                map.put("Adress", amock.getAdress());
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
