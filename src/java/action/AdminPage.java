/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Category;
import model.Customer;
import model.DVD;
import java.util.logging.Logger;
import org.apache.log4j.PropertyConfigurator;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class AdminPage implements ActionInterface{
private static org.apache.log4j.Logger logger;
    public String execute(HttpServletRequest request) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
        logger = org.apache.log4j.Logger.getLogger(AdminPage.class.getPackage().getName());
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
                logger.info("Admin Login");
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
