import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;


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