package hu.domparse.hlo5zk;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMModifyHLO5ZK {

	public static void main(String[] args) {
		try {
			
			// XML file beolvasasa DOM letrehozasa	
			File xmlFile = new File("XMLHLO5ZK.xml");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			// A r08-as rendeles reszeben a mennyiseg legyen 30
			NodeList nodes = doc.getElementsByTagName("Resze");
			for	(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getAttributes().getNamedItem("ReszeId").getTextContent().equals("r08")) {
						NodeList childNodes = node.getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node childNode = childNodes.item(j);
							if (childNode.getNodeName().equals("Menny")) {
								childNode.setTextContent("30");
							}
						}
					}
				}
			}
			
			//A rend03 szamu rendelest Jason Statham helyet Bekülo Bela vitte
			nodes = doc.getElementsByTagName("Rendeles");
			for	(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getAttributes().getNamedItem("Rend_Id").getTextContent().equals("rend03")) {
						NodeList childNodes = node.getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node childNode = childNodes.item(j);
							if (childNode.getNodeName().equals("Beszall_neve")) {
								childNode.setTextContent("Békülő Béla");
							}
						}
					}
				}
			}
			
			// Az sz02-es szerzonek valtozzon meg az email cime *@email* helyett *@gmail* -re, a telefonszama pedig legyen 06-16-420-32-64
			nodes = doc.getElementsByTagName("Szerzo");
			for	(int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					if (node.getAttributes().getNamedItem("Szerzo_Id").getTextContent().equals("sz02")) {
						NodeList childNodes = node.getChildNodes();
						for (int j = 0; j < childNodes.getLength(); j++) {
							Node childNode = childNodes.item(j);
							if (childNode.getNodeName().equals("Elerhetoseg")) {
								childNodes = childNode.getChildNodes();
								for(int k = 0;k < childNodes.getLength();k++) {
									childNode = childNodes.item(k);
									if(childNode.getNodeName().equals("Email")) {
										childNode.setTextContent("yvonne.hof@gmail.com");
									}
									if(childNode.getNodeName().equals("Telszam")) {
										childNode.setTextContent("06-16-420-32-64");
									}
								}
							}
						}
					}
				}
			}
			
			// Kiiratás
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transf = transformerFactory.newTransformer();
			transf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transf.setOutputProperty(OutputKeys.INDENT, "yes");
			transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amunt", "2");
			
			DOMSource source = new DOMSource(doc);
			
			StreamResult console = new StreamResult(System.out);
			StreamResult file = new StreamResult("ModifiedXMLHLO5ZK.xml");
			
			transf.transform(source, console);
			transf.transform(source, file);
		} 
		// Hibakezeles
		catch (ParserConfigurationException | SAXException | IOException | TransformerException  e) {
			System.out.println("An error occured!\nError Message:\n" + e.getMessage());
			e.printStackTrace();
		}
	}
}