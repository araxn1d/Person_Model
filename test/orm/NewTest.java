/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orm;

import addressbook.infrastructure.convertors.ConverterException;
import addressbook.infrastructure.convertors.ConverterFabric;
import addressbook.infrastructure.convertors.ByteConverter;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;
import org.junit.*;

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
        int l = ByteConverter.IntToByteArray(id).length;
        assertTrue(l == 4);
    }

    @Test
    public void byteToIntTest() {
        int id = 10;
        byte[] l = ByteConverter.IntToByteArray(id);
        assertTrue(id == ByteConverter.ByteArrayToInt(l));
    }

    @Test
    public void binarySerializtionTest() {
        byte[] out = new byte[4 + 4 + 100];
        try {
            byte[] id_b = ByteConverter.IntToByteArray(10);
            byte[] person_id_b = ByteConverter.IntToByteArray(11);
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

            assertTrue(10 == ByteConverter.ByteArrayToInt(id_b1));
            assertTrue(11 == ByteConverter.ByteArrayToInt(person_id_b1));

            System.out.println(new String(adress_b1, "UTF-8").length());
            System.out.println(new String(adress_b1, "UTF-8").trim());

            assertTrue("String".equals(new String(adress_b1, "UTF-8").trim()));

        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(NewTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void phoneConvertorTest() throws ConverterException {
        PhoneMock mock = new PhoneMock();
        mock.setId(10);
        mock.setPerson_id(11);
        mock.setPhone("9999");
        HashMap<String, Object> dictionary = ConverterFabric.GetInstance(PhoneMock.class).ToDictionary(mock);
        PhoneMock mock2 = (PhoneMock) ConverterFabric.GetInstance(PhoneMock.class).ToObject(dictionary);
        assertTrue(mock.getId() == mock2.getId());
        assertTrue(mock.getPerson_id() == mock2.getPerson_id());
        assertTrue(mock.getPhone().equals(mock2.getPhone()));
    }

    @Test
    public void personConvertorTest() throws ConverterException {
        PersonMock mock = new PersonMock();
        mock.setId(10);
        mock.setFirstName("Vasia");
        mock.setLastName("Vasilek");
        mock.setBirthDate("1992");
        mock.setEMail("vasia@mai.ru");
        HashMap<String, Object> dictionary = ConverterFabric.GetInstance(PersonMock.class).ToDictionary(mock);
        PersonMock mock2 = (PersonMock) ConverterFabric.GetInstance(PersonMock.class).ToObject(dictionary);
        assertTrue(mock.getId() == mock2.getId());
        assertTrue(mock.getFirstName().equals(mock2.getFirstName()));
        assertTrue(mock.getLastName().equals(mock2.getLastName()));
        assertTrue(mock.getBirthDate().equals(mock2.getBirthDate()));
        assertTrue(mock.getEMail().equals(mock2.getEMail()));
    }
    
    @Test
    public void daoTest(){
        Class clazz=String.class;
        HashMap<String,Class<?>> a=new HashMap<>();
        a.put("str", clazz);
    } 
}
