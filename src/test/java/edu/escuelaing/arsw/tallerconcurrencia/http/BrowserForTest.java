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
    private String resource;
    public boolean fail=false;
    public String message="";
    private static Object lock=new Object();

    public BrowserForTest(String resource){
        this.resource=resource;
    }

    @Override
    public void run() {
        URL google=null;
        try {
            google= new URL("http://127.0.0.1:35000/"+resource);
        } catch (MalformedURLException ex) {
            System.err.println("Error en la URL");
        }
       try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(google.openStream()))) {
//            String inputLine = null;           
//            while ((inputLine = reader.readLine()) != null) {              
//               // System.out.println(inputLine);
//            }   
        } catch (IOException ex) {            
            fail=true;
            message=ex.toString();            
        }
    }
}


