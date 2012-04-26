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
    public Object read(OutputStream stream) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean write(InputStream stream) {
        throw new UnsupportedOperationException("Not supported yet.");
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