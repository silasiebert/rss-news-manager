/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.Utils;
import br.udesc.argc.gui.view.WindowNews;
import br.udesc.argc.gui.view.models.NewsModel;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.dao.core.NewsDAO;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author gabrielnaoto
 */
public class NewsControl {

    private WindowNews wn;
    private NewsModel nm;
    private NewsDAO dao;

    public NewsControl() {
        wn = new WindowNews();
        nm = new NewsModel();
        dao = FactoryDAO.getPersistence().getNewsDAO();
        init();
    }

    private void init() {
        wn.jTable1.setModel(nm);

        wn.jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {

                try {
                    int row = wn.jTable1.getSelectedRow();
                    int col = wn.jTable1.getSelectedColumn();

                    URI uri = new URI((String) nm.getValueAt(row, col));

                    Utils.open(uri);
                } catch (URISyntaxException ex) {

                }
            }
        });

        DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
            Font font = new Font(Font.DIALOG, Font.BOLD, 12);

            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                        row, column);
                setFont(font);
                return this;
            }

        };
        
        wn.jTable1.getColumnModel().getColumn(1).setCellRenderer(r);

        wn.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                nm.update();
                System.out.println("updated");
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });

        wn.buttonUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nm.update();
                System.out.println("manually updated");
            }
        });

    }

    public void run() {
        wn.setVisible(true);
    }

}
