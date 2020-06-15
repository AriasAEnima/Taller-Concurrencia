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
import java.util.concurrent.Callable;

/**
 *
 * @author J. Eduardo Arias
 */
public class CallableBrowser implements Callable<String> {
    private String resource;   
    private String message="Correcto"; 
    private boolean print;

    public CallableBrowser(String resource){
        this(resource,false);
    }
    public CallableBrowser(String resource,boolean print){
        this.resource=resource;
        this.print=print;
    }
      
    /**
     * @return Retorna "Correcto" si no hay error, de lo contrario
     * el error del servidor. Si print entonces imprime la respuesta del servidor
     * @throws Exception sobre operaciones de Callable
     */
    @Override
    public String call() throws Exception {    
        URL google=null;
        try {
            google= new URL("http://127.0.0.1:35000/"+resource);
            try (BufferedReader reader
                    = new BufferedReader(new InputStreamReader(google.openStream()))) {
                if (print) {
                    printResponse(reader);
                }
            } catch (Exception ex) {
                message = ex.toString();
            }
        } catch (MalformedURLException ex) {
            System.err.println("Error en la URL");
        }              
       return message;        
    }
    
    /**
     * Imprime la respuesta del servidor
     * @param reader BufferedReader a leer
     * @throws IOException si falla el Buffered Reader
     */
    public void printResponse(BufferedReader reader ) throws IOException{
            String inputLine;           
            while ((inputLine = reader.readLine()) != null) {              
                System.out.println(inputLine);
            }           
    }
}
