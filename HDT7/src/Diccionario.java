
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Edgar Chamo 13083
 * @author Daniel Gerendas 13158
 * @author Aaron Giron 13042
 */
public class Diccionario {
    File archivo = null;
      FileReader fr = null;
      BufferedReader br = null;
    
    public void llenarDiccionario(){
        ArrayList<String> palabras= new ArrayList<String>();
        //Comienza código tomado de internet
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           archivo = new File ("C:\\Users\\Daniel\\Documents\\Daniel pers\\U\\Segundo año\\Segundo Semestre\\Estructuras de Datos\\Hojas de Trabajo\\diccionario.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           int ind=0;
           while((linea=br.readLine())!=null){
              palabras.add(linea);
           }
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{                    
              if( null != fr ){   
                 fr.close();     
              }                  
           }catch (Exception e2){ 
              e2.printStackTrace();
           }
        }
        //termina codigo tomado dd internet
      }
}
