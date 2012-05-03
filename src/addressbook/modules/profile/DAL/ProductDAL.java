/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.modules.profile.DAL;

import addressbook.infrastructure.convertors.ConverterException;
import addressbook.infrastructure.convertors.ConverterFabric;
import addressbook.mocks.profile.AddressMock;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;
import addressbook.modules.profile.models.ProductModel;
import java.sql.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jeka
 */
public class ProductDAL {

    private String UpdateAddress = "UPDATE " + AddressMock.TABLE_NAME + " SET personid=? , address=? WHERE personid=?";
    private String UpdatePhones = "UPDATE " + PhoneMock.TABLE_NAME + " SET personid=? , phone=? WHERE personid=?";
    private String UpdatePersons = "UPDATE " + PersonMock.TABLE_NAME + " SET firstname=? , "
            + "lastname=? birthdate=?, email=? WHERE id=?";
    private String InsertAddress = "INSERT INTO " + AddressMock.TABLE_NAME + " VALUES (? , ? , ?)";
    private String InsertPhones = "INSERT INTO " + PhoneMock.TABLE_NAME + " VALUES (? , ? , ?)";
    private String InsertPersons = "INSERT INTO " + PersonMock.TABLE_NAME + " VALUES (? , ? , ? , ? , ?)";
    private String SelectAdresses = "SELECT * FROM " + AddressMock.TABLE_NAME + " WHERE personid=?";
    private String SelectPhones = "SELECT * FROM " + PhoneMock.TABLE_NAME + " WHERE personid=?";
    private String SelectPersons = "SELECT * FROM " + PersonMock.TABLE_NAME + " WHERE id=?";

    /**
     *
     */
    public ProductDAL() {
    }

    /**
     *
     * @param name
     * @param passw
     * @param db_url
     * @param driverName
     */
    public ProductDAL(String name, String passw, String db_url, String driverName) {
        this.m_login = name;
        this.m_password = passw;
        this.m_url = db_url;
        this.m_driverName = driverName;
    }

    /**
     *
     * @param id
     * @return
     */
    public ProductModel getProductBuID(int id) throws SQLException {
        return null;
    }

    /**
     * Get AddressMock instance with specifics personId
     *
     * @param id - personId of the address mock
     * @return - AddressMock object
     * @throws SQLException
     */
    public AddressMock getAdressMockByID(int personid) throws SQLException, ConverterException {
        //Create DB connection (within tranzaction)
        if (null == m_connection) {
            m_connection = createConnection();
        }
        //Compile prepared state
        PreparedStatement state = m_connection.prepareStatement(SelectAdresses);
        //fill the preparedStatemant with id
        state.setInt(1, personid);
        //get Result Set's to obtain the result of the query
        ResultSet set = state.executeQuery();
        //get new Instanse from concreate convertor
        AddressMock mock = (AddressMock) ConverterFabric.GetInstance(AddressMock.class).ToObject(getDictionaryFromSet(set));
        return mock;
    }

    /**
     *
     * @param mock
     * @return
     * @throws SQLException
     */
    protected PhoneMock getPhoneMockByID(PhoneMock mock) throws SQLException {

        return null;
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    protected PersonMock getPersonMockByID(int personid) throws SQLException {
        return null;
    }

    /**
     * Commit DB transaction and close connection
     *
     * @throws SQLException
     */
    public void CommitTransaction() throws SQLException {
        try {
            m_connection.commit();
        } catch (Exception ex) {
            //catch any commint exception and rollback tranzaction
            m_connection.rollback();
            Logger.getLogger(ProductDAL.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //close connection
            m_connection.close();
        }
    }

    public void SetPassword(String pass) {
        if (null != pass) {
            m_password = pass;
        }
    }

    public void SetLogin(String login) {
        if (null != login) {
            m_login = login;
        }
    }

    public void SetDriverName(String name) {
        if (null != name) {
            m_driverName = name;
        }
    }

    public void SetDataBaseUrl(String url) {
        if (null != url) {
            m_url = url;
        }
    }

    /**
     * Create SQL Connection
     *
     * @return - new Connection
     * @throws SQLException
     */
    private Connection createConnection() throws SQLException {
        try {
            Class.forName(m_driverName);
            Connection connection = DriverManager.getConnection(m_url, m_login, m_password);
            //Create transaction by and setting false to autocommit
            connection.setAutoCommit(false);
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Class not FOUND");
        }
    }

    private HashMap<String, Object> getDictionaryFromSet(ResultSet set) throws SQLException {
        //Some code example
        //Create dictionary, that will be puted to the converter
        HashMap<String, Object> dictionary = new HashMap<>();
        set.next();
        //get metadata to obtain the names of the colums
        ResultSetMetaData rsMetaData = set.getMetaData();
        //get count of the colums 
        int count = rsMetaData.getColumnCount();
        //fill dictionary with data      
        for (int i = 1; i <= count; i++) {
            String columnName = rsMetaData.getColumnName(i);
            //System.out.println(columnName.toLowerCase() + " _  " + set.getObject(i));
            dictionary.put(columnName.toLowerCase(), set.getObject(i));
        }
        return dictionary;
    }
    private Connection m_connection = null;
    private String m_url = "jdbc:h2:~/test";
    private String m_login = "sa";
    private String m_password = "";
    private String m_driverName = "org.h2.Driver";
}
