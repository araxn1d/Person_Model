/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.mocks.profile;

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
public class AddressMock implements INullable, IAssignable, IBinarySerializable, ICloneable {

    public static final String TABLE_NAME = "Adresses";
    public static final int MAX_LENGTH = 30;

    public static boolean validate(AddressMock mock) {
        boolean ans = true;
        if (mock.getId() <= 0 || mock.getPerson_id() <= 0
                || mock.isNull() || null == mock) {

            ans = false;
        }
        return ans;
    }

    public AddressMock() {
    }

    public AddressMock(int id, int person_id, String adress) {
        this.setId(id);
        this.setPerson_id(person_id);
        this.setAdress(adress);
    }

    @Override
    public Object clone() {
        AddressMock addressMock = new AddressMock();
        assignTo(addressMock);
        return addressMock;
    }

    @Override
    public boolean assignTo(Object obj) {
        boolean ans = false;
        if ((obj != null) && (obj instanceof AddressMock)) {
            AddressMock mock = (AddressMock) obj;

            mock.setId(this.getId());
            mock.setPerson_id(this.getPerson_id());
            mock.setAdress(this.getAdress());

            ans = true;
        }
        return ans;
    }

    @Override
    public Object read(InputStream stream) {
        try {
            //Cr8 new byte array that will contain the mock's fields
            //like id [4 bytes] and adress[max 100 bytes]
            byte[] in = new byte[4 + 4 + 100];

            //fill byte array from the stream
            stream.read(in);

            byte[] id_b = new byte[4];
            byte[] person_id_b = new byte[4];
            byte[] adress_b = new byte[in.length - 8];

            //copy bytes from filled byte array(in) to mock's fields byte arrays
            System.arraycopy(in, 0, id_b, 0, 4);
            System.arraycopy(in, 4, person_id_b, 0, 4);
            System.arraycopy(in, 8, adress_b, 0, adress_b.length);

            //set the mock's properties,trim the empty bytes of the String
            this.setId(ByteConverter.byteArrayToInt(id_b));
            this.setPerson_id(ByteConverter.byteArrayToInt(person_id_b));
            this.setAdress(new String(adress_b, "UTF-8").trim());

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
            //like id [4 bytes] and adress [max 100 bytes]
            byte[] out = new byte[4 + 4 + 100];

            //fill byte arrays that represents mock's fields
            byte[] id_b = ByteConverter.intToByteArray(this.getId());
            byte[] person_id_b = ByteConverter.intToByteArray(this.getPerson_id());
            byte[] adress_b = this.getAdress().getBytes("UTF-8");

            //copy data to out array,that will be write to the stream
            System.arraycopy(id_b, 0, out, 0, 4);
            System.arraycopy(person_id_b, 0, out, 4, 4);
            System.arraycopy(adress_b, 0, out, 8, adress_b.length);
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

    public void setId(int m_id) {
        this.id = m_id;
    }

    public int getId() {
        return id;
    }

    public void setAdress(String adress) {
        this.adress = cutString(adress, AddressMock.MAX_LENGTH);
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
