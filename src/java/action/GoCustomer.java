/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Category;
import model.Customer;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class GoCustomer implements ActionInterface{

    public String execute(HttpServletRequest request) {
        List<Category> categories = new ArrayList<Category>();
        List<Customer> customers = new ArrayList<Customer>();
        try {
            categories = Koneksi.getCategoryDao().read();
            customers = Koneksi.getCustomerDao().readAll();
        } catch (Exception ex) {
            Logger.getLogger(GoCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("customers", customers);
        request.setAttribute("categories", categories);
        return "customer.jsp";
    }

}
