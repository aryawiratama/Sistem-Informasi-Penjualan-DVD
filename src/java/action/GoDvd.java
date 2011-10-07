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
import model.Category;
import model.DVD;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class GoDvd implements ActionInterface{

    public String execute(HttpServletRequest request) {
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
            Logger.getLogger(GoDvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("status", "Logout");
        request.setAttribute("categories", categories);
        return "dvd.jsp";
    }

}
