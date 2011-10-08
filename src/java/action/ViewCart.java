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
import model.Order;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class ViewCart implements ActionInterface{

    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        Order order = (Order) session.getAttribute("cart");
        List<Category> categories= new ArrayList<Category>();
        try {
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
        }
        request.setAttribute("cart", order);
        request.setAttribute("categories", categories);
        return "cart.jsp";
    }

}