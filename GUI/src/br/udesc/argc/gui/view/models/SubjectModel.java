/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.view.models;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.SubjectDAO;
import br.udesc.argc.persistence.model.Subject;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gabrielnaoto
 */
public class SubjectModel extends AbstractTableModel {

    private List<Subject> subjects;
    private SubjectDAO dao;

    public SubjectModel() {
        dao = FactoryDAO.getPersistence().getSubjectDAO();
        subjects = dao.list();
    }

    @Override
    public int getRowCount() {
        return subjects.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Subject s = subjects.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return s.getId();
            }
            case 1: {
                return s.getSubject();
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
                return "Subject";
            }

        }
        return null;
    }
    
    public Subject getSubject(int index){
        return subjects.get(index);
    }
    
    public void update(){
        subjects = dao.list();
        fireTableDataChanged();
    }

}
