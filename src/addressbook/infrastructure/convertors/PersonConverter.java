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

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IConvertable#ToObject(java.util.HashMap)
     *
     */
    @Override
    public INullable ToObject(HashMap<String, Object> dictionary) throws ConverterException {
        PersonMock mock = new PersonMock();
        if (dictionary != null) {
            try {
                mock.SetId((Integer) dictionary.get("id"));
                mock.SetFirstName((String) dictionary.get("firstname"));
                mock.SetLastName((String) dictionary.get("lastname"));
                mock.SetBirthDate((String) dictionary.get("birthdate"));
                mock.SetEMail((String) dictionary.get("email"));
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return mock;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IConvertable#ToDictionary(addressbook.infrastructure.interfaces.INullable)
     *
     */
    @Override
    public HashMap<String, Object> ToDictionary(INullable mock) throws ConverterException {
        HashMap<String, Object> map = new HashMap<>();
        if (mock != null) {
            try {
                PersonMock amock = (PersonMock) mock;
                map.put("id", amock.GetId());
                map.put("firstname", amock.GetFirstName());
                map.put("lastname", amock.GetLastName());
                map.put("birthdate", amock.GetBirthDate());
                map.put("email", amock.GetEMail());
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
