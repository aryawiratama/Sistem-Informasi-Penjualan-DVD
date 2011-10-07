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
public class GoUpdateDvd implements ActionInterface{

    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        List<Category> categories = new ArrayList<Category>();
        try {
            DVD dvd = Koneksi.getdVDDao().readById(id);
            categories = Koneksi.getCategoryDao().read();
            request.setAttribute("dvd", dvd);
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(GoUpdateDvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "update.jsp";
    }

}
