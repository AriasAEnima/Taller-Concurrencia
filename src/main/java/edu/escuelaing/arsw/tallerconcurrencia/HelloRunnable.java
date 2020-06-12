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
public class HelloRunnable implements Runnable{
    static int counter=0;
    @Override
    public void run() {
        System.out.println("Hello Runnable!");
        System.out.println("My thread number: "+(counter++));
    }
    
    public static void main(String[] args){
        int i = 0;
        while( i <10){
            (new Thread(new HelloRunnable())).start();
            System.out.println("Main Thread just created: "+i);
            i++;
        }      
    }   
}
