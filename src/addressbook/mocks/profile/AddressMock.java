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
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

/**
 *
 * @author Jeka
 */
public class AddressMock implements INullable, IAssignable, IBinarySerializable, ICloneable {

    /**
     *
     */
    public static final String ENCODING = "UTF-8";
    public static final String TABLE_NAME = "Adresses";
    public static final int MAX_LENGTH = 30;

    /**
     * @return new nullable instance of AdressMock
     */
    public static AddressMock GetNullableInstance() {
        AddressMock mock = new AddressMock();
        mock.m_isNull = true;
        return mock;
    }

    /**
     * @param mock - AddressMock object, fields of which should be validated
     * @return true if validation was success , false if validation failed
     */
    public static boolean Validate(AddressMock mock) {
        boolean ans = true;
        if (mock.GetId() <= 0 || mock.GetPersonId() <= 0
                || mock.IsNull() || null == mock) {
            ans = false;
        }
        return ans;
    }

    /**
     * Empty constructor of AddressMock class
     */
    public AddressMock() {
        m_id = 0;
        m_person_id = 0;
        m_adress = null;
        m_isNull = false;
    }

    /**
     * @param id - the unique identifier of entry in the table
     * @param person_id - the identifier of person that owns the address
     * @param adress - the address of the person
     */
    public AddressMock(int id, int person_id, String adress) {
        this.SetId(id);
        this.SetPersonId(person_id);
        this.SetAddress(adress);
        m_isNull = false;
    }

    /**
     * (non-Javadoc)
     *
     * @see addressbook.infrastructure.interfaces.ICloneable#Clone()
     */
    @Override
    public Object Clone() {
        AddressMock addressMock = new AddressMock();
        AssignTo(addressMock);
        return addressMock;
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
        if ((obj != null) && (obj instanceof AddressMock)) {
            AddressMock mock = (AddressMock) obj;

            mock.SetId(this.GetId());
            mock.SetPersonId(this.GetPersonId());
            mock.SetAddress(this.GetAdress());

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
            String address = null;

            // read int values that represent as 4 byte
            id = input.readInt();
            personId = input.readInt();

            // read firstName length
            byte addressLength = (byte) input.readByte();
            // create array to store firstName if it length not equal -1
            if (addressLength != -1) {
                byte[] addressBytes = new byte[addressLength];
                // read address bytes and create String value
                input.read(addressBytes, 0, addressLength);
                address = new String(addressBytes, ENCODING);
            }
            //set values to the object
            this.SetId(id);
            this.SetPersonId(personId);
            this.SetAddress(address);
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
     */
    @Override
    public boolean Write(OutputStream stream) {
        try {
            //writes  id to the stream
            stream.write(ByteConverter.IntToByteArray(this.GetId()));

            //writes id of person that owns the address to the stream
            stream.write(ByteConverter.IntToByteArray(this.GetPersonId()));

            //-1 if the address is null
            if (null == this.GetAdress()) {
                stream.write(ByteConverter.IntToByteArray(-1));
            } else {
                //wtites the length of the address  to the stream

                byte[] towrite = this.GetAdress().getBytes(ENCODING);
                stream.write(ByteConverter.IntToByteArray(towrite.length));
                stream.write(towrite);
            }
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
        final AddressMock other = (AddressMock) obj;
        if (this.m_id != other.m_id) {
            return false;
        }
        if (this.m_person_id != other.m_person_id) {
            return false;
        }
        if (!Objects.equals(this.m_adress, other.m_adress)) {
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
        hash = 67 * hash + this.m_id;
        hash = 67 * hash + this.m_person_id;
        hash = 67 * hash + Objects.hashCode(this.m_adress);
        hash = 67 * hash + (this.m_isNull ? 1 : 0);
        return hash;
    }

    public void SetId(int m_id) {
        this.m_id = m_id;
    }

    public int GetId() {
        return m_id;
    }

    public void SetAddress(String address) {
        if (null != address) {
            this.m_adress = cutString(address, AddressMock.MAX_LENGTH);
        }
    }

    public String GetAdress() {
        return m_adress;
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
    private int m_id = 0;
    private int m_person_id = 0;
    private String m_adress = null;
    private boolean m_isNull = false;
}
