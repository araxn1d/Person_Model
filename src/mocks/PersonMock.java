/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jeka
 */
public class PersonMock implements INullable, IAssignable, IBinarySerializable {

    public static final String TABLE_NAME = "Persons";
    public static final int MAX_LENGTH = 30;

    public static boolean validate(PersonMock mock) {
        boolean result = true;
        if (mock.getId() <= 0 || mock.m_isNull || null == mock) {
            result = false;
        }
        return result;
    }

    public PersonMock() {
    }

    public PersonMock(int id, String firstName, String lastName, String birthDate) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEMail(eMail);
    }

    @Override
    public boolean assignTo(Object obj) {
        boolean ans = false;
        if ((obj != null) && (obj instanceof PersonMock)) {
            PersonMock mock = (PersonMock) obj;

            mock.setId(this.getId());
            mock.setFirstName(this.getFirstName());
            mock.setLastName(this.getLastName());
            mock.setBirthDate(this.getBirthDate());
            mock.setEMail(this.getEMail());

            ans = true;
        }
        return ans;
    }
    
    @Override
    public Object read(InputStream stream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean write(OutputStream stream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isNull() {
        return m_isNull;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String m_eMail) {
        this.eMail = cutString(m_eMail, PersonMock.MAX_LENGTH);
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String m_BirthDate) {
        this.birthDate = cutString(m_BirthDate, PersonMock.MAX_LENGTH);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String m_LastName) {
        this.lastName = cutString(m_LastName, PersonMock.MAX_LENGTH);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String m_FirstName) {
        this.firstName = cutString(m_FirstName, PersonMock.MAX_LENGTH);
    }

    public int getId() {
        return id;
    }

    public void setId(int m_id) {
        this.id = m_id;
    }

    private String cutString(String prop, int l) {
        if (prop.length() > l) {
            prop = prop.substring(0, l - 1);
        }
        return prop;
    }
    private int id = 0;
    private String firstName = null;
    private String lastName = null;
    private String birthDate = null;
    private String eMail = null;
    private boolean m_isNull = false;

}
