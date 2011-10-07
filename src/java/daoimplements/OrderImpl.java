/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoimplements;

import dao.OrderDao;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderDetail;
import org.joda.time.DateTime;

/**
 *
 * @author bandenk
 */
public class OrderImpl implements OrderDao{
private Connection connection;

    public OrderImpl(Connection connection) {
        this.connection = connection;
    }

    public void insert(Order order) throws Exception {
        long genId = System.currentTimeMillis();
        DateTime date = new DateTime();
        String sql = "INSERT INTO orders (id,trans_date,customer_id) VALUES (?,?,?)";
        String sql1 = "INSERT INTO order_detail (orders_id,dvd_id,quantity)" +
                "VALUES (?,?,?)";
        this.connection.setAutoCommit(false);
        try{
            PreparedStatement statement = this.connection.prepareStatement(sql);
            statement.setLong(1,genId);
            statement.setString(2, date.toString("yyyy-MM-dd"));
            statement.setLong(3,order.getCustomer().getId());
            statement.executeUpdate();
            for(OrderDetail detail : order.getDetail()){
                PreparedStatement statement1 = this.connection.prepareStatement(sql1);
                statement1.setLong(1, genId);
                statement1.setLong(2, detail.getDvd().getId());
                statement1.setInt(3, detail.getJumlah());
                statement1.executeUpdate();
                this.connection.commit();
                statement1.close();
                statement1 = null;
            }
            statement.close();
            
        }catch(Exception exception){
            this.connection.rollback();
            throw exception;
        }finally{
            this.connection.setAutoCommit(true);
        }
    }

    public void delete(long id) throws Exception {
        String sql1 = "DELETE FROM orders WHERE id = ?";
        String sql2 = "DELETE FROM order_detail WHERE orders_id=?";
        this.connection.setAutoCommit(false);
        try{
            PreparedStatement statement1 = this.connection.prepareStatement(sql1);
            statement1.setLong(1, id);
            statement1.executeUpdate();
            PreparedStatement statement2 = this.connection.prepareStatement(sql2);
            statement2.setLong(1, id);
            statement2.executeUpdate();
            this.connection.commit();
            statement1.close();
            statement2.close();
        }catch(Exception exception){
            this.connection.rollback();
            throw exception;
        }finally{
            this.connection.setAutoCommit(true);
        }
    }

    public void update(long oldId, Order order) throws Exception {
        delete(oldId);
        insert(order);
    }

    public Order readById(long ID) throws Exception {
        Order order = null;
        String sql1 = "SELECT id,trans_date, customer_id FROM orders WHERE id=?";
        String sql2 = "SELECT id, dvd_id,quantity FROM order_detail WHERE orders_id= ?";
        PreparedStatement statement1 = this.connection.prepareStatement(sql1);
        PreparedStatement statement2 = this.connection.prepareStatement(sql2);
        statement1.setLong(1, ID);
        statement2.setLong(1, ID);
        ResultSet rs = statement1.executeQuery();
        if(rs.next()){
            order = new Order();
            order.setId(rs.getLong(1));
            order.setTransDate(new DateTime(rs.getString(2)).toDate());
            order.setCustomer(new CustomerImpl(this.connection).readById(rs.getLong(3)));
            ResultSet rs1 = statement2.executeQuery();
            while(rs1.next()){
                OrderDetail detail = new OrderDetail();
                detail.setId(rs1.getLong(1));
                detail.setDvd(new DVDImpl(this.connection).readById(rs.getLong(2)));
                detail.setJumlah(rs.getInt(3));
                order.getDetail().add(detail);
            }
        }
        return order;
    }

    public List<Order> readAll() throws Exception {
        List<Order> listOrder = new ArrayList<Order>();
        String sql1 = "SELECT id,trans_date, customer_id FROM orders";
        String sql2 = "SELECT id, dvd_id,quantity FROM order_detail";
        PreparedStatement statement1 = this.connection.prepareStatement(sql1);
        PreparedStatement statement2 = this.connection.prepareStatement(sql2);
        ResultSet rs = statement1.executeQuery();
        while(rs.next()){
            Order order = new Order();
            order.setId(rs.getLong(1));
            order.setTransDate(new DateTime(rs.getString(2)).toDate());
            order.setCustomer(new CustomerImpl(this.connection).readById(rs.getLong(3)));
            ResultSet rs1 = statement2.executeQuery();
            while(rs1.next()){
                OrderDetail detail = new OrderDetail();
                detail.setId(rs1.getLong(1));
                detail.setDvd(new DVDImpl(this.connection).readById(rs.getLong(2)));
                detail.setJumlah(rs.getInt(3));
                order.getDetail().add(detail);
            }
        }
        return listOrder;
    }

    public List<Order> readByCustomer(long customerId) throws Exception {
        List<Order> listOrder = new ArrayList<Order>();
        String sql1 = "SELECT id,trans_date, customer_id FROM orders WHERE customer_id = ?";
        String sql2 = "SELECT id, dvd_id,quantity FROM order_detail WHERE orders_id = ?";
        PreparedStatement statement1 = this.connection.prepareStatement(sql1);
        PreparedStatement statement2 = this.connection.prepareStatement(sql2);
        statement1.setLong(1, customerId);
        ResultSet rs = statement1.executeQuery();
        while(rs.next()){
            Order order = new Order();
            order.setId(rs.getLong(1));
            order.setTransDate(new DateTime(rs.getString(2)).toDate());
            order.setCustomer(new CustomerImpl(this.connection).readById(rs.getLong(3)));
            statement2.setLong(1, order.getId());
            ResultSet rs1 = statement2.executeQuery();
            while(rs1.next()){
                OrderDetail detail = new OrderDetail();
                detail.setId(rs1.getLong(1));
                detail.setDvd(new DVDImpl(this.connection).readById(rs.getLong(2)));
                detail.setJumlah(rs.getInt(3));
                order.getDetail().add(detail);
            }
        }
        return listOrder;
    }

}
