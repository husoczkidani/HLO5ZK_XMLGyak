package hu.domparse.hlo5zk;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMQueryHLO5ZK {

	public static void main(String[] args) {
		
		String[] getIds = { "Konyvesbolt","Kb_Id", "Rendeles","Rend_Id", "Resze", "ReszeId", "Konyv","ISBN", "Szerzo","Szerzo_Id" };
		
		try {
			// XML file beolvasasa DOM letrehozasa	
			File xmlFile = new File("XMLHLO5ZK.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			System.out.println("Könyvek melyek több mint 250 oldalasak:\n-------------------------");
			NodeList konyvek = doc.getElementsByTagName("Konyv");
			
			for(int i = 0; i<konyvek.getLength();i++) {
				Element konyv = (Element)konyvek.item(i);
				NodeList childNodes = konyv.getChildNodes();
				for(int j = 0; j<childNodes.getLength();j++) {
					Node childNode = childNodes.item(j);
					if(childNode.getNodeName().equals("Oldalszam")) {
						if(Integer.parseInt(childNode.getTextContent()) > 250) {
							
							writeId(konyv,getIds);
							printChildNodes(konyv);
						}
					}
				}
			}
			
			System.out.println("sz01 -es azonosítójú szerző:\n-------------------------");
			NodeList szerzok = doc.getElementsByTagName("Szerzo");
			
			for(int i = 0; i<szerzok.getLength();i++) {
				Element szerzo = (Element)szerzok.item(i);
				if(szerzo.getAttribute("Szerzo_Id").equals("sz01")) {
					writeId(szerzo,getIds);
					printChildNodes(szerzo);
				}
			}
			
			System.out.println("Sátoraljaújhelyi könyvesboltok:\n-------------------------");
			NodeList konyvesboltok = doc.getElementsByTagName("Konyvesbolt");
			
			for(int i = 0; i< konyvesboltok.getLength(); i++) {
				Element konyvesbolt = (Element)konyvesboltok.item(i);
				NodeList childNodes = konyvesbolt.getChildNodes();
				for(int j = 0; j < childNodes.getLength(); j++) {
					Node childNode = childNodes.item(j);
					if(childNode.getNodeName().equals("Cim")) {
						NodeList childChildNodes = childNode.getChildNodes();
						for(int k = 0; k< childChildNodes.getLength();k++) {
							Node childChildNode = childChildNodes.item(k);
							if(childChildNode.getNodeName().equals("Telepules")) {
								if(childChildNode.getTextContent().equals("Sátoraljaújhely")) {
									writeId(konyvesbolt,getIds);
									printChildNodes(konyvesbolt);
								}
							}
						}
					}
				}
			}
			
		}
		// Esetleges hibák kezelése	
		catch(IOException | ParserConfigurationException | SAXException e) {
			System.out.println("An error occured!\nError Message:\n" + e.getMessage());
			e.printStackTrace();
		}
	}
	// A kiválasztott element Id-jének a kiírása
	private static void writeId(Element elem, String[]getIds) {
		int idNum = 0;
		String tag = elem.getNodeName();
		for(int t = 0; t < getIds.length; t++) {
			if(getIds[t].equals(tag)) {
				idNum = t+1;
			}
		}
		String id = elem.getAttribute(getIds[idNum]);
		System.out.println(getIds[idNum]+": " + id);
	}
	// A kiválasztott element Tulajdonságainak a kiírása
	private static void printChildNodes(Element elem) {
		String text = "";
		NodeList childNodes = elem.getChildNodes();
		for (int k = 0; k < childNodes.getLength(); k++) {
			if (!(childNodes.item(k).getTextContent().trim().equals(""))) {
				
				//Szöveg formázása
				text = childNodes.item(k).getTextContent().trim();
				text = text.replaceAll("\\n", ", ").replaceAll("\\s+", " ");
				
				System.out.println(childNodes.item(k).getNodeName() + ": " + text);
			}
		}
		System.out.println("");
	}
}