/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 *
 * @author J. Eduardo Arias
 */
public class ServerTest {
    private static String test_Result="";
    
    
    @Test
    public void testMustFailNotFound(){       
        try {
            ServerHttp server = ServerHttp.getInstanceThreads(1);
            Thread s = new Thread(server);        
            s.start();          
            BrowserForTest k=new BrowserForTest("hugeimgs.jpg");
            Thread b = new Thread(k);               
            long init = System.currentTimeMillis();                
            b.start();          
            b.join();            
            server.stop();
            s.join();          
            long fin = System.currentTimeMillis();	// Instante final del procesamiento            
            test_Result+="===== Tiempo total de procesamiento testMustFailNotFound: "+(fin-init)+ " Milisegundos =====\n";
            if(k.fail){
                assertTrue(true);                
            }else{
                fail(k.message);   
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Test
    public void testMustFailNotSuported(){       
        try {
            ServerHttp server = ServerHttp.getInstanceThreads(1);
            Thread s = new Thread(server);        
            s.start();          
            BrowserForTest k=new BrowserForTest("hugeimg.ico");
            Thread b = new Thread(k);               
            long init = System.currentTimeMillis();                
            b.start();          
            b.join();            
            server.stop();
            s.join();          
            long fin = System.currentTimeMillis();	// Instante final del procesamiento            
            test_Result+="===== Tiempo total de procesamiento testMustFailNotSuported: "+(fin-init)+ " Milisegundos =====\n";
            if(k.fail){
                assertTrue(true);                
            }else{
                fail(k.message);   
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    
         
    @Test
    public void test200_1Thread(){       
        try {
            ServerHttp server = ServerHttp.getInstanceThreads(1);
            Thread s = new Thread(server);        
            s.start();
            List<Thread> hilosBrowser=new ArrayList<>();
            List<BrowserForTest> browsersForTest=new ArrayList<>();
            for (int i=1; i<=200 ; i++){
                BrowserForTest b =new BrowserForTest("hugeimg.jpg");                
                browsersForTest.add(b);
                Thread t = new Thread(b);
                hilosBrowser.add(t);
            }
            long init = System.currentTimeMillis();            
            for(Thread h: hilosBrowser){
                h.start();
            }
            for(Thread h: hilosBrowser){
                h.join();
            }
            server.stop();
            s.join();            
            long fin = System.currentTimeMillis();	// Instante final del procesamiento            
            test_Result+="===== Tiempo total de procesamiento test200_1Thread: "+(fin-init)+ " Milisegundos =====\n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
           
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    @Test
    public void test200_7Thread(){       
        try {
            ServerHttp server = ServerHttp.getInstanceThreads(7);
            Thread s = new Thread(server);        
            s.start();
            List<Thread> hilosBrowser=new ArrayList<>();
            List<BrowserForTest> browsersForTest=new ArrayList<>();
            for (int i=1; i<=200 ; i++){
                BrowserForTest b =new BrowserForTest("hugeimg.jpg");                
                browsersForTest.add(b);
                Thread t = new Thread(b);
                hilosBrowser.add(t);
            }
            long init = System.currentTimeMillis();            
            for(Thread h: hilosBrowser){
                h.start();
            }
            for(Thread h: hilosBrowser){
                h.join();
            }
            server.stop();
            s.join();               
            long fin = System.currentTimeMillis();	// Instante final del procesamiento            
            test_Result+="===== Tiempo total de procesamiento test200_7Thread: "+(fin-init)+ " Milisegundos ===== \n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    @Test
    public void test500_7Thread(){       
        try {
            ServerHttp server = ServerHttp.getInstanceThreads(7);
            Thread s = new Thread(server);        
            s.start();
            List<Thread> hilosBrowser=new ArrayList<>();
            List<BrowserForTest> browsersForTest=new ArrayList<>();
            for (int i=1; i<=500 ; i++){
                BrowserForTest b =new BrowserForTest("hugeimg.jpg");                
                browsersForTest.add(b);
                Thread t = new Thread(b);
                hilosBrowser.add(t);
            }
            long init = System.currentTimeMillis();            
            for(Thread h: hilosBrowser){
                h.start();
            }
            for(Thread h: hilosBrowser){
                h.join();
            }
            server.stop();
            s.join();           
            long fin = System.currentTimeMillis();	// Instante final del procesamiento            
            test_Result+="===== Tiempo total de procesamiento test500_7Thread: "+(fin-init)+ " Milisegundos ===== \n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
         
    @Test
    public void test500_1Thread(){       
        try {
            ServerHttp server = ServerHttp.getInstanceThreads(1);
            Thread s = new Thread(server);        
            s.start();
            List<Thread> hilosBrowser=new ArrayList<>();
            List<BrowserForTest> browsersForTest=new ArrayList<>();
            for (int i=1; i<=500 ; i++){
                BrowserForTest b =new BrowserForTest("hugeimg.jpg");                
                browsersForTest.add(b);
                Thread t = new Thread(b);
                hilosBrowser.add(t);
            }
            long init = System.currentTimeMillis();            
            for(Thread h: hilosBrowser){
                h.start();
            }
            for(Thread h: hilosBrowser){
                h.join();
            }
            server.stop();
            s.join();                    
            long fin = System.currentTimeMillis();	// Instante final del procesamiento            
            test_Result+="===== Tiempo total de procesamiento test500_1Thread: "+(fin-init)+ " Milisegundos ===== \n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
    @AfterClass
    public static void results(){
        System.out.println(test_Result);
    }
}
