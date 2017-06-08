/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui.control;

import br.udesc.argc.gui.view.WindowEmail;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author gabrielnaoto
 */
public class WindowEmailControl {

    private WindowEmail we;
    private String email;
    private String password;
    private JSONParser parser;

    public WindowEmailControl(boolean setup) {
        we = new WindowEmail();
        parser = new JSONParser();
        init();
        if (!setup) {
            load();
        }
    }

    public void init() {
        we.buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save(getta());
            }
        });

    }

    public void load() {
        try {
            Object obj = parser.parse(new FileReader("config_sender.json"));
            JSONObject jsonObject = (JSONObject) obj;
            email = (String) jsonObject.get("email");
            password = (String) jsonObject.get("password");
            setta();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void save(String[] info) {
        if (filled()) {
            JSONObject obj = new JSONObject();
            obj.put("email", email);
            obj.put("password", password);
            try {
                FileWriter file = new FileWriter("config_sender.json");
                file.write(obj.toJSONString());
                file.flush();
                JOptionPane.showMessageDialog(we, "Successfully saved.");
                we.setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(we, "You need to fill all fields.");
        }
    }

    public String[] getta() {
        email = we.fieldEmail.getText();
        password = we.fieldPassword.getText();
        return new String[]{email, password};
    }

    public void setta() {
        we.fieldEmail.setText(email);
        we.fieldPassword.setText(password);
    }

    public boolean filled() {
        return we.fieldEmail.getText().trim().length() > 0 && we.fieldPassword.getText().trim().length() > 0;
    }

    public void run() {
        we.setVisible(true);
    }

    public void setup() {

    }
}
