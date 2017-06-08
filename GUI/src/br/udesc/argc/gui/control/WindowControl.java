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
    private String scheduler;
    private String unscheduler;

    public WindowControl() {
        window = new Window();
        scheduler = "service stopped";
        unscheduler = "awaiting";
        init();
    }

    private void init() {
        print();
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
                unscheduler = "executing unscheduled query";
                print();
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
                JOptionPane.showMessageDialog(window, "All done!");
                unscheduler = "awaiting";
                print();
                window.progressBar.setValue(0);
            }
        });

        window.startService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduler = "service started";
                print();
                JOptionPane.showMessageDialog(window, "started");
            }
        });

        window.stopService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduler = "service stopped";
                print();
                JOptionPane.showMessageDialog(window, "stopped");
            }
        });

        window.sendSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WindowEmailControl wec = new WindowEmailControl(false);
                wec.run();
            }
        });

        window.recieveSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(window, "to be done");
            }
        });
    }

    public void run() {
        window.setVisible(true);
    }
    
    public void print(){
        window.labelStatus.setText(scheduler + " | " + unscheduler);
    }

}
