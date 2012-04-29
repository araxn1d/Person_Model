/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.mocks.profile;

import addressbook.infrastructure.convertors.ByteConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import addressbook.infrastructure.interfaces.IAssignable;
import addressbook.infrastructure.interfaces.IBinarySerializable;
import addressbook.infrastructure.interfaces.ICloneable;
import addressbook.infrastructure.interfaces.INullable;

/**
 *
 * @author Jeka
 */
public class PhoneMock implements INullable, IAssignable, IBinarySerializable, ICloneable {

    public static final String TABLE_NAME = "Phones";
    public static final int MAX_LENGTH = 30;

    public static boolean validate(PhoneMock mock) {
        boolean ans = true;
        if (mock.getId() <= 0 || mock.getPerson_id() <= 0 || mock.isNull() || null == mock) {
            ans = false;
        }
        return ans;
    }

    public PhoneMock() {
    }

    public PhoneMock(int id, int person_id, String phone) {
        this.setId(id);
        this.setPerson_id(person_id);
        this.setPhone(phone);
    }

    @Override
    public Object clone() {
        PhoneMock phoneMock = new PhoneMock();
        assignTo(phoneMock);
        return phoneMock;
    }

    @Override
    public boolean assignTo(Object obj) {
        boolean ans = false;
        if ((obj != null) && (obj instanceof PhoneMock)) {
            PhoneMock mock = (PhoneMock) obj;

            mock.setId(this.getId());
            mock.setPerson_id(this.getPerson_id());
            mock.setPhone(this.getPhone());

            ans = true;
        }
        return ans;
    }

    @Override
    public Object read(InputStream stream) {
        try {
            //Cr8 new byte array that will contain the mock's fields
            //like id [4 bytes] and phone[max 100 bytes]
            byte[] in = new byte[4 + 4 + 100];

            //fill byte array from the stream
            stream.read(in);

            byte[] id_b = new byte[4];
            byte[] person_id_b = new byte[4];
            byte[] phone_b = new byte[in.length - 8];

            //copy bytes from filled byte array(in) to mock's fields byte arrays
            System.arraycopy(in, 0, id_b, 0, 4);
            System.arraycopy(in, 4, person_id_b, 0, 4);
            System.arraycopy(in, 8, phone_b, 0, phone_b.length);

            //set the mock's properties,trim the empty bytes of the String
            this.setId(ByteConverter.byteArrayToInt(id_b));
            this.setPerson_id(ByteConverter.byteArrayToInt(person_id_b));
            this.setPhone(new String(phone_b, "UTF-8").trim());

            //return true if method ran succesfull else return false
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            return false;
        }
    }

    @Override
    public boolean write(OutputStream stream) {
        try {
            //Cr8 new byte array that will contain the mock's fields
            //like id [4 bytes] and phone [max 100 bytes]
            byte[] out = new byte[4 + 4 + 100];

            //fill byte arrays that represents mock's fields
            byte[] id_b = ByteConverter.intToByteArray(this.getId());
            byte[] person_id_b = ByteConverter.intToByteArray(this.getPerson_id());
            byte[] phone_b = this.getPhone().getBytes("UTF-8");

            //copy data to out array,that will be write to the stream
            System.arraycopy(id_b, 0, out, 0, 4);
            System.arraycopy(person_id_b, 0, out, 4, 4);
            System.arraycopy(phone_b, 0, out, 8, phone_b.length);
            stream.write(out);

            //return true if method ran succesfull else return false
            return true;
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        } finally {
            return false;
        }
    }

    @Override
    public boolean isNull() {
        return m_isNull;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = cutString(phone, PhoneMock.MAX_LENGTH);
    }

    public int getId() {
        return id;
    }

    public void setId(int m_id) {
        this.id = m_id;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    private String cutString(String prop, int l) {
        if (prop.length() > l) {
            prop = prop.substring(0, l - 1);
        }
        return prop;
    }
    private String phone = null;
    private int person_id = 0;
    private int id = 0;
    private boolean m_isNull = false;
}
