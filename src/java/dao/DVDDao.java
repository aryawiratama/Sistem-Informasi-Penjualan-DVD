/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.DVD;

/**
 *
 * @author bandenk
 */
public interface DVDDao {
public void insert (DVD dvd)throws Exception;
public void delete (long id)throws Exception;
public void update (long oldId, DVD dvd)throws Exception;
public DVD readById (long id)throws Exception;
public List <DVD> readAll()throws Exception;
public List<DVD> readByCategory(long categoryId)throws Exception;
}
