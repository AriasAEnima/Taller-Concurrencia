/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class BrowserForTest{

    public BrowserForTest(String resource) {
        URL google=null;
        try {
            google= new URL("http://127.0.0.1:35000/"+resource);
        } catch (MalformedURLException ex) {
            System.err.println("Error en la URL");
        }
       try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;           
            while ((inputLine = reader.readLine()) != null) {              
                System.out.println(inputLine);
            }            
        } catch (IOException ex) {
            System.err.println("Error: "+ex.toString());
           
        }
    }
}


