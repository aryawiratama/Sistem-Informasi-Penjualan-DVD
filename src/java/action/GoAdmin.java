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
import org.apache.log4j.PropertyConfigurator;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class GoAdmin implements ActionInterface{
private static org.apache.log4j.Logger logger;

    public String execute(HttpServletRequest request) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
        logger = org.apache.log4j.Logger.getLogger(GoAdmin.class.getPackage().getName());
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
            logger.info("Admin Logout");
        }
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
            Logger.getLogger(GoAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        return page;
    }

}
