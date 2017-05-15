/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.view.models;

import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.model.Schedule;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author gabrielnaoto
 */
public class ScheduleModel extends AbstractTableModel {

    private List<Schedule> schedules;
    private ScheduleDAO dao;

    public ScheduleModel() {
        dao = FactoryDAO.getPersistence().getScheduleDAO();
        schedules = dao.list();
    }

    @Override
    public int getRowCount() {
        return schedules.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Schedule s = schedules.get(rowIndex);
        switch (columnIndex) {
            case 0: {
                return s.getId();
            }
            case 1: {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                return sdf.format(s.getDate());
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
                return "Schedule";
            }

        }
        return null;
    }

    public Schedule getSchedule(int index) {
        return schedules.get(index);
    }

    public void update() {
        schedules = dao.list();
        fireTableDataChanged();
    }

}
