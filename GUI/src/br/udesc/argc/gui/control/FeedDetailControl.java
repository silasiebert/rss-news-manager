/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowDetailsFeed;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.FeedDAO;
import br.udesc.argc.persistence.model.Feed;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielnaoto
 */
public class FeedDetailControl {

    private WindowDetailsFeed wdf;
    private FeedDAO dao;
    private Feed selected;

    public FeedDetailControl() {
        wdf = new WindowDetailsFeed();
        dao = FactoryDAO.getPersistence().getFeedDAO();
        init();
    }

    private void init() {
        wdf.buttonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        wdf.buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filled()) {
                    if (selected == null) {
                        dao.insert(getta());
                        JOptionPane.showMessageDialog(wdf, "Successfully inserted!");
                        wdf.setVisible(false);
                    } else {
                        if (dao.update(getta())) {
                            JOptionPane.showMessageDialog(wdf, "Successfully updated!");
                            wdf.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(wdf, "Error updating!");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(wdf, "Please fill all the fields.");
                }
            }
        });
    }

    public void run(Feed s) {
        if (s == null) {
            clear();
            wdf.buttonClear.setEnabled(true);
        } else {
            wdf.buttonClear.setEnabled(false);
            selected = s;
            setta(selected);
        }
        wdf.fieldId.setEditable(false);
        wdf.setVisible(true);
    }

    public void clear() {
        setta(null);
        wdf.fieldId.setText("0");
        selected = null;

    }

    public Feed getta() throws NumberFormatException {
        int id = Integer.parseInt(wdf.fieldId.getText());
        String name = wdf.fieldName.getText();
        String url = wdf.fieldUrl.getText();
        Feed object = new Feed();
        object.setId(id);
        object.setName(name);
        object.setUrl(url);
        return object;
    }

    public void setta(Feed object) {
        if (object == null) {
            wdf.fieldId.setText("");
            wdf.fieldName.setText("");
            wdf.fieldUrl.setText("");
        } else {
            wdf.fieldId.setText(Integer.toString(object.getId()));
            wdf.fieldName.setText(object.getName());
            wdf.fieldUrl.setText(object.getUrl());
        }

    }

    public boolean filled() {
        return wdf.fieldName.getText().trim().length() > 0 && wdf.fieldUrl.getText().trim().length() > 0;
    }

}
