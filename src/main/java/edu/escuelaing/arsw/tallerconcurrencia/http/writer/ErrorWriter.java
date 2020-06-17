/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http.writer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class ErrorWriter implements ResourceWriter{ 
  
    private final String message;
   
    /**
     * @param message Codigo+info
     */
    public ErrorWriter(String message ){        
        this.message=message;
    }
    
   /**
    * Escribe un HTML con el mensaje del contructor al socket del cliente
    * @param file Es ignorando este parametro en esta implementacion.
    * @param clientSocket socket del cliente.
    */
    @Override
    public void write(String file, Socket clientSocket) {
        try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String outputLine = "HTTP/1.1 "+message+"\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>"+message+"</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Error "+message+ "</h1>"
                    + "</body>"
                    + "</html>";
            out.println(outputLine);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ErrorWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String exactType() {
        return "error";
    }
    
}
