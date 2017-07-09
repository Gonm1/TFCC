/*
 * Integrantes:
 * 	- Manuel Lepe Fa�ndez.
 * 	- Gonzalo Miranda Cabrera.
*/
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;


public class Metodos{
	//Variables estaticas que se usan la interface Individual:
	public static ArrayList<String> movimiento_cinta=new ArrayList<String>();
	public static ArrayList<String> proximo_movimiento=new ArrayList<String>();
	public static ArrayList<Integer> numero_cabezal=new ArrayList<Integer>();
	public static ArrayList<String> estado_actual=new ArrayList<String>();
	public static int numero_transiciones=0;


	//busca si se encuentra el estado qi
	public static boolean Estaqi(ArrayList<Estado> mt,String qi){
		for(Estado o: mt){
			if(o.qi.equals(qi)) return true;	
		}
		return false;
	}

	//insertar estado completo con la MT:
	public static ArrayList<Estado> InsertarEstado(ArrayList<Estado> mt,String qi,String si,String sj,String qj,String movimiento){
		Estado estado_aux= new Estado(qi);
		ArrayList<Transicion> transiciones_aux= new ArrayList<Transicion>();
		Transicion transicion_aux= new Transicion(si,sj,qj,movimiento);
		transiciones_aux.add(transicion_aux);
		estado_aux.llenarTran(transiciones_aux);
		mt.add(estado_aux);
		return mt;
	}

	//Insertar solo transiciones (el estado qi ya existe en la mt):
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

	//Insertar estado sin transiciones en MT::
	public static ArrayList<Estado> InsertarEstadoSinTransicion(ArrayList<Estado> mt,String qi){
		Estado estado_aux= new Estado(qi);
		mt.add(estado_aux);
		return mt;
	}

	//Metodo que lee desde el archivo xml y retorna la MT:
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
		//Se determina el Nodo Raiz t los nodos Hijos:
		Node nodoRaiz = documento.getFirstChild();
		NodeList listaNodosHijos = nodoRaiz.getChildNodes();
		//Se recorre los Nodos Hijos:
		for (int i=0; i<(listaNodosHijos.getLength()); i++){
			Node unNodoHijo;
			unNodoHijo = listaNodosHijos.item(i);
			if (unNodoHijo.getNodeType() == Node.ELEMENT_NODE) {
				//Seguarda en String las etiquetas:
				numero_transiciones++;
				Element e = (Element) unNodoHijo;
				String qi= e.getElementsByTagName("qi").item(0).getTextContent();
				//en casi del archivo solo tenga estado inicial:
				try{
					String si= e.getElementsByTagName("si").item(0).getTextContent();
					String sj= e.getElementsByTagName("sj").item(0).getTextContent();
					String qj= e.getElementsByTagName("qj").item(0).getTextContent();
					String movimiento= e.getElementsByTagName("movimiento").item(0).getTextContent();

					//inserta las transiciones(primero verifica si existe el estado 
					if(Estaqi(mt,qi)) 
						mt=InsertarTransicion(mt,qi,si,sj,qj,movimiento);//si existe el estado solo inserta la transición
					else 
						mt=InsertarEstado(mt,qi,si,sj,qj,movimiento); //si no existe el estado qi inserta el estado completo.
					//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

				}catch(Exception spe){
					spe.printStackTrace();
					//algun problema del archivo xml mal formado.
				}

			}
		}
	
		mt=ComprobarEstados(mt);//Comprueba si estan todo los estados(cuando en mt hay un estado sin transicion no se agrega al xml
								//este metodo agrega esos estados
		return mt;

	}

	//Se encarga que en la mt esten todos los estados(para los estados sin transicion);
	public static ArrayList<Estado> ComprobarEstados(ArrayList<Estado> mt){
		ArrayList<String> para_insertar=new ArrayList<String>();
		boolean aux2=false;	
		for(Estado o:mt){
			//el Try catch es por si un estado no tiene transiciones.
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
		//en el arraylist para_insertar están todos los estados que se deben insertar
		for(String o:para_insertar){
			mt=InsertarEstadoSinTransicion(mt,o);		
		}       
		
		return mt;    		
	}
	
	//METODO QUE CREA LA CINTA DE LA MAQUINA
	public static Cinta CrearCinta(String cadena){
		Cinta cadenas=new Cinta();
		String[] separada = cadena.split(""); 

		for(int j=0;j<separada.length;j++){
			cadenas.insertarFinal(separada[j]);

		}

		return cadenas;
	}
	
	//Metodo recursivo que se encarga de verificar si una cadena pertenece a la cinta 
	public static boolean verificarCadena(ArrayList<Estado> mt,Cinta cadena, String qii,NodoCinta caracter){
		Agregar_movimientos(qii,cadena,caracter); //agrega un nuevo movimiento para ser mostrado en la parte dinamica de la interface Individual
		for(Estado o:mt){
			if(o.qi.equals(qii)){
				//En caso de que no tenga transiciones y o.todasTransiciones este null por eso el try;
				try{
					for (Transicion t: o.todasTransiciones) {
						if(t.si.equals(caracter.simbolo)){
							caracter.simbolo=t.sj;//cambia si por sj.
							if(!t.movimiento.equals("E")){ 
								NodoCinta aux=cadena.moverse(caracter,t.movimiento);
								agregar_Smovimiento(t.qj,t.sj,t.movimiento);
								return verificarCadena(mt,cadena,t.qj,aux); //llamada recursiva con los nuevas variables 
							}else{
								agregar_Smovimiento(t.qj,t.sj,"E");
								return verificarCadena(mt,cadena,t.qj,caracter);//llamada recursiva con el mismo cacacter ya que no se mueve la cinta.
							}
						}
					}

				}catch(Exception spe){
					agregar_Smovimiento("null","null","null");
					if(o.estadoFinal==true)	return true; //si se llega a un estado sin transiciones y es final retorna true(la cadena es aceptada por el lenguaje)
					else return false; //Caso contrario.
				}
				
				//no encuentra transiciones para si:
				agregar_Smovimiento("null","null","null");
				if(o.estadoFinal==true) return true;
				else return false;				
			}
		}

		return true;
	}

	//Agrega todos los estados a un <arraylist<estado> para cuando hay que escoger un estado final 
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

	//agrega String para ver como va quedando la cinta nos sirvira para la parte dinamica
	public static void Agregar_movimientos(String qi,Cinta cadena,NodoCinta cabezal){
		estado_actual.add(qi);
		String ca="##";
		int i=0;
		NodoCinta paso = cadena.inicio;
		while(paso!=null){
			ca=ca+paso.simbolo;
			if(paso==cabezal) numero_cabezal.add(i+2);
			paso = paso.sig;
			i++;
		}
		ca=ca+"##";
		movimiento_cinta.add(ca);
	}

	//agrega el próximo movimiento que se hará.
	public static void agregar_Smovimiento(String qj,String sj,String movimiento){
		if(!qj.equals("null")) proximo_movimiento.add("("+qj+","+sj+","+movimiento+")");         
		else proximo_movimiento.add("(No hay movimiento)"); 

	}

}
