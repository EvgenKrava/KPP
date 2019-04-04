import org.w3c.dom.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class DOMModifcator {

    private Document document;

    DOMModifcator(Document document){
        this.document=document;
    }

    public void print(){
        print(document);
    }

    private static void print(Node node) {
        int type = node.getNodeType();
        switch (type) {
            // print the document element
            case Node.DOCUMENT_NODE: {
                System.out.println("<?xml version=\"1.0\" ? >");
                print(((Document) node).getDocumentElement());
                break;
            }

            case Node.ELEMENT_NODE: {
                System.out.print("<");
                System.out.print(node.getNodeName());
                NamedNodeMap attrs = node.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++)
                    print(attrs.item(i));
                System.out.print(">");
                if (node.hasChildNodes()) {
                    NodeList children = node.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++)
                        print(children.item(i));
                }
                System.out.print("</");
                System.out.print(node.getNodeName());
                System.out.print(">");
                break;
            }

            case Node.ATTRIBUTE_NODE: {
                System.out.print(" " + node.getNodeName() + "=\"" +
                        ((Attr) node).getValue() + "\"");
                break;
            }
            case Node.TEXT_NODE: {
                System.out.print(node.getNodeValue());
                break;
            }
        }
    }

    public void addElement(String nodeName, String elementName) throws TransformerException {
        NodeList elements = document.getElementsByTagName(nodeName);
        Element element1 = document.createElement(elementName);
        for (int i = 0; i < elements.getLength(); i++) {
            elements.item(i).appendChild(element1);
        }
    }

    public void removeElement(String elementName){
        NodeList elemnts = document.getElementsByTagName(elementName);
        for (int i = 0; i < elemnts.getLength(); i++) {
            elemnts.item(i).getParentNode().removeChild(elemnts.item(i));
        }
    }

    public int getChildsCoin(String nodeName, int index){
        return document.getElementsByTagName(nodeName).item(index).getChildNodes().getLength();
    }

    public void modeElement(String elementName, int index, String newValue){
        NodeList elements = document.getElementsByTagName(elementName);
        elements.item(index).setTextContent(newValue);
    }

    public void save(String fileName) throws TransformerException {
        File file = new File("src/main/resources/",fileName);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        //transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }
}
