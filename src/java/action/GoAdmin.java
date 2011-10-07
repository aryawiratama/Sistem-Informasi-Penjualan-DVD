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
public class GoAdmin implements ActionInterface{

    public String execute(HttpServletRequest request) {
        String page = null;
        List<Category> categories = new ArrayList<Category> ();
        HttpSession session = request.getSession(true);
        Customer customer = (Customer) session.getAttribute("admin");
        try {
        if (customer==null){
            page = "loginadmin.jsp";
        }else{
            List <DVD> dvds = new ArrayList<DVD> ();
            dvds = Koneksi.getdVDDao().readAll();
            request.setAttribute("categories", categories);
            request.setAttribute("status", "Login");
            session.removeAttribute("admin");
            page = "controller?action=home";
        }
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
            Logger.getLogger(GoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        return page;
    }

}
