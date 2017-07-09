public class Cinta{
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
