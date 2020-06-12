/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class HelloRunnableWithJoin implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println("Hello Runnable! "+ LocalDateTime.now());
            Thread.sleep(4000);
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloRunnableWithJoin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args){
        Thread t = new Thread(new HelloRunnableWithJoin());
        t.start();
        try {
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(HelloRunnableWithJoin.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("All threads have endend. "+LocalDateTime.now());
    }
}
