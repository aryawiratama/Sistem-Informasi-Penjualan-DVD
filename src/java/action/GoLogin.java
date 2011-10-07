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
import javax.servlet.http.HttpSession;
import model.Category;
import model.Order;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class GoLogin implements ActionInterface{

    public String execute(HttpServletRequest request) {
        String page = null;
        HttpSession session = request.getSession(true);
        Order order = (Order) session.getAttribute("cart");
        if (order ==null){
            request.setAttribute("msg", "Your Cart Is Empty");
            page = "controller?action=home";
        }else if (order!=null){
            page = "login.jsp";
        }
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
            Logger.getLogger(GoLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        return page;
    }

}
