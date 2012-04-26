/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package product;

import mocks.AdressMock;
import mocks.PersonMock;
import mocks.PhoneMock;

/**
 *
 * @author Jeka
 */
public class ProductModel  {

    public ProductModel(PersonMock person, AdressMock adress, PhoneMock phone) {
        boolean a=person.assignTo(this.m_person);
        boolean b=adress.assignTo(this.m_adress);
        boolean c=phone.assignTo(this.m_phone);
        
        if(a && b && c){
            
        }
    }

    
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
        return m_adress.getAdress();
    }
    private AdressMock m_adress=null;
    private PhoneMock m_phone=null;
    private PersonMock m_person=null;
}
