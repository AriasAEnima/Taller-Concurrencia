/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Se hicieron pruebas de 200 y 500 Browser y con un servidor atentiendo
 * con 1 o 7 hilos.
 * @author J. Eduardo Arias
 */
public class ARunnableTest {
    private static String test_Result="";
    
    
    @Test
    public void testMustFailNotFound(){       
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
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
            test_Result+="===== ServerTest: Tiempo total de procesamiento testMustFailNotFound: "+(fin-init)+ " Milisegundos"+k.message+ "=====\n";
            if(k.fail){
                assertTrue(true);                
            }else{
                fail(k.message);   
            }
            
        }catch (InterruptedException ex) {
            fail(ex.toString());
        }
    } 
    
    @Test
    public void testMustFailNotSuported(){       
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
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
            test_Result+="===== ServerTest: Tiempo total de procesamiento testMustFailNotSuported: "+(fin-init)+ " Milisegundos"+k.message+ "=====\n";
            if(k.fail){
                assertTrue(true);                
            }else{
                fail(k.message);   
            }
            
        }catch (InterruptedException ex) {
            fail(ex.toString());
        }
    } 
    
    
         
    @Test
    public void test200_1Thread(){       
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
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
            test_Result+="===== ServerTest: Tiempo total de procesamiento test200_1Thread: "+(fin-init)+ " Milisegundos =====\n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }           
        } catch (InterruptedException ex) {
            fail(ex.toString());
        }
    }    
    
    @Test
    public void test200_7Thread(){       
        try {
            ServerHttp server = ServerHttp.getTestServer(7);
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
            test_Result+="===== ServerTest: Tiempo total de procesamiento test200_7Thread: "+(fin-init)+ " Milisegundos ===== \n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
        }catch (InterruptedException ex) {
            fail(ex.toString());
        }
    } 
    
    @Test
    public void test500_7Thread(){       
        try {
            ServerHttp server = ServerHttp.getTestServer(7);
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
            test_Result+="===== ServerTest: Tiempo total de procesamiento test500_7Thread: "+(fin-init)+ " Milisegundos ===== \n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
        } catch (InterruptedException ex) {
            fail(ex.toString());
        }
    } 
    
         
    @Test
    public void test500_1Thread(){       
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
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
            test_Result+="===== ServerTest: Tiempo total de procesamiento test500_1Thread: "+(fin-init)+ " Milisegundos ===== \n";
            for (BrowserForTest b: browsersForTest){
                if(b.fail){
                    fail(b.message);
                }           
                break;
            }
        } catch (InterruptedException ex) {
            fail(ex.toString());
        }
        
       
    }
    /**
     * Imprime los tiempos de ejecucion y las respuestas generales de 
     * las pruebas.
     */
    @AfterClass
    public static void results(){
        System.out.println(test_Result);
    }
}
