package hu.domparse.hlo5zk;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMReadHLO5ZK {

	public static void main(String[] args) {
		
		String[] tags = { "Konyvesbolt", "Rendeles", "Resze", "Konyv", "Szerzo" };
		String[] ids = {"Kb_Id", "Rend_Id", "ReszeId", "ISBN", "Szerzo_Id"};
		
		try {
			
			// XML file beolvasasa DOM letrehozasa
			File xmlFile = new File("XMLHLO5ZK.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			//Gyaker elem kiirasa
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName() + "\n");
			
			//Elemek kiiratasa
			int n = 0;
			for (String tag : tags) {
				NodeList nodeList = doc.getElementsByTagName(tag);
				System.out.println(tag + " elemek:\n----------------------------------------");
				
				//Azonositok es tulajdonsagok
				for (int i = 0; i < nodeList.getLength(); i++) {
					Node node = nodeList.item(i);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						Element element = (Element)node;
						
						//Azonositok
						String id = element.getAttribute(ids[n]);
						System.out.println(ids[n] +": " + id);
						
						//Tulajdonsagok
						String text = "";
						NodeList childNodes = element.getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							if (!(childNodes.item(j).getTextContent().trim().equals(""))) {
								
								//Formalas
								text = childNodes.item(j).getTextContent().trim();
								text = text.replaceAll("\\n", ", ").replaceAll("\\s+", " ");
								
								System.out.println(childNodes.item(j).getNodeName() + ": " + text);
							}	
						}
					}
					System.out.println();
				}
				n++;
			}
			
		// Hibakezeles	
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.out.println("An error occured!\nError Message:\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}
