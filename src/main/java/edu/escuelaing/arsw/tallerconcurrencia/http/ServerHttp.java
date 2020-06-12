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
    private boolean bandera;
    private ExecutorService executor;
    

    @Override
    public void run() {
        ServerSocket serverSocket=null;
        try {           
            serverSocket = new ServerSocket(35000);            
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }     
        System.out.println("Listo para recibir ...");
        long init = System.currentTimeMillis();
        int no=1;
        while(bandera){           
            try {
                System.out.println("Esperando ...");
                Socket clientSocket = serverSocket.accept();
                Runnable process = new ClientSocketProcess(clientSocket);
                executor.execute(process);
                synchronized(lock){
                    System.out.println("Ya respondi ..."+no);
                    no++;                    
                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage()+": No se pudo iniciar el socket"); 
            }
        }     
        executor.shutdown();	
        while (!executor.isTerminated()) {
            // Espero a que terminen de ejecutarse todos los procesos       	
        }
        long fin = System.currentTimeMillis();	// Instante final del procesamiento
        System.out.println("Tiempo total de procesamiento: "+(fin-init)/1000+" Segundos");
    }
    
    private ServerHttp(){
        bandera=true;
        executor = Executors.newFixedThreadPool(2);          
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
   
}
