/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.Window;
import br.udesc.argc.rss.Executer;
import br.udesc.argc.sheduler.AbstractScheduler;
import com.rometools.rome.io.FeedException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author gabrielnaoto
 */
public class WindowControl {

    private Window window;
    private String scheduler;
    private String unscheduler;
    private AbstractScheduler as;

    public WindowControl() {
        window = new Window();
        scheduler = "service stopped";
        unscheduler = "awaiting";
        as = AbstractScheduler.getScheduler();
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
                   ex.printStackTrace();
                } catch (IllegalArgumentException ex) {
                   ex.printStackTrace();
                } catch (FeedException ex) {
                   ex.printStackTrace();
                } catch (MessagingException ex) {
                   ex.printStackTrace();
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
                as.start();
                JOptionPane.showMessageDialog(window, "Service started");
            }
        });

        window.stopService.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduler = "service stopped";
                print();
                as.stop();
                JOptionPane.showMessageDialog(window, "Service stopped");
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
                try {
                    JSONParser parser = new JSONParser();
                    Object obj = parser.parse(new FileReader("config_reciever.json"));
                    JSONObject jsonObject = (JSONObject) obj;
                    String email = (String) jsonObject.get("email");
                    int i = JOptionPane.showConfirmDialog(window, "You are currently sending news to this email address:\n"
                            + email
                            + "\nWould you like to make changes?");
                    if (i == 0) {
                        String novo;
                        do {
                            novo = JOptionPane.showInputDialog("Please inform the new email address (at least 5 characters)");
                        } while (novo.trim().length() < 5);
                        JSONObject o = new JSONObject();
                        o.put("email", novo);
                        FileWriter file = new FileWriter("config_reciever.json");
                        file.write(o.toJSONString());
                        file.flush();
                        JOptionPane.showMessageDialog(null, "Successfully saved.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public void run() {
        window.setVisible(true);
    }

    public void print() {
        window.labelStatus.setText(scheduler + " | " + unscheduler);
    }

}
