/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author bandenk
 */
public interface ActionInterface {

    public String execute(HttpServletRequest request);
}
