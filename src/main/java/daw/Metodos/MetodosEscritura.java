/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daw.Metodos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import daw.App;
import daw.CatalogoApp;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author daniel
 */
public class MetodosEscritura {

    public static void escribirCatalogo(ArrayList<App> lista) throws JAXBException {
        CatalogoApp catalogo = new CatalogoApp();
        catalogo.setLista(lista);
        catalogo.setDescripcion("listado App para xml");
        JAXBContext contexto = JAXBContext.newInstance(CatalogoApp.class);
        Marshaller serializador = contexto.createMarshaller();
        serializador.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // serializador.marshal(catalogo, System.out);
        serializador.marshal(catalogo, new File("listadoxml.xml"));
    }

    public static void escribirJson(ArrayList<App> lista) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapeador.writeValue(new File("listado.json"), lista);
    }

    public static void escribirJson(App app, String ruta) throws IOException {
        ObjectMapper mapeador = new ObjectMapper();
        mapeador.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapeador.writeValue(new File("./aplicacionesJSON/" + ruta), app);
    }
    
}
