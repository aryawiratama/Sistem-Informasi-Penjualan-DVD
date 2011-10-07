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
public class SaveUpdate implements ActionInterface{

    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        List<Category> categories = new ArrayList<Category>();
        List<DVD> dvds = new ArrayList<DVD>();
        DVD dvd = new DVD();
        try {
            dvd.setJudul(request.getParameter("judul"));
            dvd.setDescription(request.getParameter("description"));
            dvd.setPrice(Double.parseDouble(request.getParameter("price")));
            dvd.setCategory(Koneksi.getCategoryDao().getId(Long.parseLong(request.getParameter("category"))));
            Koneksi.getdVDDao().update(id, dvd);
            dvds = Koneksi.getdVDDao().readAll();
            categories = Koneksi.getCategoryDao().read();
        } catch (Exception ex) {
            Logger.getLogger(SaveUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("msg", "Data Successfuly Update");
        request.setAttribute("dvds", dvds);
        request.setAttribute("categories", categories);
        request.setAttribute("status", "Logout");
        return "admin.jsp";
    }

}
