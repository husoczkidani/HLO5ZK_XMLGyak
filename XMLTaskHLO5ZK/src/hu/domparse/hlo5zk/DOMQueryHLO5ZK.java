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
			
			// XPath objektum létrehozása
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			// Az osszes konyv 
			String expression = "/Rendelesek/Konyv";
			
			// Az utolso szerzo
			//String expression = "/Rendelesek/Szerzo[position()<2]";
			
			//
			//String expression = "/Rendelesek/Konyv[Oldalszam>250]";
			
			
			//Query
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
			
			//Eredmeny
			for (int j = 0; j < nodeList.getLength(); j++) {
				Node node = nodeList.item(j);
				System.out.println("\nElem típusa: " + node.getNodeName());
				Node nNode = nodeList.item(j);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element elem = (Element)nNode;
					
					//Azonosito
					int idNum = 0;
					String tag = elem.getNodeName();
					for(int t = 0; t < getIds.length; t++) {
						if(getIds[t].equals(tag)) {
							idNum = t+1;
						}
					}
					String id = elem.getAttribute(getIds[idNum]);
					System.out.println(getIds[idNum]+": " + id);
					
					//Tulajdonsagok
					String text = "";
					NodeList childNodes = elem.getChildNodes();
					for (int k = 0; k < childNodes.getLength(); k++) {
						if (!(childNodes.item(k).getTextContent().trim().equals(""))) {
							
							//Formalas
							text = childNodes.item(k).getTextContent().trim();
							text = text.replaceAll("\\n", ", ").replaceAll("\\s+", " ");
							
							System.out.println(childNodes.item(k).getNodeName() + ": " + text);
						}
					}
				}
				System.out.println();
			}
		}
		// Esetleges hibák kezelése	
		catch(ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
			System.out.println("An error occured!\nError Message:\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}