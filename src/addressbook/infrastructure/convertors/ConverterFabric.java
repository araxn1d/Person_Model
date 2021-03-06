/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.infrastructure.convertors;

import addressbook.infrastructure.interfaces.IConvertable;
import addressbook.mocks.profile.AddressMock;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;

/**
 *
 * @author Jeka
 */
public class ConverterFabric {

    /**
     * Empty private constructor
     * not allowed to create ConverFabric objects
     */
    private ConverterFabric() {
    }
    /**
     * 
     * @param clazz - the value which determines the behavior of the fabric
     * @return - the concrete Fabric
     * @throws ConverterException 
     */
    public static IConvertable GetInstance(Class clazz) throws ConverterException  {
        if (clazz.getSimpleName().equals(PersonMock.class.getSimpleName())) {
            return new PersonConverter();
        }
        if (clazz.getSimpleName().equals(PhoneMock.class.getSimpleName())) {
            return new PhoneConverter();
        }
        if (clazz.getSimpleName().equals(AddressMock.class.getSimpleName())) {
            return new AddressConverter();
        }
        throw new ConverterException("Fabric exception");
    }
}
