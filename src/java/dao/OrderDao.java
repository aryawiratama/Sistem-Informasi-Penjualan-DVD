/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Order;

/**
 *
 * @author bandenk
 */
public interface OrderDao {
    public void insert (Order order)throws Exception;
    public void delete (long id) throws Exception;
    public void update (long oldId, Order order)throws Exception;
    public Order readById(long ID) throws Exception;
    public List<Order> readAll() throws Exception;
    public List<Order> readByCustomer(long customerId)throws Exception;
}
