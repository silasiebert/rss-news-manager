/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

/**
 *
 * @author gabrielnaoto
 */
public class Utils {

    public static void open(URI uri) {
        if (Desktop.isDesktopSupported()) {
            try {
                Desktop.getDesktop().browse(uri);
            } catch (IOException e) {
            }
        } else {
        }
    }

}
