/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.jdbc;

import br.udesc.argc.persistence.model.Feed;
import java.util.List;
import br.udesc.argc.persistence.dao.core.FeedDAO;
import br.udesc.argc.persistence.model.Feed;
import br.udesc.argc.persistence.utils.SQLiteJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author gabrielnaoto
 */
public class JDBCFeedDAO implements FeedDAO {

    @Override
    public void insert(Feed object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "INSERT INTO FEED (name, url) "
                    + "VALUES ('" + object.getName() + "', '" + object.getUrl() + "');";
            stmt.executeUpdate(sql);
            stmt.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Record created successfully");
    }

    @Override
    public boolean delete(int id) {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            return stmt.executeUpdate("DELETE FROM FEED WHERE id = " + id + ";") > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Feed find(int id) {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FEED WHERE id = " + id + ";");
            Feed s = null;
            while (rs.next()) {
                int i = rs.getInt("id");
                String url = rs.getString("url");
                String name = rs.getString("name");
                s = new Feed();
                s.setId(i);
                s.setUrl(url);
                s.setName(name);
            }
            rs.close();
            stmt.close();
            return s;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Feed> list() {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM feed;");
            List<Feed> feeds = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String url = rs.getString("url");
                String name = rs.getString("name");
                Feed s = new Feed();
                s.setId(id);
                s.setUrl(url);
                s.setName(name);
                feeds.add(s);
            }
            rs.close();
            stmt.close();
            return feeds;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Feed object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "UPDATE FEED SET "
                    + "name = '" + object.getName()+ "',"
                    + "url = '" + object.getUrl()+ "'"
                    + "WHERE id = " + object.getId() + ";";
            return stmt.executeUpdate(sql) > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

}
