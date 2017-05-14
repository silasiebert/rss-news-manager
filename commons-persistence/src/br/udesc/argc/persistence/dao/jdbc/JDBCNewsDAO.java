/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.jdbc;

import br.udesc.argc.persistence.dao.core.NewsDAO;
import br.udesc.argc.persistence.model.News;
import br.udesc.argc.persistence.utils.SQLiteJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabrielnaoto
 */
public class JDBCNewsDAO implements NewsDAO {

    @Override
    public void insert(News object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "INSERT INTO NEWS (url, title) "
                    + "VALUES ('" + object.getUrl() + "', '" + object.getTitle() + "');";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Record created successfully");
    }

    @Override
    public List<News> list() {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM news;");
            List<News> news = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String url = rs.getString("url");
                String title = rs.getString("title");
                News neww = new News();
                neww.setId(id);
                neww.setUrl(url);
                neww.setTitle(title);
                news.add(neww);
            }
            rs.close();
            stmt.close();
            return news;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

}
