/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui;

import br.udesc.argc.gui.control.WindowControl;
import br.udesc.argc.gui.control.WindowEmailControl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;

/**
 *
 * @author gabrielnaoto
 */
public class GUI {

    public static void main(String[] args) {
        WindowControl wc = new WindowControl();
        wc.run();
        try {
            FileReader f = new FileReader(new File("config_sender.json"));
        } catch (FileNotFoundException f) {
            FileWriter file = null;
            try {
                JOptionPane.showMessageDialog(null, "You must insert e-mail reciever settings to the system.");
                String novo;
                do {
                    novo = JOptionPane.showInputDialog("Please inform a new e-mail address to send e-mails(at least 5 characters)");
                } while (novo.trim().length() < 5);
                JSONObject o = new JSONObject();
                o.put("email", novo);
                file = new FileWriter("config_reciever.json");
                file.write(o.toJSONString());
                file.flush();
                JOptionPane.showMessageDialog(null, "Successfully saved.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            FileReader f = new FileReader(new File("config_sender.json"));
        } catch (FileNotFoundException f) {
            JOptionPane.showMessageDialog(null, "You must insert e-mail sender settings to the system.");
            WindowEmailControl wec = new WindowEmailControl(true);
            wec.run();
        }
        
    }

}
