/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Customer;

/**
 *
 * @author bandenk
 */
public interface CustomerDao {
public void insert(Customer customer) throws Exception;
public void update(long oldId,Customer customer)throws Exception;
public void delete (long id)throws Exception;
public Customer readById(long id) throws Exception;
public List<Customer> readAll()throws Exception;
public Customer login(String username)throws Exception;
}
