/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowSubject;
import br.udesc.argc.gui.view.models.SubjectModel;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.SubjectDAO;
import br.udesc.argc.persistence.model.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielnaoto
 */
public class SubjectControl {

    private WindowSubject ws;
    private SubjectDetailControl sdc;
    private SubjectModel sm;
    private SubjectDAO dao;

    public SubjectControl() {
        ws = new WindowSubject();
        sdc = new SubjectDetailControl();
        sm = new SubjectModel();
        dao = FactoryDAO.getPersistence().getSubjectDAO();
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
                sdc.run(sm.getSubject(ws.jTable1.getSelectedRow()));
            }
        });
        ws.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (ws.jTable1.getSelectedRow() < 0){
                    JOptionPane.showMessageDialog(ws, "You need to select an entry first.");
                } else {
                    Subject s = sm.getSubject(ws.jTable1.getSelectedRow());
                    int result = JOptionPane.showConfirmDialog(ws, "Do you confirm to remove the [" + s.getSubject() + "] ?");
                    if (result == 0){
                        dao.delete(s.getId());
                    }
                }
            }
        });
    }

    public void run() {
        ws.setVisible(true);
    }

}
