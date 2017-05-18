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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String sql = "INSERT INTO NEWS (url, title, feed, date) "
                    + "VALUES ('" + object.getUrl() + "', '" + object.getTitle() + "', " + object.getFeed() + ", '" + sdf.format(object.getDate()) + "');";
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
                int feed = rs.getInt("feed");
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = df.parse(rs.getString("date"));
                News n = new News();
                n.setId(id);
                n.setUrl(url);
                n.setFeed(feed);
                n.setTitle(title);
                n.setDate(date);
                news.add(n);
            }
            rs.close();
            stmt.close();
            return news;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }

    }

    @Override
    public News getLastNewsFromFeed(int id) {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        News n = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM news WHERE id = ("
                    + "select max(id) from news where feed = " + id + ");");
            while (rs.next()) {
                int i = rs.getInt("id");
                String url = rs.getString("url");
                String title = rs.getString("title");
                int feed = rs.getInt("feed");
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                Date date = df.parse(rs.getString("date"));
                n = new News();
                n.setId(i);
                n.setUrl(url);
                n.setFeed(feed);
                n.setTitle(title);
                n.setDate(date);
            }
            rs.close();
            stmt.close();
            return n;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

}
