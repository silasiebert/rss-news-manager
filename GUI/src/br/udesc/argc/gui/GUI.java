/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.gui;

import br.udesc.argc.gui.control.WindowControl;
import br.udesc.argc.persistence.dao.core.FactoryDAO;
import br.udesc.argc.persistence.model.News;

/**
 *
 * @author gabrielnaoto
 */
public class GUI {
    
    public static void main(String[] args) {
        WindowControl wc = new WindowControl();
        wc.run();
    }
    
}
