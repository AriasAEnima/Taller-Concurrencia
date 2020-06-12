/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia;

/**
 *
 * @author J. Eduardo Arias
 */
public class HelloThread extends Thread{
    
    @Override
    public void run(){
        System.out.println("Hello Thread");
    }
    
    public static void main(String[] args){
        (new HelloThread()).start();
    }
    
}
