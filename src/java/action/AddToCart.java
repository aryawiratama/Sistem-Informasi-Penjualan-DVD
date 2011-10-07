/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.Detail1_1Impl;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.Category;
import model.DVD;
import model.Order;
import model.OrderDetail;
import util.Koneksi;

/**
 *
 * @author bandenk
 */
public class AddToCart implements ActionInterface{

    public String execute(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        int qty = Integer.parseInt(request.getParameter("quantity"));
        List<Category> categories = new ArrayList<Category>();
        List<DVD> dvds = new ArrayList<DVD>();
        HttpSession session = request.getSession(true);
        try {
            categories = Koneksi.getCategoryDao().read();
            dvds = Koneksi.getdVDDao().readAll();
            DVD dvd = Koneksi.getdVDDao().readById(id);
            OrderDetail detail = new OrderDetail();
            detail.setJumlah(qty);
            detail.setDvd(dvd);
            Order order = (Order) session.getAttribute("cart");
            if (order==null){
                order = new Order();
            }
            order.getDetail().add(detail);
            session.setAttribute("cart", order);
        } catch (Exception ex) {
            Logger.getLogger(AddToCart.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("categories", categories);
        request.setAttribute("dvds", dvds);
        return "home.jsp";
    }

}
