/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package daw;

import daw.Metodos.Lectura;
import daw.Metodos.MetodosEscritura;
import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;

/**
 *
 * @author daniel
 */
public class Tarea7eDanielNavarro {

    public static void main(String[] args) {
        //genero una lista de apps
        ArrayList<App> listaApp = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listaApp.add(new App());
        }

        //Genera en la raíz del proyecto, a partir de la lista de Apps, 
        //un fichero XML con los datos de todas las aplicaciones.
        try {
            MetodosEscritura.escribirCatalogo(listaApp);
        } catch (JAXBException ja) {
            System.out.println("error creando xml");
        }

        
        //Genera en la raíz del proyecto, a partir de la lista de Apps,
        //un fichero JSON con los datos de todas las aplicaciones.
        try {
            MetodosEscritura.escribirJson(listaApp);
        } catch (IOException io) {
            System.out.println("Error creando json");
        }

        
        //Genera tantos archivos JSON como aplicaciones haya en la lista en una 
        //carpeta llamada ./aplicacionesJSON. El nombre de cada archivo será el
        //nombre de la aplicación en cuestión.
        crearDirectorio("aplicacionesJSON");

        for (int i = 0; i < listaApp.size(); i++) {
            try {
                MetodosEscritura.escribirJson(listaApp.get(i),listaApp.get(i).getNombre()+".json");
            } catch (IOException io) {
                System.out.println("Error creando json");
            }
        }
        
        //Realiza una lectura del fichero XML y muestra los datos de todas las apps por consola.
        System.out.println("lectura xml\n\n\n");
        try{
            Lectura.leerXml("listadoxml.xml");
        }catch(JAXBException jax){
            System.out.println("error leyendo xml");
        }
        
        //Realiza una lectura del fichero JSON y muestra los datos de todas las apps por consola.
        System.out.println("\n\nlectura json \n\n");
        try{
            Lectura.leerJson("listado.json");
        }catch(IOException ioe){
            System.out.println("error leyendo json");
        }
        
        
        //Muestra un listado de los ficheros que hay en ./aplicacionesJSON. 
        
        File rutaApps = new File("./aplicacionesJSON");
        File[] listaficheros = rutaApps.listFiles();
        for(File f: listaficheros){
            System.out.println(f.getName());
        }
        
        
        //Pregunta al usuario el nombre del fichero json (de los anteriores) que
        //quiere leer. Una vez leído muestra los datos de esta app por consola.
        Scanner teclado = new Scanner(System.in);
        System.out.println("introduce nombre archivo a visualizar");
        String nombrearchivo = teclado.nextLine();
        
        try {
            Lectura.leerJson(nombrearchivo);
        } catch (IOException ex) {
            System.out.println("error accediendo al archivo : " + nombrearchivo);
        }
        

    }


    public static void crearDirectorio(String ruta) {

        Path directorio = Paths.get(ruta);

        try {
            Files.createDirectories(directorio);
        } catch (FileAlreadyExistsException faee) {
            System.out.println("No se puede crear " + ruta + " porque ya existe");
        } catch (AccessDeniedException ade) {
            System.out.println("No tiene permisos para crear " + ruta);
        } catch (IOException e) {
            System.out.println("Problema creando el directorio " + ruta);
            System.out.println("Seguramente la ruta está mal escrita o no existe");
        }
    }

}
