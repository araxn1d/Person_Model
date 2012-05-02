/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.mocks.profile;

import addressbook.infrastructure.convertors.ByteConverter;
import addressbook.infrastructure.interfaces.IAssignable;
import addressbook.infrastructure.interfaces.IBinarySerializable;
import addressbook.infrastructure.interfaces.ICloneable;
import addressbook.infrastructure.interfaces.INullable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jeka
 */
public class PersonMock implements INullable, IAssignable, IBinarySerializable, ICloneable {

    public static final String TABLE_NAME = "Persons";
    public static final int MAX_LENGTH = 30;

    /**
     *
     * @return new nullable instance of AdressMock
     */
    public static PersonMock getNullableInstance() {
        PersonMock mock = new PersonMock();
        mock.m_isNull = true;
        return mock;
    }

    /**
     *
     * @param mock - PersonMock object, fields of which should be validated
     * @return true if validation was success , false if validation failed
     */
    public static boolean validate(PersonMock mock) {
        boolean result = true;
        if (mock.getId() <= 0 || mock.m_isNull || null == mock) {
            result = false;
        }
        return result;
    }

    /**
     * Empty constructor of AddressMock class
     */
    public PersonMock() {
        m_id = 0;
        m_firstName = null;
        m_lastName = null;
        m_birthDate = null;
        m_eMail = null;
        m_isNull = false;
    }

    /**
     *
     * @param id - the unique identifier of entry in the table
     * @param firstName - the first name of the person
     * @param lastName - the last name of the person
     * @param birthDate - the date of birth of the person
     * @param eMail - the email that owns person
     */
    public PersonMock(int id, String firstName, String lastName, String birthDate, String eMail) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEMail(eMail);
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.ICloneable#Clone()
     *
     */
    @Override
    public Object Clone() {
        PersonMock personMock = new PersonMock();
        AssignTo(personMock);
        return personMock;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IAssignable#AssignTo(java.lang.Object)
     *
     */
    @Override
    public boolean AssignTo(Object obj) {
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

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IBinarySerializable#Read(java.io.InputStream)
     *
     */
    @Override
    public Object Read(InputStream stream) {
        try {
            //Cr8 new byte array that will contain the mock's fields
            //like id [4 bytes] and firstName [max 100 bytes]
            byte[] in = new byte[4 + 100 + 100 + 100 + 100];

            //fill byte array from the stream
            stream.read(in);

            byte[] id_b = new byte[4];
            byte[] fname_b = new byte[100];
            byte[] lname_b = new byte[100];
            byte[] birth_b = new byte[100];
            byte[] email_b = new byte[100];

            //copy bytes from filled byte array(in) to mock's fields byte arrays
            System.arraycopy(in, 0, id_b, 0, 4);
            System.arraycopy(in, 4, fname_b, 0, 100);
            System.arraycopy(in, 104, lname_b, 0, 100);
            System.arraycopy(in, 204, birth_b, 0, 100);
            System.arraycopy(in, 304, email_b, 0, 100);

            //set the mock's properties ,trim the empty bytes of the String
            this.setId(ByteConverter.ByteArrayToInt(id_b));
            this.setFirstName(new String(fname_b, "UTF-8").trim());
            this.setLastName(new String(lname_b, "UTF-8").trim());
            this.setBirthDate(new String(birth_b, "UTF-8").trim());
            this.setEMail(new String(email_b, "UTF-8").trim());

            //return true if method ran succesfull else return false
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            return false;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IBinarySerializable#Write(java.io.OutputStream)
     *
     */
    @Override
    public boolean Write(OutputStream stream) {
        try {
            //Cr8 new byte array that will contain the mock's fields
            //like id [4 bytes] and firstName [max 100 bytes]
            byte[] out = new byte[4 + 100 + 100 + 100 + 100];

            //fill byte arrays that represents mock's fields
            byte[] id_b = ByteConverter.IntToByteArray(this.getId());
            byte[] fname_b = this.getFirstName().getBytes("UTF-8");
            byte[] lname_b = this.getLastName().getBytes("UTF-8");
            byte[] birth_b = this.getBirthDate().getBytes("UTF-8");
            byte[] email_b = this.getEMail().getBytes("UTF-8");

            //copy data to out array,that will be write to the stream
            System.arraycopy(id_b, 0, out, 0, 4);
            System.arraycopy(fname_b, 0, out, 4, fname_b.length);
            System.arraycopy(lname_b, 0, out, 104, lname_b.length);
            System.arraycopy(birth_b, 0, out, 204, birth_b.length);
            System.arraycopy(email_b, 0, out, 304, email_b.length);

            stream.write(out);
            //return true if method ran succesfull else return false
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            return false;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.INullable#IsNull()
     *
     */
    @Override
    public boolean IsNull() {
        return m_isNull;
    }

    public String getEMail() {
        return m_eMail;
    }

    public void setEMail(String m_eMail) {
        this.m_eMail = cutString(m_eMail, PersonMock.MAX_LENGTH);
    }

    public String getBirthDate() {
        return m_birthDate;
    }

    public void setBirthDate(String m_BirthDate) {
        this.m_birthDate = cutString(m_BirthDate, PersonMock.MAX_LENGTH);
    }

    public String getLastName() {
        return m_lastName;
    }

    public void setLastName(String m_LastName) {
        this.m_lastName = cutString(m_LastName, PersonMock.MAX_LENGTH);
    }

    public String getFirstName() {
        return m_firstName;
    }

    public void setFirstName(String m_FirstName) {
        this.m_firstName = cutString(m_FirstName, PersonMock.MAX_LENGTH);
    }

    public int getId() {
        return m_id;
    }

    public void setId(int m_id) {
        this.m_id = m_id;
    }

    /**
     *
     * @param prop - string that should be cuted
     * @param l - the maximum length of the prop parameter
     * @return cuted string
     */
    private String cutString(String prop, int l) {
        if (null != prop) {
            if (prop.length() > l) {
                prop = prop.substring(0, l - 1);
            }
            return prop;
        } else {
            return "null String";
        }
    }
    private int m_id = 0;
    private String m_firstName = null;
    private String m_lastName = null;
    private String m_birthDate = null;
    private String m_eMail = null;
    private boolean m_isNull = false;
}
