/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.modules.profile.models;

import addressbook.mocks.profile.AddressMock;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;

/**
 *
 * @author Jeka
 */
public class ProductModel {

    /**
     * 
     * @param person
     * @param adress
     * @param phone
     * @return 
     */
    public static ProductModel getInstance(PersonMock person, AddressMock adress, PhoneMock phone) {
        return new ProductModel(person, adress, phone);
    }
    
    /**
     * 
     */
    private ProductModel() {
        this(new PersonMock(), new AddressMock(), new PhoneMock());
    }

    /**
     * 
     * @param person
     * @param adress
     * @param phone 
     */
    private ProductModel(PersonMock person, AddressMock adress, PhoneMock phone) {
        boolean a = person.AssignTo(this.m_person);
        boolean b = adress.AssignTo(this.m_adress);
        boolean c = phone.AssignTo(this.m_phone);

        if (a && b && c) {
        }
    }

    /**
     * 
     * @return 
     */
    public static boolean validate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Integer getId() {
        return m_person.getId();
    }

    public String getFirstName() {
        return m_person.getFirstName();
    }

    public String getLastName() {
        return m_person.getLastName();
    }

    public String getDate() {
        return m_person.getBirthDate();
    }

    public String getPhone() {
        return m_phone.getPhone();
    }

    public String getAdress() {
        return m_adress.GetAdress();
    }
    protected AddressMock m_adress = null;
    protected PhoneMock m_phone = null;
    protected PersonMock m_person = null;
}
