package action;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import model.Category;
import util.Koneksi;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bandenk
 */
public class GoBuy implements ActionInterface{

    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
            Logger.getLogger(GoBuy.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        request.setAttribute("id", id);
        return "buy.jsp";
    }

}
