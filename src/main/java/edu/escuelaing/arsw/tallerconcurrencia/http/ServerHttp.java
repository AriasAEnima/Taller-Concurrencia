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


/**
 *
 * @author J. Eduardo Arias
 */
public class ServerHttp implements Runnable {
    private static ServerHttp instance;
    private static final Object lock=new Object();
    private ExecutorService executor;
    private ServerSocket serverSocket=null;
    
    /**
     * Crea un Server con newCachedThreadPool
     */
    private ServerHttp(){      
        executor =Executors.newCachedThreadPool();         
    }

    /**
     * Crea un Server con newFixedThreadPool
     * @param n numero de hilos
     */
    private ServerHttp(int n){                 
        executor = Executors.newFixedThreadPool(n);        
    }
    
    /**
     * Mantiene el servidor corriendo hasta que el ServerSocket
     * se cierra, por cada socket aceptado creara un hilo.
     */
    @Override
    public void run() {        
        int port=getPort();
        try {           
            serverSocket = new ServerSocket(port);            
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+port);
            System.exit(1);
        }     
        System.out.println("Listo para recibir ...");     
        try {
            while (true) {   
                synchronized (lock) {
                //System.out.println("Esperando ...");                
                Socket clientSocket = serverSocket.accept();
                Runnable process = new ClientSocketProcess(clientSocket);
                executor.execute(process);
                }
                
            }
        } catch (IOException ex) {          
            executor.shutdown();
            while (!executor.isTerminated()) {
                // Espero a que terminen de ejecutarse todos los procesos       	
            }
        }
    }
    
    /**
     * Cierra el socket y para el Server.
     */ 
    public void stop() {            
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.err.println(ex.getMessage()+": No se pudo iniciar el socket o este fue cerrado con proposito"); 
        }
    }

    /**
     * Devuelve un Server con thread
     * @param args ninguno      
     */
    public static void main(String[] args) {      
        getInstance().run();
    }    
    
    /**
     * Devuelve la instancia del Server
     * @return Devuelve el server Cachedpool
     */
    
    public static ServerHttp getInstance(){      
        if(instance==null){
            synchronized(ServerHttp.class){
                instance=new ServerHttp();
            }
        }
        return instance;
    }  
    
    /**
     * Este metodo SOLO es para pruebas se hace necesario,
     * necesitaremos diferentes tipos de server.
     * (rompe el patron singleton)
     * @param n numero de hilos de server de prueba
     * @return un server con n hilos para atender
     */
    public static ServerHttp getTestServer(int n){   
        return new ServerHttp(n);      
    }  
    
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
 
}
