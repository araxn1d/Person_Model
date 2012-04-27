/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orm;

import convertor.ConverterException;
import convertor.ConvertorFabric;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import mocks.ByteConvertor;
import mocks.PersonMock;
import mocks.PhoneMock;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author Jeka
 */
public class NewTest {

    public NewTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void utfStringToByteLengthTest() {
        String s = "str";
        try {
            System.out.println(s.getBytes("UTF-8").length);
            assertTrue(s.getBytes("UTF-8").length == 3);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void intToByteTest() {
        int id = 10;
        int l = ByteConvertor.intToByteArray(id).length;
        assertTrue(l == 4);
    }

    @Test
    public void byteToIntTest() {
        int id = 10;
        byte[] l = ByteConvertor.intToByteArray(id);
        assertTrue(id == ByteConvertor.byteArrayToInt(l));
    }

    @Test
    public void binarySerializtionTest() {
        byte[] out = new byte[4 + 4 + 100];
        try {
            byte[] id_b = ByteConvertor.intToByteArray(10);
            byte[] person_id_b = ByteConvertor.intToByteArray(11);
            byte[] adress_b = "String".getBytes("UTF-8");

            System.arraycopy(id_b, 0, out, 0, 4);
            System.arraycopy(person_id_b, 0, out, 4, 4);
            System.arraycopy(adress_b, 0, out, 8, adress_b.length);
            
            byte[] id_b1 = new byte[4];
            byte[] person_id_b1 = new byte[4];
            byte[] adress_b1 = new byte[out.length - 8];

            System.arraycopy(out, 0, id_b1, 0, 4);
            System.arraycopy(out, 4, person_id_b1, 0, 4);
            System.arraycopy(out, 8, adress_b1, 0, adress_b1.length);
            
            assertTrue(10 == ByteConvertor.byteArrayToInt(id_b1));
            assertTrue(11 == ByteConvertor.byteArrayToInt(person_id_b1));
            
            System.out.println(new String(adress_b1,"UTF-8").length());
            System.out.println(new String(adress_b1,"UTF-8").trim());
            
            assertTrue("String".equals(new String(adress_b1,"UTF-8").trim()));
            
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Test
    public void phoneConvertorTest() throws ConverterException{
        PhoneMock mock=new PhoneMock();
        mock.setId(10);
        mock.setPerson_id(11);
        mock.setPhone("9999");
        HashMap<String,String> dictionary=ConvertorFabric.getInstance(PhoneMock.class).toDictionary(mock);
        PhoneMock mock2=null;
        mock2=(PhoneMock)ConvertorFabric.getInstance(PhoneMock.class).toObject(dictionary);
        assertTrue(mock.getId()==mock2.getId());
        assertTrue(mock.getPerson_id()==mock2.getPerson_id());
        assertTrue(mock.getPhone().equals(mock2.getPhone()));
    }
    
    @Test
    public void personConvertorTest() throws ConverterException{
        PersonMock mock=new PersonMock();
        mock.setId(10);
        mock.setFirstName("jopa");
        mock.setLastName("pipa");
        mock.setBirthDate("1992");
        mock.setEMail("vasia@mai.ru");
        HashMap<String,String> dictionary=ConvertorFabric.getInstance(PersonMock.class).toDictionary(mock);       
        PersonMock mock2=(PersonMock)ConvertorFabric.getInstance(PersonMock.class).toObject(dictionary);
        assertTrue(mock.getId()==mock2.getId());
        assertTrue(mock.getFirstName().equals(mock2.getFirstName()));
        assertTrue(mock.getLastName().equals(mock2.getLastName()));
        assertTrue(mock.getBirthDate().equals(mock2.getBirthDate()));
        assertTrue(mock.getEMail().equals(mock2.getEMail()));
    }
}
