/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.core;

import br.udesc.argc.persistence.dao.jdbc.JDBCFactoryDAO;

/**
 *
 * @author gabrielnaoto
 */
public abstract class FactoryDAO {
    
    public abstract FeedDAO getFeedDAO();
    
    public abstract NewsDAO getNewsDAO();
    
    public abstract ScheduleDAO getScheduleDAO();
    
    public abstract SubjectDAO getSubjectDAO();
    
    public static FactoryDAO getPersistence(){
        return new JDBCFactoryDAO();
    }
    
}
