/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http.writer;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class ImageWriter implements ResourceWriter{
    private String type;
    
    public ImageWriter(String type) {
        this.type = type;
    }    
    
    /**
     * Escribe bits de una imagen utilizando el socket del cliente.
     * @param file path del archivo
     * @param clientSocket para responder
     */
    @Override
    public void write(String file,Socket clientSocket) {        
        FileInputStream inputImage= null;
        try {
            File graphicResource= new File("resources" +file);
            inputImage = new FileInputStream(graphicResource);
            byte[] bytes = new byte[(int) graphicResource.length()];
            inputImage.read(bytes);
            DataOutputStream binaryOut;
            binaryOut = new DataOutputStream(clientSocket.getOutputStream());
            binaryOut.writeBytes("HTTP/1.1 200 OK \r\n");
            binaryOut.writeBytes("Content-Type: image/"+type+"\r\n");
            binaryOut.writeBytes("Content-Length: " + bytes.length);
            binaryOut.writeBytes("\r\n\r\n");
            binaryOut.write(bytes);
            binaryOut.close();
        } catch (IOException ex) {
            new ErrorWriter("404 NOT FOUND").write("", clientSocket);
        }      
    }

    @Override
    public String exactType() {
        return "image/"+type;
    }

   
    
}
