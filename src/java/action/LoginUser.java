/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Customer;
import model.Order;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class LoginUser implements ActionInterface{

    public String execute(HttpServletRequest request) {
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        String page=null;
        HttpSession session = request.getSession(true);
        List<Category>  categories = new ArrayList<Category>();
        try {
            Customer customer = Koneksi.getCustomerDao().login(user);
            categories = Koneksi.getCategoryDao().read();
            Order order = (Order) session.getAttribute("cart");
            if (customer!=null){
                if (password.equals(customer.getPassword())){
                    request.setAttribute("msg", "Your Transaction Successfully Saved");
                    order.setCustomer(customer);
                    session.removeAttribute("cart");
                    Koneksi.getOrderDao().insert(order);
                    page = "controller?action=home";
                }else{
                    request.setAttribute("msg", "User Name Or password is Invalid");
                    page = "controller?action=login";
                }
            }else{
                request.setAttribute("msg", "User Name Or password is Invalid");
                page = "controller?action=login";
            }
        } catch (Exception ex) {
        }
        request.setAttribute("categories", categories);
        return page;
    }

}
