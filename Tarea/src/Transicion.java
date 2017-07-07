import java.util.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Scanner;

public class Transicion{
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