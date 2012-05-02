/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

import addressbook.infrastructure.interfaces.IConvertable;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.mocks.profile.PhoneMock;
import java.util.HashMap;

/**
 *
 * @author Jeka
 */
public class PhoneConverter implements IConvertable {

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IConvertable#ToObject(java.util.HashMap)
     *
     */
    @Override
    public INullable ToObject(HashMap<String, Object> dictionary) throws ConverterException {
        PhoneMock mock = new PhoneMock();
        if (dictionary != null) {
            try {
                mock.SetId((Integer) dictionary.get("id"));
                mock.SetPersonId((Integer) dictionary.get("personid"));
                mock.SetPhone((String) dictionary.get("phone"));
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
                PhoneMock amock = (PhoneMock) mock;
                map.put("id", amock.GetId());
                map.put("personid", amock.GetPersonId());
                map.put("phone", amock.GetPhone());
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
