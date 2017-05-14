/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.persistence.dao.jdbc;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.FeedDAO;
import br.udesc.argc.persistence.dao.core.NewsDAO;
import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.dao.core.SubjectDAO;

/**
 *
 * @author gabrielnaoto
 */
public class JDBCFactoryDAO extends FactoryDAO {

    @Override
    public FeedDAO getFeedDAO() {
        return new JDBCFeedDAO();
    }

    @Override
    public NewsDAO getNewsDAO() {
        return new JDBCNewsDAO();
    }

    @Override
    public ScheduleDAO getScheduleDAO() {
        return new JDBCScheduleDAO();
    }

    @Override
    public SubjectDAO getSubjectDAO() {
        return new JDBCSubjectDAO();
    }

}
