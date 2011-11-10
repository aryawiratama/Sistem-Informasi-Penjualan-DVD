/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author bandenk
 */
public class Logging implements Filter{
private FilterConfig filterConfig;
private static Logger logger;
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        PropertyConfigurator.configure(filterConfig.getServletContext().getRealPath("WEB-INF/classes/log4j.properties"));
        logger = Logger.getLogger(Logging.class.getPackage().getName());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
        String page = (String) request.getAttribute("page");
        logger.info("Web Accesed : " + page);
    }

    public void destroy() {
        
    }
}
