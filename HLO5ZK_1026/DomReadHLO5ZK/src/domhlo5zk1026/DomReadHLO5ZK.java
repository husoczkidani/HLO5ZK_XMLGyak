package domhlo5zk1026;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.w3c.dom.*;

public class DomReadHLO5ZK {
	
	public static void main(String[] args) {

		try {
			File input = new File("usersHLO5ZK.xml");
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(input);
			
			doc.getDocumentElement().normalize();
			
			System.out.println("Root element: "+ doc.getDocumentElement().getNodeName());
			
			NodeList nodes = doc.getElementsByTagName("user");
			
			for(int i = 0; i < nodes.getLength();i++) {
				Node node = nodes.item(i);
				System.out.println("\nCurrent element: " +node.getNodeName());
				
				if(node.getNodeType() == Node.ELEMENT_NODE) {
					Element element = (Element) node;
					
					System.out.println("User id: " + element.getAttribute("id"));
					System.out.println("First Name: " + element.getElementsByTagName("firstname").item(0).getTextContent());
					System.out.println("Last Name: " + element.getElementsByTagName("lastname").item(0).getTextContent());
					System.out.println("Profession: " + element.getElementsByTagName("profession").item(0).getTextContent());
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
