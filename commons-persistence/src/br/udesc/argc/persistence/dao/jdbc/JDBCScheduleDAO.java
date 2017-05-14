/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.jdbc;

import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.model.Schedule;
import br.udesc.argc.persistence.utils.SQLiteJDBC;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author gabrielnaoto
 */
public class JDBCScheduleDAO implements ScheduleDAO {

    @Override
    public void insert(Schedule object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "INSERT INTO SCHEDULE (date) "
                    + "VALUES (" + object.getDate() + ");";
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
            return stmt.executeUpdate("DELETE FROM SCHEDULE WHERE id = " + id + ";") > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    @Override
    public Schedule find(int id) {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCHEDULE WHERE id = " + id + ";");
            Schedule s = null;
            while (rs.next()) {
                int i = rs.getInt("id");
                Date date = rs.getDate("date");
                s = new Schedule();
                s.setId(i);
                s.setDate(date);
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
    public List<Schedule> list() {
        Connection c = SQLiteJDBC.getConnection();
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM schedule;");
            List<Schedule> schedules = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                Schedule s = new Schedule();
                s.setId(id);
                s.setDate(date);
                schedules.add(s);
            }
            rs.close();
            stmt.close();
            return schedules;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean update(Schedule object) {
        Connection c = null;
        Statement stmt = null;
        try {
            c = SQLiteJDBC.getConnection();
            stmt = c.createStatement();
            String sql = "UPDATE SCHEDULE SET "
                    + "subject = '" + object.getDate() + "'"
                    + "WHERE id = " + object.getId() + ";";
            return stmt.executeUpdate(sql) > 0;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

}
