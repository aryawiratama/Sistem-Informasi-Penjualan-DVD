/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoimplements;

import dao.CustomerDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Customer;

/**
 *
 * @author bandenk
 */
public class CustomerImpl implements CustomerDao{
private Connection connection;
public CustomerImpl (Connection connection){
    this.connection = connection;
}
    public void insert(Customer customer) throws Exception {
        String sql = "INSERT INTO customers (user_name,password,real_name" +
                ",address1,address2,city,state,zip,email,phone) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, customer.getUsername());
        statement.setString(2, customer.getPassword());
        statement.setString(3, customer.getRealname());
        statement.setString(4, customer.getAddress1());
        statement.setString(5, customer.getAddress2());
        statement.setString(6, customer.getCity());
        statement.setString(7, customer.getState());
        statement.setString(8, customer.getZip());
        statement.setString(9, customer.getEmail());
        statement.setString(10, customer.getPhone());
        statement.executeUpdate();
        statement.close();
    }

    public void update(long oldId, Customer customer) throws Exception {
        String sql = "UPDATE customers set user_name = ?,password = ?,reaal_name = ?" +
                ", address1 = ?,address2 = ?,city = ?, state = ?,zip = ? ,email = ? ,phone = ? " +
                "where id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, customer.getUsername());
        statement.setString(2, customer.getPassword());
        statement.setString(3, customer.getRealname());
        statement.setString(4, customer.getAddress1());
        statement.setString(5, customer.getAddress2());
        statement.setString(6, customer.getCity());
        statement.setString(7, customer.getState());
        statement.setString(8, customer.getZip());
        statement.setString(9, customer.getEmail());
        statement.setString(10, customer.getPhone());
        statement.setLong(11, oldId);
        statement.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM customers Where id =?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public Customer readById(long id) throws Exception {
        String sql = "SELECT * FROM customers where id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        Customer customer = null;
        while (rs.next()){
            customer = new Customer();
            customer.setId(rs.getLong(1));
            customer.setUsername(rs.getString(2));
            customer.setPassword(rs.getString(3));
            customer.setRealname(rs.getString(4));
            customer.setAddress1(rs.getString(5));
            customer.setAddress2(rs.getString(6));
            customer.setCity(rs.getString(7));
            customer.setState(rs.getString(8));
            customer.setZip(rs.getString(9));
            customer.setEmail(rs.getString(10));
            customer.setPhone(rs.getString(11));
        }
        rs.close();
        statement.close();
        return customer;
    }

    public List<Customer> readAll() throws Exception {
        String sql = "SELECT * FROM customers ";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet  rs = statement.executeQuery();
        List<Customer> list = new ArrayList<Customer>();
        while (rs.next()){
        Customer  customer = new Customer();
        customer.setId(rs.getLong(1));
        customer.setUsername(rs.getString(2));
        customer.setPassword(rs.getString(3));
        customer.setRealname(rs.getString(4));
        customer.setAddress1(rs.getString(5));
        customer.setAddress2(rs.getString(6));
        customer.setCity(rs.getString(7));
        customer.setState(rs.getString(8));
        customer.setZip(rs.getString(9));
        customer.setEmail(rs.getString(10));
        customer.setPhone(rs.getString(11));
        list.add(customer);
        }
        rs.close();
        statement.close();
        return list;
    }

    public Customer login(String username) throws Exception {
        Customer customer = null;
        String sql = "SELECT * FROM customers where user_name= ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            customer = new Customer();
            customer.setId(rs.getLong(1));
            customer.setUsername(rs.getString(2));
            customer.setPassword(rs.getString(3));
            customer.setRealname(rs.getString(4));
            customer.setAddress1(rs.getString(5));
            customer.setAddress2(rs.getString(6));
            customer.setCity(rs.getString(7));
            customer.setState(rs.getString(8));
            customer.setZip(rs.getString(9));
            customer.setEmail(rs.getString(10));
            customer.setPhone(rs.getString(11));
        }
        rs.close();
        statement.close();
        return customer;
    }

}
