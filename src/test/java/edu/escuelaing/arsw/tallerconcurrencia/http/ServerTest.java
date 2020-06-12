/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import org.junit.*;

/**
 *
 * @author J. Eduardo Arias
 */
public class ServerTest {
         
    @Test
    public void Test1(){       
       Thread s=new Thread(new Runnable() {
           @Override
           public void run() {
               ServerHttp.main(null);
           }
       }); 
       s.start();
       
       Thread b= new Thread(new Runnable() {
           @Override
           public void run() {
               new BrowserForTest("sds.ico"); 
           }
       });     
       b.start();
        try {
            b.join();        
            s.interrupt();
            System.out.println("\n ??? Paraste el server loco , sos un crack ???? \n");
        } catch (InterruptedException ex) {
            System.err.println("Se revento algo PAAAAAAAA !!!  ");
        }
   
    }    
    
}
