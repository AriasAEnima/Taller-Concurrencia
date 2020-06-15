/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Se hicieron pruebas de 200 y 500 Browser y con un servidor atentiendo
 * con 1 o 7 hilos.
 * @author J. Eduardo Arias
 */
public class CallableTest {
     private static String test_Result="";
    
    @Test
    public void testMustFailNotFound(){       
        List<Future<String>> resultados;
        List<Callable<String>> hilos=new ArrayList<>();
        String firstmessage;
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
            Thread s = new Thread(server);        
            s.start();            
            ExecutorService servicio= Executors.newFixedThreadPool(1);          
            for (int i=1 ; i<=10; i++){
                hilos.add(new CallableBrowser("hugeimgs.jpg"));     
            }                    
            long init = System.currentTimeMillis();    
            resultados=servicio.invokeAll(hilos);                  
            long fin = System.currentTimeMillis();	// Instante final del procesamiento 
            server.stop();
            s.join();             
            firstmessage=resultados.get(0).get();
            for (Future<String>fs: resultados){
                if (fs.get().equals("Correcto")){
                    fail("Debio fallar");
                    firstmessage="No funciono";
                    break;
                }
            }                  
            test_Result+="===== Callable Test: Tiempo total de procesamiento testMustFailNotFound: "+(fin-init)+ " Milisegundos , Mensaje: "+firstmessage+" =====\n";                
        } catch(InterruptedException | ExecutionException ex) {
           fail(ex.toString());
        }
    }
    
    @Test
    public void testMustFailNotSuported(){       
        List<Future<String>> resultados;
        List<Callable<String>> hilos=new ArrayList<>();
        String firstmessage;
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
            Thread s = new Thread(server);        
            s.start();            
            ExecutorService servicio= Executors.newFixedThreadPool(10);        
            for (int i=1 ; i<=10; i++){
                hilos.add(new CallableBrowser("hugeimg.ico"));     
            }                        
            long init = System.currentTimeMillis();    
            resultados=servicio.invokeAll(hilos);                  
            long fin = System.currentTimeMillis();	// Instante final del procesamiento 
            server.stop();
            s.join();                         
            firstmessage=resultados.get(0).get();
            for (Future<String>fs: resultados){
                if (fs.get().equals("Correcto")){
                    fail("Debio fallar");
                    firstmessage="No funciono";
                    break;
                }
            }           
            test_Result+="===== Callable Test: Tiempo total de procesamiento testMustFailNotSuported: "+(fin-init)+ " Milisegundos  , Mensaje: "+ firstmessage +" =====\n";   
        } catch(InterruptedException | ExecutionException ex) {
           fail(ex.toString());
        }
    } 
    
    
    @Test
    public void test200_1Thread(){       
        List<Future<String>> resultados;
        List<Callable<String>> hilos=new ArrayList<>();
        String firstmessage;
        try {
            ServerHttp server = ServerHttp.getTestServer(1);
            Thread s = new Thread(server);        
            s.start();            
            ExecutorService servicio= Executors.newCachedThreadPool();           
            for (int i=1 ; i<=200; i++){
                hilos.add(new CallableBrowser("hugeimg.jpg"));     
            }       
            long init = System.currentTimeMillis();    
            resultados=servicio.invokeAll(hilos);                  
            long fin = System.currentTimeMillis();	// Instante final del procesamiento   
            server.stop();
            s.join();                    
            firstmessage=resultados.get(0).get();
            for (Future<String>fs: resultados){
                if (!fs.get().equals("Correcto")){
                    fail("Algo fallo: "+fs.get());
                    firstmessage="No funciono";
                    break;
                }
            }           
            test_Result+="===== Callable Test:  Tiempo total de procesamiento test200_1Thread: "+(fin-init)+ " Milisegundos  , Mensaje: "+ firstmessage +" =====\n";   
        }catch(InterruptedException | ExecutionException ex) {
           fail(ex.toString());
        }
    }    
    
    @Test
    public void test200_7Thread(){       
        List<Future<String>> resultados;
        List<Callable<String>> hilos=new ArrayList<>();
        String firstmessage;
        try {
            ServerHttp server = ServerHttp.getTestServer(7);
            Thread s = new Thread(server);        
            s.start();            
            ExecutorService servicio= Executors.newCachedThreadPool(); 
            for (int i=1 ; i<=200; i++){
                hilos.add(new CallableBrowser("hugeimg.jpg"));     
            }                        
            long init = System.currentTimeMillis();    
            resultados=servicio.invokeAll(hilos);
            long fin = System.currentTimeMillis();	// Instante final del procesamiento   
            server.stop();
            s.join();              
            firstmessage=resultados.get(0).get();
            for (Future<String>fs: resultados){
                if (!fs.get().equals("Correcto")){
                    fail("Algo fallo: "+fs.get());
                    firstmessage="No funciono";
                    break;
                }
            }           
            test_Result+="===== Callable Test:  Tiempo total de procesamiento test200_7Thread: "+(fin-init)+ " Milisegundos  , Mensaje: "+ firstmessage +" =====\n";   
        } catch(InterruptedException | ExecutionException ex) {
           fail(ex.toString());
        } 
    } 
    
    /**
     * Aqui podemos observar la respuesta del servidor para 3 tipos
     * de archivos jpg , js y html.
     */
    @Test
    public void printerTest(){       
        List<Future<String>> resultados;
        List<Callable<String>> hilos=new ArrayList<>();
        String firstmessage;
        try {
            ServerHttp server = ServerHttp.getTestServer(7);
            Thread s = new Thread(server);        
            s.start();            
            ExecutorService servicio= Executors.newCachedThreadPool();            
            long init = System.currentTimeMillis();               
            hilos.add(new CallableBrowser("hugeimg.jpg",true));     
            hilos.add(new CallableBrowser("index.html",true));     
            hilos.add(new CallableBrowser("prueba.js",true));    
            resultados=servicio.invokeAll(hilos);
            server.stop();
            s.join();  
            long fin = System.currentTimeMillis();	// Instante final del procesamiento   
            firstmessage=resultados.get(0).get();
            for (Future<String>fs: resultados){
                if (!fs.get().equals("Correcto")){
                    fail("Algo fallo: "+fs.get());
                    firstmessage="No funciono";
                    break;
                }
            }                  
            test_Result+="===== Callable Test: Tiempo total de procesamiento printerTest: "+(fin-init)+ " Milisegundos , Mensaje: "+firstmessage+" =====\n";                
        } catch(InterruptedException | ExecutionException ex) {
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
