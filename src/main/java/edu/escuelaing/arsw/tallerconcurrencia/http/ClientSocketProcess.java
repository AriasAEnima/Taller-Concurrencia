/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import edu.escuelaing.arsw.tallerconcurrencia.http.writer.ResourceChooser;
import edu.escuelaing.arsw.tallerconcurrencia.http.writer.ResourceWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author J. Eduardo Arias
 */
public class ClientSocketProcess implements Runnable {
    private final Socket clientSocket;

    public ClientSocketProcess(Socket clientSocket) {        
         this.clientSocket=clientSocket;      
    }
    
    /**
     * Una vez aceptado del socket del cliente, abre un BufferedReader
     * y atrapa la petecion GET , utiliza el servicio de ChooseWriter
     * y responde.
     */
    @Override
    public void run() {
            ResourceWriter rw;
            try(BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){                 
                String path=getPath(in);    
                rw = ResourceChooser.choose(path); 
                rw.write(path, clientSocket);                    
                in.close();
                clientSocket.close();              
            } catch (Exception ex) {
                System.err.println(ex.getMessage()+": Error del proceso en el servidor");            
            }
    }
    
   /**
     * Captura el path de una peticion GET
     * @param in Buffer del Socket del Cliente
     * @return el path del archivo.
     * @throws IOException si no es posible leer el buffer
     */
    public static String getPath(BufferedReader in) throws IOException{
        String inputLine,path="";
        while ((inputLine = in.readLine()) != null) {
             System.out.println("Received: " + inputLine);
             if (inputLine.contains("GET")) {
                 path=inputLine.split(" ")[1];                                             
             }
             if (!in.ready()) {
                 break;
             }
        }
        return path;
    }
    
}
