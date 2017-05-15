/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowDetailsScheduler;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.model.Schedule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author gabrielnaoto
 */
public class ScheduleDetailControl {

    private WindowDetailsScheduler wds;
    private ScheduleDAO dao;
    private Schedule selected;

    public ScheduleDetailControl() {
        wds = new WindowDetailsScheduler();
        dao = FactoryDAO.getPersistence().getScheduleDAO();
        init();
    }

    private void init() {
        System.out.println("init");
        wds.timeSpinner.setModel(new SpinnerDateModel());
        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(wds.timeSpinner, "HH:mm");
        wds.timeSpinner.setEditor(timeEditor);

        wds.buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        wds.buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selected == null) {
                    dao.insert(getta());
                    JOptionPane.showMessageDialog(wds, "Successfully inserted!");
                    wds.setVisible(false);
                } else {
                    if (dao.update(getta())) {
                        JOptionPane.showMessageDialog(wds, "Successfully updated!");
                        wds.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(wds, "Error updating!");
                    }
                }
            }
        });
    }

    public void run(Schedule s) {
        if (s == null) {
            clear();
            wds.buttonClear.setEnabled(true);
        } else {
            wds.buttonClear.setEnabled(false);
            selected = s;
            setta(selected);
        }
        wds.fieldId.setEditable(false);
        wds.setVisible(true);
    }

    public void clear() {
        setta(null);
        wds.fieldId.setText("0");
        selected = null;

    }

    public Schedule getta() throws NumberFormatException {
        int id = Integer.parseInt(wds.fieldId.getText());
        Date d = (Date) wds.timeSpinner.getValue();
        Schedule object = new Schedule();
        object.setId(id);
        object.setDate(d);
        return object;
    }

    public void setta(Schedule object) {
        if (object == null) {
            wds.fieldId.setText("");
        } else {
            wds.fieldId.setText(Integer.toString(object.getId()));
            wds.timeSpinner.setValue(object.getDate());
        }
    }

}
