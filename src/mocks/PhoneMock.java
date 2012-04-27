/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Jeka
 */
public class PhoneMock implements INullable, IAssignable, IBinarySerializable {

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
            byte[] in = new byte[4+4+100];
            stream.read(in);

            byte[] id_b = new byte[4];
            byte[] person_id_b = new byte[4];
            byte[] phone_b = new byte[in.length - 8];

            System.arraycopy(in, 0, id_b, 0, 4);
            System.arraycopy(in, 4, person_id_b, 0, 4);
            System.arraycopy(in, 8, phone_b, 0, phone_b.length);

            this.setId(ByteConvertor.byteArrayToInt(id_b));
            this.setPerson_id(ByteConvertor.byteArrayToInt(person_id_b));
            this.setPhone(new String(phone_b, "UTF-8").trim());

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
            byte[] out = new byte[4 + 4 +100];

            byte[] id_b = ByteConvertor.intToByteArray(this.getId());
            byte[] person_id_b = ByteConvertor.intToByteArray(this.getPerson_id());
            byte[] phone_b = this.getPhone().getBytes("UTF-8");

            if (id_b.length == 4 && person_id_b.length == 4 && phone_b.length <= 30) {
                System.arraycopy(id_b, 0, out, 0, 4);
                System.arraycopy(person_id_b, 0, out, 4, 4);
                System.arraycopy(phone_b, 0, out, 8, phone_b.length);
                stream.write(out);
            }
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
