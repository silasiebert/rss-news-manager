/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowDetailsSubject;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.SubjectDAO;
import br.udesc.argc.persistence.model.Subject;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielnaoto
 */
public class SubjectDetailControl {

    private WindowDetailsSubject wds;
    private SubjectDAO dao;
    private Subject selected;

    public SubjectDetailControl() {
        wds = new WindowDetailsSubject();
        dao = FactoryDAO.getPersistence().getSubjectDAO();
        init();
    }

    private void init() {
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

    public void run(Subject s) {
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

    public Subject getta() throws NumberFormatException {
        int id = Integer.parseInt(wds.fieldId.getText());
        String subject = wds.fieldSubject.getText();
        Subject object = new Subject();
        object.setId(id);
        object.setSubject(subject);
        return object;
    }

    public void setta(Subject object) {
        if (object == null) {
            wds.fieldId.setText("");
            wds.fieldSubject.setText("");
        } else {
            wds.fieldId.setText(Integer.toString(object.getId()));
            wds.fieldSubject.setText(object.getSubject());
        }
    }
}
