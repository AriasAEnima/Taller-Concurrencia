/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http.writer;

import java.io.BufferedReader;
import java.net.Socket;

/**
 *
 * @author J. Eduardo Arias
 */
public interface ResourceWriter {
    
    /**
     * Permite responder el recurso solicitado.
     * @param file path del archivo
     * @param clientSocket para escribir ahi
     */
    public void write(String file,Socket clientSocket);
    /**
     * @return Devuelve el tipo exacto de ResourceWriter
     */
    public String exactType();
}
