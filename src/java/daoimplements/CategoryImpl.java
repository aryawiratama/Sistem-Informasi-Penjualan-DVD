/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoimplements;

import dao.CategoryDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author bandenk
 */
public class CategoryImpl implements CategoryDao{
private Connection connection;
public CategoryImpl (Connection connection){
    this.connection = connection;
}
    public void insert(Category category) throws Exception {
        String sql = "INSERT INTO category (name)values(?)";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, category.getName());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE category WHERE id=?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public void update(long oldId, Category category) throws Exception {
        String sql = "UPDATE category set name = ? where id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, category.getName());
        statement.setLong(2, oldId);
        statement.executeUpdate();
        statement.close();
    }

    public List<Category> read() throws Exception {
        String sql = "SELECT * FROM category";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet rs  = statement.executeQuery();
        List<Category> list = new ArrayList<Category>();
        while (rs.next()){
            Category category = new Category();
            category.setId(rs.getLong(1));
            category.setName(rs.getString(2));
            list.add(category);
        }
        rs.close();
        statement.close();
        return list;
    }

    public Category getId(long id) throws Exception {
        String sql = "SELECT id,name FROM category where id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        Category category = null;
        if (rs.next()){
            category = new Category();
            category.setId(rs.getLong(1));
            category.setName(rs.getString(2));
        }
        rs.close();
        statement.close();
        return category;
    }
}
