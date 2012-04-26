/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mocks.AdressMock;
import mocks.PersonMock;
import mocks.PhoneMock;
import product.ProductModel;

/**
 *
 * @author Jeka
 */
public class ProductDAO {

    public ProductDAO(String name, String passw, String db_url, String driverName) {
        this.m_name = name;
        this.m_password = passw;
        this.m_url = db_url;
        this.m_driverName = driverName;
    }

    protected AdressMock getAdressMockByID(int id) {
        return null;
    }

    protected PhoneMock getPhoneMockByID(int id) {
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        m_connection = DriverManager.getConnection(m_url, m_name, m_password);
        System.out.println("Connected.");
    }
    private Connection m_connection = null;
    private String m_url = "jdbc:h2:~/test";
    private String m_name = "sa";
    private String m_password = "";
    private String m_driverName = "org.h2.Driver";
}
