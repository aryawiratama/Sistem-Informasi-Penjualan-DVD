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
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class GoRegistration implements ActionInterface {

    public String execute(HttpServletRequest request) {
        List<Category> categories = new  ArrayList<Category>();
        HttpSession session = request.getSession(true);
        Customer customer = (Customer) session.getAttribute("admin");
        if (customer == null){
            request.setAttribute("status", "Login");
        }else{
            request.setAttribute("status", "Logout");
        }
        try {
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
        }
        request.setAttribute("categories", categories);
        return "registration.jsp";
    }
}
