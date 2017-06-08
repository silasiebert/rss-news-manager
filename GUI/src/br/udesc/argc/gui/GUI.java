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
import javax.swing.JOptionPane;

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
            JOptionPane.showMessageDialog(null, "You must insert e-mail settings to the system.");
            WindowEmailControl wec = new WindowEmailControl(true);
        }
    }

}
