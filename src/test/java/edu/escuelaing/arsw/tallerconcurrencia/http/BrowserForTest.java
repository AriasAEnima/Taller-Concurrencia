/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


/**
 *
 * @author J. Eduardo Arias
 */
public class BrowserForTest implements Runnable{
    private final String resource;
    public boolean fail=false;
    public String message="Correcto";  

    public BrowserForTest(String resource){
        this.resource=resource;
    }
    /**
     * Simula un Browser haciendo una peticion del path del recurso
     * ingresado en el constructor.
     */
    @Override
    public void run() {
         URL google;
        try {
            google= new URL("http://127.0.0.1:35000/"+resource);
            try (BufferedReader reader
                    = new BufferedReader(new InputStreamReader(google.openStream()))) {
//            String inputLine = null;           
//            while ((inputLine = reader.readLine()) != null) {              
//               // System.out.println(inputLine);
//            }   
            } catch (IOException ex) {
                fail = true;
                message = ex.toString();
            }               
        } catch (MalformedURLException ex) {
            System.err.println("Error en la URL");
        }
      
    }
}


