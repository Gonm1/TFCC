import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

class Transicion{
		String sj;
		String si;
		String qj;
		String movimiento;
		Transicion sig;

		public Transicion(String sii,String sjj,String qjj,String movimiento1){
			sj=sjj;
			si=sii;
			qj=qjj;
			movimiento=movimiento1;

		}

}

class Estado{

	String qi;
	boolean estadoFinal=false;
	ArrayList<Transicion> todasTransiciones;

	public Estado(String qii){
		qi=qii;
	}
	
	public void llenarTran(ArrayList<Transicion> todasTransiciones1){
		todasTransiciones=todasTransiciones1;
		
	}
}

class NodoCinta{
	String simbolo;
	NodoCinta ant;
	NodoCinta sig;
	
	public NodoCinta(String simbolo1){
		simbolo=simbolo1;
		ant = null;
		sig = null;
	}
}

class Cinta{
	NodoCinta inicio;
	NodoCinta fin;
	
	public Cinta(){
		inicio = null;
		fin = null;
	}

	public void insertarFinal(String simbolo){

        if(fin==null){
            fin=new NodoCinta(simbolo);
            inicio=fin;
        }else{
            NodoCinta aux=new NodoCinta(simbolo);
            aux.ant=fin;
            fin.sig=aux;
            fin=aux;
        }            
    }

    public void insertarInicio(String simbolo){
		if(inicio==null){
            inicio=new NodoCinta(simbolo);
            fin=inicio;
        }else{
           	NodoCinta aux=new NodoCinta(simbolo);
            aux.sig=inicio;
            inicio.ant=aux;
            inicio=aux;
        }       
	}

	public NodoCinta moverse(NodoCinta si,String derIzq){

		if (derIzq.equals("R")) {
				if (si.sig==null) {
					insertarFinal("#");
					return fin;
				}else{
					return si.sig;
				}
		}else{
				if (si.ant==null) {
					insertarInicio("#");
					return inicio;
				}else{
					return si.ant;
				}

		}
	}
}

public class Metodos{

	//Verifica si se encuentra un estado qi en la maquina:
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
                        Element e = (Element) unNodoHijo;
                       	String qi= e.getElementsByTagName("qi").item(0).getTextContent();
            			
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
                    }
               }
            mt=ComprobarEstados(mt);// comprueba si se encuentra todos los estados en la maquina.
       		return mt;
 
    }

    //Se encarga que en la mt esten todos los estados(para los estados sin transicion);
    public static ArrayList<Estado> ComprobarEstados(ArrayList<Estado> mt){
		for(Estado o: mt){
            	try{
           			for (Transicion t: o.todasTransiciones) {
 	          			if(!(Estaqi(mt,t.qj))) mt=InsertarEstadoSinTransicion(mt,t.qj);
           			}
            	}catch(Exception spe){
	  				
           		}
        }
        return mt;    		

    }

    //crea la cinta a partir de un string ingresado por el usario.
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
    				//En caso de que no tenga transiciones(estado sin transicion) y o.todasTransiciones este null por eso el try;
    					try{
    						for (Transicion t: o.todasTransiciones) {
 	  							if(t.si.equals(caracter.simbolo)){
 	          						caracter.simbolo=t.sj;
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
 

}
