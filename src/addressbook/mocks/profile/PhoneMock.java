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
import java.util.Random;

/**
 *
 * @author Jeka
 */
public class PhoneMock implements INullable, IAssignable, IBinarySerialize, ICloneable {
    
    public static final String ENCODING = "UTF-8";
    public static final String TABLE_NAME = "Phones";
    public static final int MAX_LENGTH = 30;
    private static final int HASH_CONST = 29;

    /**
     * @return new nullable instance of AdressMock
     */
    public static PhoneMock getNullableInstance() {
        PhoneMock mock = new PhoneMock();
        mock.m_isNull = true;
        return mock;
    }

    /**
     * @param mock - PhoneMock object, fields of which should be validated
     * @return true if validation was success , false if validation failed
     */
    public static boolean validate(PhoneMock mock) {
        boolean ans = true;
        if (mock.GetId() <= 0 || mock.GetPersonId() <= 0 || mock.IsNull() || null == mock) {
            ans = false;
        }
        return ans;
    }

    /**
     * Empty constructor of AddressMock class
     */
    public PhoneMock() {
        m_phone = null;
        m_person_id = 0;
        m_id = 0;
        m_isNull = false;
    }

    /**
     * @param id - the unique identifier of entry in the table
     * @param person_id - the identifier of person that owns the phone
     * @param phone - the phone of the person
     */
    public PhoneMock(int id, int person_id, String phone) {
        this.SetId(id);
        this.SetPersonId(person_id);
        this.SetPhone(phone);
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.ICloneable#Clone()
     */
    @Override
    public Object Clone() {
        PhoneMock phoneMock = new PhoneMock();
        AssignTo(phoneMock);
        return phoneMock;
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
        if ((obj != null) && (obj instanceof PhoneMock)) {
            PhoneMock mock = (PhoneMock) obj;
            
            mock.SetId(this.GetId());
            mock.SetPersonId(this.GetPersonId());
            mock.SetPhone(this.GetPhone());
            
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
            int personId;
            String phone = null;

            // read int values that represent as 4 byte
            id = input.readInt();
            personId = input.readInt();
            // read phone length
            int phoneLength = input.readInt();
            // create array to store phone if it length not equal -1
            if (phoneLength == 0) {
                phone = "";
            }
            if (phoneLength > 0) {
                byte[] phoneBytes = new byte[phoneLength];
                // read phone bytes and create String value
                input.read(phoneBytes, 0, phoneLength);
                phone = new String(phoneBytes, ENCODING);
            }
            //set values to the object
            this.SetId(id);
            this.SetPersonId(personId);
            this.SetPhone(phone);
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

            //writes id of person that owns the address to the stream
            stream.write(ByteConverter.IntToByteArray(this.GetPersonId()));

            //-1 if the address is null
            if (null == this.GetPhone()) {
                stream.write(ByteConverter.IntToByteArray(-1));
            }
            if (this.GetPhone().length() == 0) {
                stream.write(ByteConverter.IntToByteArray(0));
            }            
            if (this.GetPhone().length() > 0) {
                //wtites the length of the phone stream
                byte[] towrite = this.GetPhone().getBytes(ENCODING);
                stream.write(ByteConverter.IntToByteArray(towrite.length));
                stream.write(towrite);
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
        final PhoneMock other = (PhoneMock) obj;
        if (!Objects.equals(this.m_phone, other.m_phone)) {
            return false;
        }
        if (this.m_person_id != other.m_person_id) {
            return false;
        }
        if (this.m_id != other.m_id) {
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
        int hash = 3;
        hash = HASH_CONST * hash + Objects.hashCode(this.m_phone);
        hash = HASH_CONST * hash + this.m_person_id;
        hash = HASH_CONST * hash + this.m_id;
        hash = HASH_CONST * hash + (this.m_isNull ? 1 : 0);
        return hash;
    }
    
    public String GetPhone() {
        return m_phone;
    }
    
    public void SetPhone(String phone) {
        if (null != phone) {
            this.m_phone = cutString(phone, PhoneMock.MAX_LENGTH);
        }
    }
    
    public int GetId() {
        return m_id;
    }
    
    public void SetId(int m_id) {
        this.m_id = m_id;
    }
    
    public int GetPersonId() {
        return m_person_id;
    }
    
    public void SetPersonId(int person_id) {
        this.m_person_id = person_id;
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
    private String m_phone = null;
    private int m_person_id = 0;
    private int m_id = 0;
    private boolean m_isNull = false;
}
