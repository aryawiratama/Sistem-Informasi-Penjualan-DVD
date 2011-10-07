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
import model.DVD;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class GoHome implements ActionInterface{

    public String execute(HttpServletRequest request) {
        List<Category> categories = new ArrayList<Category>();
        List<DVD> dvds = new ArrayList<DVD>();
        HttpSession session = request.getSession(true);
        Customer customer = (Customer) session.getAttribute("admin");
        String page = null;
        if (customer == null){
            request.setAttribute("status", "Login");
            page = "home.jsp";
        }else{
            request.setAttribute("status", "Logout");
            page="admin.jsp";
        }
        try {
            categories = Koneksi.getCategoryDao().read();
            dvds = Koneksi.getdVDDao().readAll();
        } catch (Exception ex) {

        }
        request.setAttribute("categories", categories);
        request.setAttribute("dvds", dvds);
        return page;
    }

}
