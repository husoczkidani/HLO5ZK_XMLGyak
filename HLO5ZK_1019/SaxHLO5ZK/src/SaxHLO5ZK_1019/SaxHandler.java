package SaxHLO5ZK_1019;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class SaxHandler extends DefaultHandler {
    private int indent = 0;
    private String formatAttributes(Attributes attributes){
        int attrLength = attributes.getLength();
        if(attrLength == 0){
            return "";
        }
        StringBuilder sb = new StringBuilder(",{");
        for(int i = 0;i< attrLength; i++){
            sb.append(attributes.getLocalName(i) + ":" + attributes.getValue(i));
            if ( i<attrLength - 1){
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    private void indent() {
        for(int i = 0; i < indent ;i++){
                System.out.print(" ");
        }
    }
    
    @Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) {
		indent++;
		indent();
		System.out.println(qName + formatAttributes(attributes) + " start");
	}
	
	@Override
	public void endElement (String uri, String localName, String qName) {
		indent();
		indent--;
		System.out.println(qName + " end");
	}
	
	@Override
	public void characters(char ch[], int start, int length) {
		String chars = new String(ch, start, length);
		if(!chars.isBlank()) {
			indent++;
			indent();
			indent--;
			indent();
			System.out.println(chars);
		}
	}
	
	public void warning(SAXParseException spe) throws SAXException {
	    System.out.println("\nFigyelmeztetés:\n" + spe.getMessage());
	}
	        
	public void error(SAXParseException spe) throws SAXException {
	    String message = "\nHiba:\n" + spe.getMessage();
	    throw new SAXException(message);
	}

	public void fatalError(SAXParseException spe) throws SAXException {
	    String message = "\nFatális hiba:\n" + spe.getMessage();
	    throw new SAXException(message);
	}
}
