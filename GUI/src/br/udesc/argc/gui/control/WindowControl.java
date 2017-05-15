/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gabrielnaoto
 */
public class WindowControl {

    private Window window;

    public WindowControl() {
        window = new Window();
        init(); 
    }

    private void init() {
        window.configurarFeed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FeedControl fc = new FeedControl();
                fc.run();
            }
        });
        window.configurarHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SchedulerControl sc = new SchedulerControl();
                sc.run();
                
            }
        });
        window.configurarTermos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SubjectControl sc = new SubjectControl();
                sc.run();
            }
        });
        window.consultarNoticias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewsControl nc = new NewsControl();
                nc.run();
                
            }
        });
        window.executarVerificacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(window, "Verificação concluída com sucesso!");
            }
        });
    }
    
    public void run(){
        window.setVisible(true);
    }

}
