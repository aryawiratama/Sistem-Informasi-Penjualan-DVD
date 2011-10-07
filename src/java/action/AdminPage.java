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
import model.Customer;
import model.DVD;
import sun.java2d.loops.CustomComponent;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class AdminPage implements ActionInterface{

    public String execute(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Category> categories = new ArrayList<Category>();
        List<DVD> dvds = new ArrayList<DVD>();
        String page = null;
        try {
        Customer customer = Koneksi.getCustomerDao().login(username);
        if (customer!=null){
            if (customer.getPassword().equals(password) && password.equals("admin")){
                HttpSession session = request.getSession(true);
                request.setAttribute("status", "Logout");
                session.setAttribute("admin", customer);
                page = "admin.jsp";
            }else{
                request.setAttribute("msg", "Password is Invalid");
                page = "controller?action=home";
            }
        }else{
            request.setAttribute("msg", "Username Not Found");
            page = "controller?action=home";
            }
            categories = Koneksi.getCategoryDao().read();
            dvds = Koneksi.getdVDDao().readAll();
        }catch (Exception ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("dvds", dvds);
        request.setAttribute("categories", categories);
        return page;
    }

}
