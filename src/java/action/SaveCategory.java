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
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class SaveCategory implements ActionInterface{

    public String execute(HttpServletRequest request) {
        String nama = request.getParameter("name");
        List<Category> categories = new ArrayList<Category> ();
        try {
            categories = Koneksi.getCategoryDao().read();
            Category category = new Category();
            category.setName(nama);
            Koneksi.getCategoryDao().insert(category);
        } catch (Exception ex) {
            Logger.getLogger(SaveCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        return "category.jsp";
    }

}
