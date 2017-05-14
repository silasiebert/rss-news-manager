/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowFeed;
import br.udesc.argc.gui.view.models.FeedModel;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.FeedDAO;
import br.udesc.argc.persistence.model.Feed;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielnaoto
 */
public class FeedControl {
    private WindowFeed wf;
    private FeedDetailControl fdc;
    private FeedModel fd;
    private FeedDAO dao;

    public FeedControl() {
        wf = new WindowFeed();
        fdc = new FeedDetailControl();
        fd = new FeedModel();
        dao = FactoryDAO.getPersistence().getFeedDAO();
        init();
    }

    private void init() {
        wf.jTable1.setModel(fd);

        wf.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                fd.update();
                System.out.println("updated");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        wf.buttonNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fdc.run(null);
            }
        });

        wf.buttonEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fdc.run(fd.getFeed(wf.jTable1.getSelectedRow()));
            }
        });
        wf.buttonRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (wf.jTable1.getSelectedRow() < 0){
                    JOptionPane.showMessageDialog(wf, "You need to select an entry first.");
                } else {
                    Feed s = fd.getFeed(wf.jTable1.getSelectedRow());
                    int result = JOptionPane.showConfirmDialog(wf, "Do you confirm to remove the [" + s.getName()+ "] ?");
                    if (result == 0){
                        dao.delete(s.getId());
                    }
                }
            }
        });
    }

    public void run() {
        wf.setVisible(true);
    }
    
    
}
