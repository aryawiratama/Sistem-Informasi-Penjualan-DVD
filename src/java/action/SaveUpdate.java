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
import org.apache.log4j.PropertyConfigurator;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class SaveUpdate implements ActionInterface{
private static org.apache.log4j.Logger logger;

    public String execute(HttpServletRequest request) {
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
        logger = org.apache.log4j.Logger.getLogger(SaveUpdate.class.getPackage().getName());
        long id = Long.parseLong(request.getParameter("id"));
        List<Category> categories = new ArrayList<Category>();
        List<DVD> dvds = new ArrayList<DVD>();
        DVD dvd = new DVD();
        try {
            DVD oldDVD = Koneksi.getdVDDao().readById(id);
            dvd.setJudul(request.getParameter("judul"));
            dvd.setDescription(request.getParameter("description"));
            dvd.setPrice(Double.parseDouble(request.getParameter("price")));
            dvd.setCategory(Koneksi.getCategoryDao().getId(Long.parseLong(request.getParameter("category"))));
            Koneksi.getdVDDao().update(id, dvd);
            dvds = Koneksi.getdVDDao().readAll();
            categories = Koneksi.getCategoryDao().read();
            logger.info(logDvd(oldDVD, dvd));

        } catch (Exception ex) {
            Logger.getLogger(SaveUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("msg", "Data Successfuly Update");
        request.setAttribute("dvds", dvds);
        request.setAttribute("categories", categories);
        request.setAttribute("status", "Logout");
        return "admin.jsp";
    }

    private String logDvd(DVD old,DVD dvd){
        String log = null;
        if (!old.getDescription().equalsIgnoreCase(dvd.getDescription())){
            log = "Update DVD Description From " + old.getDescription() + " to " + dvd.getDescription();
        }else if (old.getPrice()!=dvd.getPrice()){
            log = "Update DVD Price From " + old.getPrice() + " to " + dvd.getPrice();
        }else if (!old.getJudul().equalsIgnoreCase(dvd.getJudul())){
            log = "Update DVD Tittle From " + old.getJudul() + " to " + dvd.getJudul();
        }else if (old.getCategory().getName().equalsIgnoreCase(dvd.getCategory().getName())){
            log = "Update DVD Category From " + old.getCategory().getName() + " to " + dvd.getCategory().getName();
        }
        return log;
    }
}
