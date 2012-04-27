/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import mocks.AdressMock;
import mocks.INullable;
import mocks.PersonMock;
import mocks.PhoneMock;

/**
 *
 * @author Jeka
 */
public class ConverterFabric {

    private ConverterFabric() {
    }

    public static IConverter getInstance(Class clazz) {
        IConverter ans = null;
        if (clazz.getName().equals(PersonMock.class.getName())) {
            ans = new PersonConverter();
        }
        if (clazz.getName().equals(PhoneMock.class.getName())) {
            ans = new PhoneConverter();
        }
        if (clazz.getName().equals(AdressMock.class.getName())) {
            ans = new AdressConverter();
        }
        return ans;
    }
}
