/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import java.io.InputStream;
import java.io.OutputStream;
import javax.naming.BinaryRefAddr;
import sun.security.krb5.internal.EncAPRepPart;

/**
 *
 * @author Jeka
 */
public class AdressMock implements INullable, IAssignable, IBinarySerializable {

    public static final String TABLE_NAME = "Adresses";
    public static final int MAX_LENGTH = 30;

    public static boolean validate(AdressMock mock) {
        boolean ans = true;
        if (mock.getId() <= 0 || mock.getPerson_id() <= 0
                || mock.isNull() || null == mock) {

            ans = false;
        }
        return ans;
    }

    public AdressMock() {
    }

    public AdressMock(int id, int person_id, String adress) {
        this.setId(id);
        this.setPerson_id(person_id);
        this.setAdress(adress);
    }

    @Override
    public boolean assignTo(Object obj) {
        boolean ans = false;
        if ((obj != null) && (obj instanceof AdressMock)) {
            AdressMock mock = (AdressMock) obj;

            mock.setId(this.getId());
            mock.setPerson_id(this.getPerson_id());
            mock.setAdress(this.getAdress());

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
        byte[] input=new byte[4+4+100];
        byte[] id_b=ByteConvertor.intToByteArray(this.getId());
        byte[] person_id_b=ByteConvertor.intToByteArray(this.getPerson_id());
       // byte[] adress_b=this.getAdress().getBytes("UTF-8");
        return false;
    }

    @Override
    public boolean isNull() {
        return m_isNull;
    }

    public void setId(int m_id) {
        this.id = m_id;
    }

    public int getId() {
        return id;
    }

    public void setAdress(String adress) {
        this.adress = cutString(adress, AdressMock.MAX_LENGTH);
    }

    public String getAdress() {
        return adress;
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
   
    private int id = 0;
    private int person_id = 0;
    private String adress = null;
    private boolean m_isNull = false;
}
