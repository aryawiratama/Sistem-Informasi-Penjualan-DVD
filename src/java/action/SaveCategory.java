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
import org.apache.log4j.PropertyConfigurator;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class SaveCategory implements ActionInterface{
private static org.apache.log4j.Logger logger;
    public String execute(HttpServletRequest request) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
        logger = org.apache.log4j.Logger.getLogger(SaveCategory.class.getPackage().getName());
        String nama = request.getParameter("name");
        List<Category> categories = new ArrayList<Category> ();
        try {
            categories = Koneksi.getCategoryDao().read();
            Category category = new Category();
            category.setName(nama);
            Koneksi.getCategoryDao().insert(category);
            logger.info("Insert New Category : " + nama);
        } catch (Exception ex) {
            Logger.getLogger(SaveCategory.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        return "category.jsp";
    }

}
