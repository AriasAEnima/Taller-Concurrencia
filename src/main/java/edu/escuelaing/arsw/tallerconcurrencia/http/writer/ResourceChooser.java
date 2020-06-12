/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arsw.tallerconcurrencia.http.writer;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author J. Eduardo Arias
 */
public class ResourceChooser {
    // Permite a partir de un string devolver un Writer
    public static Map<String,ResourceWriter> selector=new HashMap<String,ResourceWriter>(){{
            put("html",new TextWriter("html"));
            put("png",new ImageWriter("png"));
            put("jpg",new ImageWriter("jpg"));
            put("js",new TextWriter("javascript"));
            put("css",new TextWriter("css"));
            put("err",new ErrorWriter("501 Ese tipo no se admite"));
        }
    };      
    
    /**
     * A partir de un string devuelve un Writer
     * @param path el path del recurso web
     * @return Devuelve el ResourceWriter para el tipo de archivo especifico.
     * @throws Exception si el path no esta bien formado o si no es un archivo
     */
    public static ResourceWriter choose(String path) throws Exception{
        String resource="";
        try{                   
            String[] s=path.split("\\.");    
            resource=s[s.length-1].toLowerCase();           // Existen archivos como owl.min.js  
        }catch(ArrayIndexOutOfBoundsException e){
            throw new Exception(" No es una peticion de Recurso Especifico/ Peticion mal formada");
        }        
        if (!selector.containsKey(resource)){
            return selector.get("err");           
        }else{
            return selector.get(resource);      
        }
          
    }
    
}
