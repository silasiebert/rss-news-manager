/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.argc.sheduler;

/**
 *
 * @author gabrielnaoto
 */
public class Main {

    public static void main(String[] args) {
        AbstractScheduler as = AbstractScheduler.getScheduler();
        as.start();
    }

}
