/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowScheduler;
import br.udesc.argc.gui.view.tables.ScheduleTable;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.ScheduleDAO;
import br.udesc.argc.persistence.model.Schedule;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielnaoto
 */
public class SchedulerControl {

    private WindowScheduler ws;
    private ScheduleDetailControl sdc;
    private ScheduleTable sm;
    private ScheduleDAO dao;

    public SchedulerControl() {
        ws = new WindowScheduler();
        sdc = new ScheduleDetailControl();
        sm = new ScheduleTable();
        dao = FactoryDAO.getPersistence().getScheduleDAO();
        init();
    }

    private void init() {
        ws.jTable1.setModel(sm);

        ws.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                sm.update();
                System.out.println("updated");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        ws.buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sdc.run(null);
            }
        });

        ws.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sdc.run(sm.getSchedule(ws.jTable1.getSelectedRow()));
            }
        });
        ws.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ws.jTable1.getSelectedRow() < 0) {
                    JOptionPane.showMessageDialog(ws, "You need to select an entry first.");
                } else {
                    Schedule s = sm.getSchedule(ws.jTable1.getSelectedRow());
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                    int result = JOptionPane.showConfirmDialog(ws, "Do you confirm to remove the [" + sdf.format(s.getDate()) + "] ?");
                    if (result == 0) {
                        dao.delete(s.getId());
                    }
                }
            }
        });
    }

    public void run() {
        System.out.println("run");
        ws.setVisible(true);
    }

}
