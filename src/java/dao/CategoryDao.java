/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Category;

/**
 *
 * @author bandenk
 */
public interface CategoryDao {
public void insert(Category category)throws Exception;
public void delete(long id)throws Exception;
public void update(long oldId,Category category)throws Exception;
public List<Category> read ()throws Exception;
public Category getId(long id) throws Exception;
}
