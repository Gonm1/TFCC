/*
 * Integrantes:
 * 	- Manuel Lepe Fa�ndez.
 * 	- Gonzalo Miranda Cabrera.
*/
public class NodoCinta{
	String simbolo;
	NodoCinta ant;
	NodoCinta sig;

	public NodoCinta(String simbolo1){
		simbolo=simbolo1;
		ant = null;
		sig = null;
	}
}