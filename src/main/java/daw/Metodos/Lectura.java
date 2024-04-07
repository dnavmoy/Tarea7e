/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.Metodos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import daw.App;
import daw.CatalogoApp;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author daniel
 */
public class Lectura {
    
    public static void leerXml(String ruta) throws JAXBException{
        
        //FileNotFoundException {

        // Crea el contexto JAXB 
        JAXBContext contexto = JAXBContext.newInstance(CatalogoApp.class);
        // Crea el objeto Unmarshaller
        Unmarshaller um = contexto.createUnmarshaller();

        // Llama al método de unmarshalling
        CatalogoApp catalogo = (CatalogoApp) um.unmarshal(new File(ruta));

        ArrayList<App> listaApp = catalogo.getListaApp();

        listaApp.forEach(System.out::println);
    //}
        
    }
    
    public static void leerJson(String ruta) throws IOException{
        
        
        ObjectMapper mapeador = new ObjectMapper();
        //mapeador.registerModule(new JavaTimeModule());
        
        ArrayList<App> catalogo = mapeador.readValue(new File(ruta),
                    mapeador.getTypeFactory().constructCollectionType(ArrayList.class,App.class));
        System.out.println("---- Catálogo de App ----");
        for (App app : catalogo) {
            System.out.println(app);
        }
        
        
        
                    
    }
    public static void leer1Json(String ruta) throws IOException{
        
        
        ObjectMapper mapeador = new ObjectMapper();
        //mapeador.registerModule(new JavaTimeModule());
        
        
        App app= mapeador.readValue(new File(ruta), App.class);

        System.out.println("---- Mostrar App ----");
        
        System.out.println(app);
        
        
        
        
                    
    }
    
}
