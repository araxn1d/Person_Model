/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package addressbook.modules.profile.DAL;

import addressbook.infrastructure.convertors.ConverterException;
import addressbook.mocks.profile.AddressMock;
import addressbook.mocks.profile.PersonMock;
import addressbook.mocks.profile.PhoneMock;
import addressbook.modules.profile.models.ProductModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jeka
 */
public class ProductDAL {

    /**
     * 
     * @param name
     * @param passw
     * @param db_url
     * @param driverName 
     */
    public ProductDAL(String name, String passw, String db_url, String driverName) {
        this.m_name = name;
        this.m_password = passw;
        this.m_url = db_url;
        this.m_driverName = driverName;
    }

    /**
     * 
     * @param id
     * @return 
     */
    protected AddressMock getAdressMockByID(int id) {
        return null;
    }

    public PhoneMock getPhoneMockByID(PhoneMock mock) throws SQLException, ConverterException {
//        HashMap <String,Class<?>> conv=ConverterFabric.getInstance(PhoneMock.class).toDictionary(new PhoneMock(10,10,"99"));
//        createConnection();
//        Statement a=m_connection.createStatement();
//        a.execute("SELECT * FROM "+PhoneMock.TABLE_NAME+" WHERE ID="+mock.getPerson_id());
//        ResultSet set=a.getResultSet();
        return null;
    }

    protected PersonMock getPersonMockByID(int id) {
        return null;
    }

    public ProductModel getProductBuID(int id) {
        return null;
    }

    private void createConnection() throws SQLException {
        try {
            Class driverName = Class.forName(m_driverName);
            m_connection = DriverManager.getConnection(m_url, m_name, m_password);
            System.out.println("Connected.");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("Class not FOUND");
        }
    }
    private Connection m_connection = null;
    private String m_url = "jdbc:h2:~/test";
    private String m_name = "sa";
    private String m_password = "";
    private String m_driverName = "org.h2.Driver";
}
