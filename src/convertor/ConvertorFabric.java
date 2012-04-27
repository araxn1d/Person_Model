/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package convertor;

import mocks.AdressMock;
import mocks.INullable;
import mocks.PersonMock;
import mocks.PhoneMock;

/**
 *
 * @author Jeka
 */
public class ConvertorFabric {

    private ConvertorFabric() {
    }

    public static IConvertor getInstance(Class clazz) {
        IConvertor ans = null;
        if (clazz.getName().equals(PersonMock.class.getName())) {
            ans = new PersonConvertor();
        }
        if (clazz.getName().equals(PhoneMock.class.getName())) {
            ans = new PhoneConvertor();
        }
        if (clazz.getName().equals(AdressMock.class.getName())) {
            ans = new AdressConvertor();
        }
        return ans;
    }
}
