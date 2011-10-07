/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import model.Category;
import model.Customer;
import model.DVD;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class SaveRegistration implements ActionInterface {

    public String execute(HttpServletRequest request) {
        Customer customer = new Customer();
        customer.setUsername(request.getParameter("userName"));
        customer.setPassword(request.getParameter("password"));
        customer.setRealname(request.getParameter("realName"));
        customer.setAddress1(request.getParameter("address1"));
        customer.setAddress2(request.getParameter("address2"));
        customer.setCity(request.getParameter("city"));
        customer.setState(request.getParameter("state"));
        customer.setZip(request.getParameter("zip"));
        customer.setEmail(request.getParameter("email"));
        customer.setPhone(request.getParameter("phone"));
        List<Category> categories = new ArrayList<Category>();
        List <DVD>  dvds = new ArrayList<DVD>();
        try {
            categories = Koneksi.getCategoryDao().read();
            dvds = Koneksi.getdVDDao().readAll();
            Koneksi.getCustomerDao().insert(customer);
        } catch (Exception ex) {

        }
        request.setAttribute("categories", categories);
        request.setAttribute("dvds", dvds);
        request.setAttribute("msg", "Your Registration is Successfull");
        return "home.jsp";
    }

}
