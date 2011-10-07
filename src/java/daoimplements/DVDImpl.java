/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package daoimplements;

import dao.DVDDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Category;
import model.DVD;

/**
 *
 * @author bandenk
 */
public class DVDImpl implements DVDDao{
   private Connection connection;

    public DVDImpl(Connection connection) {
        this.connection = connection;
    }

    public void insert(DVD dvd) throws Exception {
        String sql = "INSERT INTO dvd (title,description,category_id,price)VALUES(?,?,?,?)";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, dvd.getJudul());
        statement.setString(2,dvd.getDescription());
        statement.setLong(3, dvd.getCategory().getId());
        statement.setDouble(4, dvd.getPrice());
        statement.executeUpdate();
        statement.close();
    }

    public void delete(long id) throws Exception {
        String sql = "DELETE FROM dvd WHERE id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setLong(1, id);
        statement.executeUpdate();
        statement.close();
    }

    public void update(long oldId, DVD dvd) throws Exception {
        String sql = "UPDATE dvd set title = ?, description = ?, category_id = ? ," +
                "price = ? where id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setString(1, dvd.getJudul());
        statement.setString(2,dvd.getDescription());
        statement.setLong(3, dvd.getCategory().getId());
        statement.setDouble(4, dvd.getPrice());
        statement.setLong(5, oldId);
        statement.executeUpdate();
        statement.close();
    }

    public DVD readById(long id) throws Exception {
        DVD dvd = null;
        String sql = "SELECT * FROM dvd WHERE id = ?";
        PreparedStatement statement= this.connection.prepareStatement(sql);
        statement.setLong(1, id);
        ResultSet rs = statement.executeQuery();
        while (rs.next()){
            dvd = new DVD();
            dvd.setId(rs.getLong(1));
            dvd.setJudul(rs.getString(2));
            dvd.setDescription(rs.getString(3));
            dvd.setCategory(new CategoryImpl(this.connection).getId(rs.getLong(4)));
            dvd.setPrice(rs.getDouble(5));
        }
        return dvd;
    }

    public List<DVD> readAll() throws Exception {
        String sql = "SELECT * FROM dvd";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        ResultSet rs  = statement.executeQuery();
        List<DVD> list = new ArrayList<DVD>();
        while (rs.next()){
            DVD dvd  = new DVD();
            dvd.setId(rs.getLong(1));
            dvd.setJudul(rs.getString(2));
            dvd.setDescription(rs.getString(3));
            dvd.setCategory(new CategoryImpl(this.connection).getId(rs.getLong(4)));
            dvd.setPrice(rs.getDouble(5));
            list.add(dvd);
        }
        rs.close();
        statement.close();
        return list;
    }

    public List<DVD> readByCategory(long categoryId) throws Exception {
        String sql = "SELECT * FROM dvd WHERE category_id = ?";
        PreparedStatement statement = this.connection.prepareStatement(sql);
        statement.setLong(1,categoryId);
        ResultSet rs = statement.executeQuery();
        List<DVD> list = new ArrayList<DVD>();
         while (rs.next()){
            DVD dvd  = new DVD();
            dvd.setId(rs.getLong(1));
            dvd.setJudul(rs.getString(2));
            dvd.setDescription(rs.getString(3));
            dvd.setCategory(new CategoryImpl(this.connection).getId(rs.getLong(4)));
            dvd.setPrice(rs.getDouble(5));
            list.add(dvd);
        }
        rs.close();
        statement.close();
        return list;
    }
}
