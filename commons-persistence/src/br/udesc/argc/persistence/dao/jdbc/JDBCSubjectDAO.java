/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.jdbc;

import br.udesc.argc.persistence.dao.core.SubjectDAO;
import br.udesc.argc.persistence.model.Subject;
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
public class JDBCSubjectDAO implements SubjectDAO {

    @Override
    public void insert(Subject object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "INSERT INTO SUBJECT (subject) "
                    + "VALUES ('" + object.getSubject() + "');";
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
            return stmt.executeUpdate("DELETE FROM subject WHERE id = " + id + ";") > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Subject find(int id) {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject WHERE id = " + id + ";");
            Subject s = null;
            while (rs.next()) {
                int i = rs.getInt("id");
                String subject = rs.getString("subject");
                s = new Subject();
                s.setId(i);
                s.setSubject(subject);
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
    public List<Subject> list() {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM subject;");
            List<Subject> subjects = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                String subject = rs.getString("subject");
                Subject s = new Subject();
                s.setId(id);
                s.setSubject(subject);
                subjects.add(s);
            }
            rs.close();
            stmt.close();
            return subjects;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Subject object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "UPDATE SUBJECT SET "
                    + "subject = '" + object.getSubject() + "'"
                    + "WHERE id = " + object.getId() + ";";
            return stmt.executeUpdate(sql) > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

}
