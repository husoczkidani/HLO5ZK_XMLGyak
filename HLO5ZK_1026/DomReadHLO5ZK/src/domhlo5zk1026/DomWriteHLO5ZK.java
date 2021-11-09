package domhlo5zk1026;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class DomWriteHLO5ZK {

	public static void main(String[] args) {
		
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			Element users = doc.createElementNS("domhlo5zk","users");
			doc.appendChild(users);
			
			users.appendChild(createUser(doc, "01", "Husóczki","Dániel","Programozó"));
			users.appendChild(createUser(doc, "02", "Mézga", "Géza","Urutazó"));
			users.appendChild(createUser(doc, "03", "Ero" , "Jeno" , " Sportoló"));
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transf = transformerFactory.newTransformer();
			
			transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transf.setOutputProperty(OutputKeys.INDENT, "yes");
			transf.setOutputProperty("{https://xml.apache.org/xslt}indent-amount", "2");
			
			DOMSource source = new DOMSource(doc);
			
			File myFile = new File("users1HLO5ZK.xml");
			
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult(myFile);
			
			transf.transform(source, console);
			transf.transform(source, file);
		}catch(Exception e ) {
			System.out.println("hiba");
		}

	}
	private static Node createUser(Document doc, String id, String firstname, String lastname, String profession) {
		Element user = doc.createElement("user");
		
		user.setAttribute("id", id);
		user.appendChild(createUserElement(doc,"firstname",firstname));
		user.appendChild(createUserElement(doc,"lastname",lastname));
		user.appendChild(createUserElement(doc,"profession",profession));
		
		return user;
	}
	
	private static Node createUserElement(Document doc, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		
		return node;
	}

}
