/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author J. Eduardo Arias
 */
public class ServerHttp implements Runnable {
    private static ServerHttp instance;
    private static Object lock=new Object();
    private static int nThread=0;
    private boolean bandera;
    private ExecutorService executor;
    private  ServerSocket serverSocket=null;
    
    private ServerHttp(){
        bandera=true;
        executor = Executors.newFixedThreadPool(7);          
    }
    
     private ServerHttp(int n){
        bandera=true;
        executor = Executors.newFixedThreadPool(n);          
    }
    
    @Override
    public void run() {       
        try {           
            serverSocket = new ServerSocket(35000);            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }     
        //System.out.println("Listo para recibir ...");
        int no=1;
        while(bandera){           
            try {
                //System.out.println("Esperando ...");                
                Socket clientSocket = serverSocket.accept();
                Runnable process = new ClientSocketProcess(clientSocket);
                executor.execute(process);
                synchronized(lock){
                   // System.out.println("Ya mande el proceso ..."+no);
                    no++;                    
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage()+": No se pudo iniciar el socket o este fue cerrado con proposito"); 
            }
        } 
        executor.shutdown();	
        while (!executor.isTerminated()) {
            // Espero a que terminen de ejecutarse todos los procesos       	
        }         
    }
    
    public void stop() {
        bandera = false;
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Devuelve los recursos solicitados por un socket cliente
     * @param args ninguno 
     * @throws IOException si algo ocurre con los sockets
     */
    public static void main(String[] args) {      
        getInstance().run();
    }    
    
    public static ServerHttp getInstance(){      
        if (instance==null){
            instance=new ServerHttp();               
        }
        return instance;
    }  
    
    public static ServerHttp getInstanceThreads(int n){      
        if (nThread!=n){
            instance=new ServerHttp(n);               
        }
        return instance;
    }  
   
}
