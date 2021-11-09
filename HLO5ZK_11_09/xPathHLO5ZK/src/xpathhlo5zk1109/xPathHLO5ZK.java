package xpathhlo5zk1109;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xPathHLO5ZK {

	public static void main(String[] args) {
		try {
			
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			
			Document doc = docBuilder.parse("studentHLO5ZK.xml");
			
			doc.getDocumentElement().normalize();
			
			XPath xPath = XPathFactory.newInstance().newXPath();
			
			String expression1 = "class";
			String expression2 = "class/student";
			String expression3 = "class/student[@id='01']";
			String expression4 = "class//student";
			String expression5 = "class/student[1]";
			String expression6 = "class/student[last()]";
			String expression7 = "class/student[last()-1]";
			String expression8 = "class/student[position()<3]";
			String expression9 = "class/*";
			String expression10 = "class/student[@*]";
			String expression11 = "//*";
			String expression12 = "class/student[kor>20]";
			String expression13 = "class/student/keresztnev | //vezeteknev";
			
			NodeList nodeList = (NodeList)xPath.compile(expression13).evaluate(doc,XPathConstants.NODESET);
			
			for (int i = 0; i<nodeList.getLength();i++)
			{
				Node node = nodeList.item(i);
				
				System.out.println("\nAktualis elem: "+ node.getNodeName());
				
				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("student")) {
					Element element = (Element) node;
					
					System.out.println("Hallgato ID: " + element.getAttribute("id"));
					
					System.out.println("Keresznev: " + element.getElementsByTagName("keresztnev").item(0).getTextContent());
					System.out.println("Vezeteknev: " + element.getElementsByTagName("vezeteknev").item(0).getTextContent());
					System.out.println("Becenev: " + element.getElementsByTagName("becenev").item(0).getTextContent());
					System.out.println("Kor: " + element.getElementsByTagName("kor").item(0).getTextContent());
					
				
				}
				
				
			}
		}catch(Exception e) {
			System.out.print(e);
		}
	}

}
