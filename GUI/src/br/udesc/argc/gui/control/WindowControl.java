/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.Window;
import br.udesc.argc.rss.Executer;
import com.rometools.rome.io.FeedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

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
        window.labelStatus.setText("aguardando...");
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

        SwingWorker w = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                for (int i = 1; i <= 100; i++) {
                    try {
                        window.progressBar.setValue(i);
                        window.progressBar.setString(i + "%");
                        Thread.sleep(20);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                return 0;
            }
        };

        window.executarVerificacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.labelStatus.setText("verificando...");
                w.execute();
                Executer exe = Executer.getExecuter();
                try {
                    exe.execute();
                } catch (IOException ex) {
                    Logger.getLogger(WindowControl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(WindowControl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FeedException ex) {
                    Logger.getLogger(WindowControl.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(WindowControl.class.getName()).log(Level.SEVERE, null, ex);
                }
                JOptionPane.showMessageDialog(window, "Verificação concluída com sucesso!");
                window.labelStatus.setText("aguardando...");
                window.progressBar.setValue(0);
            }
        });
    }

    public void run() {
        window.setVisible(true);
    }

}
