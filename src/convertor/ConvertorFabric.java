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

    public static IConvertor getInstance(INullable mock) {
        IConvertor ans = null;
        if (mock.getClass().getName().equals(PersonMock.class.getSimpleName())) {
            ans = new PersonConvertor();
        }
        if (mock.getClass().getName().equals(PhoneMock.class.getSimpleName())) {
            ans = new PhoneConvertor();
        }
        if (mock.getClass().getName().equals(AdressMock.class.getSimpleName())) {
            ans = new AdressConvertor();
        }
        return ans;
    }
}
