/*
 * Integrantes:
 * 	- Manuel Lepe Faúndez.
 * 	- Gonzalo Miranda Cabrera.
*/
import java.util.ArrayList;

public class Estado{

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
