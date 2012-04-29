/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

import addressbook.infrastructure.interfaces.IConvertable;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.mocks.profile.PersonMock;
import java.util.HashMap;

/**
 *
 * @author Jeka
 */
public class PersonConverter implements IConvertable {

    @Override
    public INullable toObject(HashMap<String, Object> dictionary) throws ConverterException {
        PersonMock mock = new PersonMock();
        if (dictionary != null) {
            try {
                mock.setId((Integer) dictionary.get("ID"));
                mock.setFirstName((String) dictionary.get("FirstName"));
                mock.setLastName((String) dictionary.get("LastName"));
                mock.setBirthDate((String) dictionary.get("BirthDate"));
                mock.setEMail((String) dictionary.get("eMail"));
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
                PersonMock amock = (PersonMock) mock;
                map.put("ID", amock.getId());
                map.put("FirstName", amock.getFirstName());
                map.put("LastName", amock.getLastName());
                map.put("BirthDate", amock.getBirthDate());
                map.put("eMail", amock.getEMail());
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
