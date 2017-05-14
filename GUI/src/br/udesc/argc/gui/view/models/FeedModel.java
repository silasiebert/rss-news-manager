/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.view.models;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.FeedDAO;
import br.udesc.argc.persistence.model.Feed;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gabrielnaoto
 */
public class FeedModel extends AbstractTableModel{

    private List<Feed> feeds;
    private FeedDAO dao;

    public FeedModel() {
        dao = FactoryDAO.getPersistence().getFeedDAO();
        feeds = dao.list();
    }

    @Override
    public int getRowCount() {
        return feeds.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Feed s = feeds.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return s.getId();
            }
            case 1: {
                return s.getName();
            }
            case 2: {
                return s.getUrl();
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: {
                return "ID";
            }
            case 1: {
                return "Name";
            }
            case 2: {
                return "URL";
            }

        }
        return null;
    }

    public Feed getFeed(int index) {
        return feeds.get(index);
    }

    public void update() {
        feeds = dao.list();
        fireTableDataChanged();
    }

}
