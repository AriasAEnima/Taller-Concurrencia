/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http.writer;

import edu.escuelaing.arsw.tallerconcurrencia.http.writer.ResourceWriter;
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
public class TextWriter implements ResourceWriter{    
    private String type;

    public TextWriter(String type) {
        this.type = type;
    }    
    
    /**
     * Escribe un archivo de texto utilizando el socket del cliente
     * @param file Debe ser el path del archivo relativo a resources (raiz)
     * @param clientSocket para responderle
     */
    @Override
    public void write(String file, Socket clientSocket) {
        PrintWriter out=null;
        try {
            String outputLine="";
            out = new PrintWriter(clientSocket.getOutputStream(), true);           
            BufferedReader bf = new BufferedReader(new FileReader("resources" + file));
            outputLine = "HTTP/1.1 200 OK\r\n";
            outputLine+="Content-Type: text/"+type+"\n";
            outputLine+="\r\n";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine+=bfRead;
            }
            out.println(outputLine);
            out.close();
        } catch (IOException ex) {
            new ErrorWriter("404 NOT FOUND").write("", clientSocket);
        } finally {
            out.close();
        }
        
    }     

    @Override
    public String exactType() {
        return "text/"+type;
    }
    
}
