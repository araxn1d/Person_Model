/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

import addressbook.infrastructure.interfaces.IConvertable;
import java.util.HashMap;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;

/**
 *
 * @author Jeka
 */
public class PersonConverter implements IConvertable {

    @Override
    public INullable toObject(HashMap<String, String> dictionary) throws ConverterException {
        PersonMock mock = new PersonMock();
        if (dictionary != null) {
            try {
                mock.setId(Integer.valueOf(dictionary.get("ID")));
                mock.setFirstName(dictionary.get("FirstName"));
                mock.setLastName(dictionary.get("LastName"));
                mock.setBirthDate(dictionary.get("BirthDate"));
                mock.setEMail(dictionary.get("eMail"));
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return mock;
    }

    @Override
    public HashMap<String, String> toDictionary(INullable mock) throws ConverterException {
        HashMap<String, String> map = new HashMap<>();
        if (mock != null) {
            try {
                PersonMock amock = (PersonMock) mock;
                map.put("ID", String.valueOf(amock.getId()));
                map.put("FirstName", String.valueOf(amock.getFirstName()));
                map.put("LastName", String.valueOf(amock.getLastName()));
                map.put("BirthDate", String.valueOf(amock.getBirthDate()));
                map.put("eMail", String.valueOf(amock.getEMail()));
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
