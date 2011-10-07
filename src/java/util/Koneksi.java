/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import dao.CategoryDao;
import dao.CustomerDao;
import dao.DVDDao;
import dao.OrderDao;
import daoimplements.CategoryImpl;
import daoimplements.CustomerImpl;
import daoimplements.DVDImpl;
import daoimplements.OrderImpl;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author bandenk
 */
public class Koneksi {
private static Connection connection;
private static CategoryDao categoryDao;
private static CustomerDao customerDao;
private static DVDDao dVDDao;
private static OrderDao orderDao;

    private Koneksi(){

    }

    private static Connection getConnection() {
        if(connection==null){
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/dvd","root","bandenk");
            } catch (Exception ex) {
            }
        }
        return connection;
    }

    public static CategoryDao getCategoryDao() {
        if (categoryDao==null){
            categoryDao = new CategoryImpl(getConnection());
        }
        return categoryDao;
    }

    public static CustomerDao getCustomerDao() {
        if(customerDao== null){
            customerDao = new CustomerImpl(getConnection());
        }
        return customerDao;
    }

    public static OrderDao getOrderDao() {
        if(orderDao==null){
            orderDao = new OrderImpl(getConnection());
        }
        return orderDao;
    }

    public static DVDDao getdVDDao() {
        if(dVDDao==null){
            dVDDao = new DVDImpl(getConnection());
        }
        return dVDDao;
    }

}
