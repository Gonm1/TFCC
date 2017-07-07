import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

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
