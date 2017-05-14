/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.utils;

import java.sql.*;

public class SQLiteJDBC {

    private static Connection c;

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            if (c == null) {
                c = DriverManager.getConnection("jdbc:sqlite:rssfeed.db");
                System.out.println("Opened database successfully");
                create();
            }
            return c;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public static void create() {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = ""
                    + "CREATE TABLE IF NOT EXISTS news ( \n"
                    + "	id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "	url varchar(50),\n"
                    + "	title varchar(50)\n"
                    + ");\n"
                    + "CREATE TABLE IF NOT EXISTS schedule ( \n"
                    + "	date text,\n"
                    + "	id integer PRIMARY KEY AUTOINCREMENT\n"
                    + ");\n"
                    + "CREATE TABLE IF NOT EXISTS feed ( \n"
                    + "	name varchar(50),\n"
                    + "	url varchar(50),\n"
                    + "	id integer PRIMARY KEY AUTOINCREMENT\n"
                    + ");\n"
                    + "CREATE TABLE IF NOT EXISTS subject ( \n"
                    + "	subject varchar(50),\n"
                    + "	id integer PRIMARY KEY AUTOINCREMENT\n"
                    + ");\n"

            ;
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        ;
    }

}
