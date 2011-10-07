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
public class SaveDvd implements ActionInterface{

    public String execute(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("category"));
        DVD dvd = new DVD();
        List<Category> categories = new ArrayList<Category>();
        List<DVD> dvds = new ArrayList<DVD>();
        try {
            categories = Koneksi.getCategoryDao().read();
            dvd.setJudul(request.getParameter("judul"));
            dvd.setPrice(Double.parseDouble(request.getParameter("price")));
            dvd.setDescription(request.getParameter("description"));
            dvd.setCategory(Koneksi.getCategoryDao().getId(id));
            Koneksi.getdVDDao().insert(dvd);
            dvds = Koneksi.getdVDDao().readAll();
        } catch (Exception ex) {
            Logger.getLogger(SaveDvd.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("dvds", dvds);
        request.setAttribute("categories", categories);
        request.setAttribute("msg", "Data Successfully Saved");
        return "admin.jsp";
    }

}
