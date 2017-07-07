import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

public class Metodos{
	public static int numero_transiciones=0;
	//busca si se encuentra el estado qi
	public static boolean Estaqi(ArrayList<Estado> mt,String qi){
				for(Estado o: mt){
					if(o.qi.equals(qi)) return true;	
				}
			return false;
	}

	//insertar estado completo:
	public static ArrayList<Estado> InsertarEstado(ArrayList<Estado> mt,String qi,String si,String sj,String qj,String movimiento){
		Estado estado_aux= new Estado(qi);
		ArrayList<Transicion> transiciones_aux= new ArrayList<Transicion>();
		Transicion transicion_aux= new Transicion(si,sj,qj,movimiento);
		transiciones_aux.add(transicion_aux);
		estado_aux.llenarTran(transiciones_aux);
		mt.add(estado_aux);
		return mt;
	}

	//insertar Solo transiciones (el estado qi ya existe en la mt):
	public static ArrayList<Estado> InsertarTransicion(ArrayList<Estado> mt,String qi,String si,String sj,String qj,String movimiento){
		for(Estado o: mt){
			if(o.qi.equals(qi)){
				Transicion transicion_aux= new Transicion(si,sj,qj,movimiento);
				o.todasTransiciones.add(transicion_aux);
				return mt;
			}	
		}
		return null; 
	}

	//Insertar estado sin transiciones:
	public static ArrayList<Estado> InsertarEstadoSinTransicion(ArrayList<Estado> mt,String qi){
		Estado estado_aux= new Estado(qi);
		mt.add(estado_aux);
		return mt;
	}
	
	//metodo que lee desde el archivo xml
	public static ArrayList<Estado> Leerxml(String ruta_archivo){

			ArrayList<Estado> mt= new ArrayList<Estado>();
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance ( );
            Document documento = null;
            try{
                  DocumentBuilder builder = factory.newDocumentBuilder();
                  documento = builder.parse( new File(ruta_archivo));
            }catch (Exception spe){
                // Algún tipo de error: fichero no accesible, formato de XML incorrecto, etc.
            }

            Node nodoRaiz = documento.getFirstChild();
            NodeList listaNodosHijos = nodoRaiz.getChildNodes();
                for (int i=0; i<(listaNodosHijos.getLength()); i++){
                    Node unNodoHijo;
                    unNodoHijo = listaNodosHijos.item(i);
                    if (unNodoHijo.getNodeType() == Node.ELEMENT_NODE) {
                    	numero_transiciones++;
                        Element e = (Element) unNodoHijo;
                       	String qi= e.getElementsByTagName("qi").item(0).getTextContent();
            			
            			try{
                        	String si= e.getElementsByTagName("si").item(0).getTextContent();
                        	String sj= e.getElementsByTagName("sj").item(0).getTextContent();
                        	String qj= e.getElementsByTagName("qj").item(0).getTextContent();
                       		String movimiento= e.getElementsByTagName("movimiento").item(0).getTextContent();
                       	
                       	//inserta las transiciones(primero verifica si existe el estado 
                       		if(Estaqi(mt,qi)) 
								mt=InsertarTransicion(mt,qi,si,sj,qj,movimiento);
							else 
								mt=InsertarEstado(mt,qi,si,sj,qj,movimiento);
						//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                    	}catch(Exception spe){
                    		spe.printStackTrace();
                    		//algun problema del archivo xml mal formado.
                    	}

                    }
               }
            mt=ComprobarEstados(mt);
       		return mt;
 
    }

    //Se encarga que en la mt esten todos los estados(para los estados sin transicion);
    public static ArrayList<Estado> ComprobarEstados(ArrayList<Estado> mt){
    	ArrayList<String> para_insertar=new ArrayList<String>();
    	boolean aux2=false;	
		for(Estado o:mt){
            	try{
           			for (Transicion t: o.todasTransiciones) {
 	          			if(!(Estaqi(mt,t.qj))){
 	          				String qi_aux= t.qj;
 	          				for(String a:para_insertar){
										if(a.equals(qi_aux)) aux2=true;			
							}
							if(!aux2) para_insertar.add(qi_aux);
							aux2=false;
 	          			}
           			}
            	}catch(Exception spe){
	  				
           		}
        }
		for(String o:para_insertar){
				mt=InsertarEstadoSinTransicion(mt,o);		
		}       
        return mt;    		

    }

    public static Cinta CrearCinta(String cadena){
    	Cinta cadenas=new Cinta();
		String[] separada = cadena.split(""); 

		for(int j=0;j<separada.length;j++){
			cadenas.insertarFinal(separada[j]);

		}

		return cadenas;
    }

    public static boolean verificarCadena(ArrayList<Estado> mt,Cinta cadena, String qii,NodoCinta caracter){
    	for(Estado o:mt){
    			if(o.qi.equals(qii)){
    				//En caso de que no tenga transiciones y o.todasTransiciones este null por eso el try;
    					try{
    						for (Transicion t: o.todasTransiciones) {
 	  							if(t.si.equals(caracter.simbolo)){
 	          						caracter.simbolo=t.sj;
 	          						//System.out.println("movimiento= "+t.movimiento);
 	          						if(!t.movimiento.equals("E")){
 	          							NodoCinta aux=cadena.moverse(caracter,t.movimiento);
 	          							return verificarCadena(mt,cadena,t.qj,aux);
 	          						}else{
 	          							return verificarCadena(mt,cadena,t.qj,caracter);
 	          						}
 	          					}
           					}
           						
           				}catch(Exception spe){
	  						if(o.estadoFinal==true)	return true;
           					else return false;
           				}
           			
      				if(o.estadoFinal==true) return true;
           			else return false;				

    			}
    	}
    				
   	return true;
    }

    //metodo extra que muestra el estado por pantalla:
    public static void mostrarMaquina(ArrayList<Estado> mt){
		System.out.println("MAQUINA DE TURING:");
    	for(Estado o: mt){
            System.out.print("Estado "+ o.qi+" Tranciciones:");
            	try{
           			for (Transicion t: o.todasTransiciones) {
 	          			System.out.print(" si="+t.si+" sj="+t.sj+" qj="+t.qj+" Movimiento="+t.movimiento+" ||");
           			}
            	}catch(Exception spe){
	  				System.out.println("Sin transiciones");
           		}
           		System.out.println();	
        }

    }

    public static String[] todos_estados(ArrayList<Estado> mt){
        int largo=2;
        largo=mt.size();
        String[] estados=new String[largo+1];
        estados[0]="seleccionar...";
        int aux=1;
        for(Estado o: mt){
            estados[aux]=o.qi;
            aux++;
        }
        return estados;
    }
 
}
