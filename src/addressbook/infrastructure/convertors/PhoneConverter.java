/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;
import java.util.HashMap;
import addressbook.infrastructure.interfaces.IConvertable;
import addressbook.infrastructure.interfaces.INullable;
import addressbook.mocks.profile.PhoneMock;

/**
 *
 * @author Jeka
 */
public class PhoneConverter implements IConvertable {

    @Override
    public INullable toObject(HashMap<String, String> dictionary) throws ConverterException {
        PhoneMock mock = new PhoneMock();
        if (dictionary != null) {
            try {
                mock.setId(Integer.valueOf(dictionary.get("ID")));
                mock.setPerson_id(Integer.valueOf(dictionary.get("Person_ID")));
                mock.setPhone(dictionary.get("Phone"));
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
                PhoneMock amock = (PhoneMock) mock;
                map.put("ID", String.valueOf(amock.getId()));
                map.put("Person_ID", String.valueOf(amock.getPerson_id()));
                map.put("Phone", amock.getPhone());
            } catch (Throwable e) {
                throw new ConverterException(e.getMessage());
            }
        }
        return map;
    }
}
