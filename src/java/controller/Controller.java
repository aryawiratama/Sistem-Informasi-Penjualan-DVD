/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import action.ActionInterface;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author bandenk
 */
public class Controller extends HttpServlet {
Properties properties;
RequestDispatcher dispatchers;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            properties = new Properties();
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("conf.properties");
            properties.load(is);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String reqPage = request.getParameter("action");
        String reqClass = properties.getProperty(reqPage);
        String page = null;
        if (reqClass!=null){
            try {
                ActionInterface helper = (ActionInterface) Class.forName(reqClass).newInstance();
                page=helper.execute(request);
            } catch (Exception ex) {
            }
            if (!page.contains("controller")){
                page = "WEB-INF/page/" + page;
            }
            dispatchers = request.getRequestDispatcher(page);
            dispatchers.forward(request, response);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
