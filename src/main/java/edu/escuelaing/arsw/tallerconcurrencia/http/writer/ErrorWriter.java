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
    private String message;
   
    public ErrorWriter(String message ){        
        this.message=message;
    }
    
   
    public void write(String file, Socket clientSocket) {
        PrintWriter out=null;
        try {                       
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            String outputLine = "HTTP/1.1 "+message+"\r\n"
                    + "Content-Type: text/html\n"
                    + "\r\n"
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
        } finally {
            out.close();
        }
        
    }

    @Override
    public String exactType() {
        return "error";
    }
    
}
