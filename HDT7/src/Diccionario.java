
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
        BinaryTree<Association<String,String>>raiz;
        ArrayList <String> oracion = new ArrayList<String>();
    
    public Diccionario(){
        raiz=new BinaryTree<Association<String,String>>();
        llenarDiccionario();
        traducirOracion();
    }
    
    public void llenarDiccionario(){
        ArrayList<String> palabras= new ArrayList<String>();
        ArrayList<Association<String,String> >asociaciones= new ArrayList<Association<String,String>>();
        //Comienza código tomado de internet
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           //deben cambiarlo para el lugar en sus compus
           archivo = new File ("C:\\Users\\Daniel\\Documents\\Daniel pers\\U\\Segundo año\\Segundo Semestre\\Estructuras de Datos\\Hojas de Trabajo\\HDT7\\diccionario.txt");
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
        for(int i=0; i<palabras.size();i++){
               int lugar=palabras.get(i).indexOf(',');
               String ingles=palabras.get(i).substring(0,lugar);
               String espaniol=palabras.get(i).substring(lugar,palabras.get(i).length()-1);
               asociaciones.add(new Association(ingles, espaniol));
        }
        
        raiz.setValue(asociaciones.get(0));
        for (int i=1; i<asociaciones.size(); i++){
            insertarNodo(raiz, asociaciones.get(i));
        }
     }
    
    private void insertarNodo(BinaryTree<Association<String,String>> padre, Association<String,String> dato)
    {
        Association<String,String> asociacion=padre.value();
        String llavePadre=asociacion.getKey();
        String llaveDato=dato.getKey();
        int num=llavePadre.compareToIgnoreCase(llaveDato);
        if(num>0 && padre.left()==null){
            padre.setLeft(new BinaryTree<Association<String,String>>(dato));
        }else if(padre.left()!=null){
            insertarNodo(padre.left(),dato);
        }
        
        if(num<0 && padre.right()==null){
            padre.setLeft(new BinaryTree<Association<String,String>>(dato));
        }else if(padre.right()!=null){
            insertarNodo(padre.right(),dato);
        }
    }
    
     private String traducirPalabra(BinaryTree<Association<String,String>> padre, String palabra)
{
	String palabraTraducida = "";
	Association<String,String> asociacion=padre.value();
       	String llavePadre=asociacion.getKey();
	int num=llavePadre.compareToIgnoreCase(palabra);
	if(num==0){
		palabraTraducida=padre.value().getValue();
	}
	if(num<0){
            if(padre.right()!=null){
                palabraTraducida=traducirPalabra(padre.right(),palabra);
            }else{
                return ("*"+palabra+"*");
            }
	}
	if(num>0){
            if(padre.left()!=null){
                    palabraTraducida=traducirPalabra(padre.left(),palabra);
            }else{
                    return ("*"+palabra+"*");
            }
		
	}

	return palabraTraducida;
    }

     private void leerOracion(){
	String palabras="";
	//Comienza código tomado de internet
        try {
           // Apertura del fichero y creacion de BufferedReader para poder
           // hacer una lectura comoda (disponer del metodo readLine()).
           //deben cambiarlo para el lugar en sus compus
           archivo = new File ("C:\\Users\\Daniel\\Documents\\Daniel pers\\U\\Segundo año\\Segundo Semestre\\Estructuras de Datos\\Hojas de Trabajo\\HDT7\\texto.txt");
           fr = new FileReader (archivo);
           br = new BufferedReader(fr);

           // Lectura del fichero
           String linea;
           int ind=0;
           while((linea=br.readLine())!=null){
              	palabras=linea;
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

	while(palabras.compareTo("")!=0){
	int lugar=palabras.indexOf(' ');
            if(lugar!=-1){
                    oracion.add(palabras.substring(0,lugar));
                    palabras=palabras.substring(lugar);
            }else{
                    oracion.add(palabras);
                    palabras="";
            }
        }
     }
     
     
    public void traducirOracion(){
        leerOracion();
        String resultado="";
        for(int i=0; i<oracion.size(); i++){
                resultado+=traducirPalabra(raiz, oracion.get(i))+" ";
        }
        System.out.println(resultado);
    }



}
