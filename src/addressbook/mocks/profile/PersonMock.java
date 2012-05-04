/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.mocks.profile;

import addressbook.infrastructure.convertors.ByteConverter;
import addressbook.infrastructure.interfaces.IAssignable;
import addressbook.infrastructure.interfaces.IBinarySerialize;
import addressbook.infrastructure.interfaces.ICloneable;
import addressbook.infrastructure.interfaces.INullable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 *
 * @author Jeka
 */
public class PersonMock implements INullable, IAssignable, IBinarySerialize, ICloneable {

    public static final String ENCODING = "UTF-8";
    public static final String TABLE_NAME = "Persons";
    public static final int MAX_LENGTH = 30;
    private static final int HASH_CONST = 67;

    /**
     * @return new nullable instance of AdressMock
     */
    public static PersonMock getNullableInstance() {
        PersonMock mock = new PersonMock();
        mock.m_isNull = true;
        return mock;
    }

    /**
     * @param mock - PersonMock object, fields of which should be validated
     * @return true if validation was success , false if validation failed
     */
    public static boolean validate(PersonMock mock) {
        boolean result = true;
        if (mock.GetId() <= 0 || mock.m_isNull || null == mock) {
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
     * @param id - the unique identifier of entry in the table
     * @param firstName - the first name of the person
     * @param lastName - the last name of the person
     * @param birthDate - the date of birth of the person
     * @param eMail - the email that owns person
     */
    public PersonMock(int id, String firstName, String lastName, String birthDate, String eMail) {
        this.SetId(id);
        this.SetFirstName(firstName);
        this.SetLastName(lastName);
        this.SetEMail(eMail);
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.ICloneable#Clone()
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
     */
    @Override
    public boolean AssignTo(Object obj) {
        boolean ans = false;
        if ((obj != null) && (obj instanceof PersonMock)) {
            PersonMock mock = (PersonMock) obj;

            mock.SetId(this.GetId());
            mock.SetFirstName(this.GetFirstName());
            mock.SetLastName(this.GetLastName());
            mock.SetBirthDate(this.GetBirthDate());
            mock.SetEMail(this.GetEMail());

            ans = true;
        }
        return ans;
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IBinarySerializable#Read(java.io.InputStream)
     */
    @Override
    public boolean Read(InputStream stream) {
        try {
            DataInputStream input = new DataInputStream(stream);

            // create variables to store read values
            int id;
            // create array of PersonMock fields to store read values
            String[] fields = new String[4];
            // read id
            id = input.readInt();

            // read and fill every field value if length of it is not
            //equals -1
            for (int i = 0; i < fields.length; i++) {
                fields[i] = null;
                byte length = (byte) input.readByte();
                if (length == 0) {
                    fields[i] = "";
                }
                if (length > 0) {
                    byte[] phoneBytes = new byte[length];
                    // read phone bytes and create String value
                    input.read(phoneBytes, 0, length);
                    fields[i] = new String(phoneBytes, ENCODING);
                }
            }
            //set values to the object
            this.SetId(id);
            this.SetFirstName(fields[0]);
            this.SetLastName(fields[1]);
            this.SetBirthDate(fields[2]);
            this.SetEMail(fields[3]);
            //return true if method ran succesfull else return false
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            return false;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see
     * addressbook.infrastructure.interfaces.IBinarySerializable#Write(java.io.OutputStream)
     */
    @Override
    public boolean Write(OutputStream stream) {
        try {
            //writes  id to the stream
            stream.write(ByteConverter.IntToByteArray(this.GetId()));

            //cr8 array of PersonMock String fields
            String[] fields = new String[4];
            fields[0] = this.GetFirstName();
            fields[1] = this.GetLastName();
            fields[2] = this.GetBirthDate();
            fields[3] = this.GetEMail();

            //check every field if it equals null then write -1,else field's
            //length and all field
            for (int i = 0; i < fields.length; i++) {
                if (null == fields[i]) {
                    stream.write(ByteConverter.IntToByteArray(-1));
                }
                if (fields[i].length() == 0) {
                    stream.write(ByteConverter.IntToByteArray(0));
                }
                if (fields[i].length() > 0) {
                    byte[] towrite = fields[i].getBytes(ENCODING);
                    stream.write(ByteConverter.IntToByteArray(towrite.length));
                    stream.write(towrite);
                }
            }
            //return true if method ran succesfull else return false
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            return false;
        }
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.INullable#IsNull()
     */
    @Override
    public boolean IsNull() {
        return m_isNull;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PersonMock other = (PersonMock) obj;
        if (this.m_id != other.m_id) {
            return false;
        }
        if (!Objects.equals(this.m_firstName, other.m_firstName)) {
            return false;
        }
        if (!Objects.equals(this.m_lastName, other.m_lastName)) {
            return false;
        }
        if (!Objects.equals(this.m_birthDate, other.m_birthDate)) {
            return false;
        }
        if (!Objects.equals(this.m_eMail, other.m_eMail)) {
            return false;
        }
        if (this.m_isNull != other.m_isNull) {
            return false;
        }
        return true;
    }

    /**
     * (non-Javadoc)
     *
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = HASH_CONST * hash + this.m_id;
        hash = HASH_CONST * hash + Objects.hashCode(this.m_firstName);
        hash = HASH_CONST * hash + Objects.hashCode(this.m_lastName);
        hash = HASH_CONST * hash + Objects.hashCode(this.m_birthDate);
        hash = HASH_CONST * hash + Objects.hashCode(this.m_eMail);
        hash = HASH_CONST * hash + (this.m_isNull ? 1 : 0);
        return hash;
    }

    public String GetEMail() {
        return m_eMail;
    }

    public void SetEMail(String eMail) {
        if (null != eMail) {
            this.m_eMail = cutString(eMail, PersonMock.MAX_LENGTH);
        }
    }

    public String GetBirthDate() {
        return m_birthDate;
    }

    public void SetBirthDate(String birthDate) {
        if (null != birthDate) {
            this.m_birthDate = cutString(birthDate, PersonMock.MAX_LENGTH);
        }
    }

    public String GetLastName() {
        return m_lastName;
    }

    public void SetLastName(String lastName) {
        if (null != lastName) {
            this.m_lastName = cutString(lastName, PersonMock.MAX_LENGTH);
        }
    }

    public String GetFirstName() {
        return m_firstName;
    }

    public void SetFirstName(String firstName) {
        if (null != firstName) {
            this.m_firstName = cutString(firstName, PersonMock.MAX_LENGTH);
        }
    }

    public int GetId() {
        return m_id;
    }

    public void SetId(int m_id) {
        this.m_id = m_id;
    }

    /**
     * @param prop - string that should be cuted
     * @param l - the maximum length of the prop parameter
     * @return cuted string
     */
    private String cutString(String prop, int l) {
        if (prop.length() > l) {
            prop = prop.substring(0, l - 1);
        }
        return prop;
    }
    private int m_id = 0;
    private String m_firstName = null;
    private String m_lastName = null;
    private String m_birthDate = null;
    private String m_eMail = null;
    private boolean m_isNull = false;
}
