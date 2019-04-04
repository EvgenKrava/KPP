import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MyParser{

    public static void createXML() throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();

        Element datasheet = document.createElement("datasheet");
//        Comment comment = document.createComment("Программа просто секс!");
//        datasheet.appendChild(comment);
        for (int i = 0; i < 9; i++) {
            Element data = document.createElement("data");
            data.setAttribute("date","19.0"+(i+1)+".2000");
            Element x =document.createElement("x");
            Element y =document.createElement("y");
            x.setTextContent(""+(double)i);
            y.setTextContent(""+Math.pow(i,2));
            data.appendChild(x);
            data.appendChild(y);
            datasheet.appendChild(data);
        }

        document.appendChild(datasheet);

        File file = new File("src/main/resources/","newData.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), new StreamResult(file));
    }

    public static void buildChart(double k, double b){
        ArrayList<Double> xData = new ArrayList<Double>();
        ArrayList<Double> yData = new ArrayList<Double>();
        for (double i = -50; i < 50; i+=0.5) {
            xData.add(i);
            yData.add(i*k+b);
        }
        XYChart chart = QuickChart.getChart("График kx+b", "X", "Y", "f(x)", xData, yData);
        new SwingWrapper(chart).displayChart();
    }

    public static void printDomTree(Node node) {
        int type = node.getNodeType();
        switch (type) {
            // print the document element
            case Node.DOCUMENT_NODE: {
                System.out.println("<?xml version=\"1.0\" ? >");
                printDomTree(((Document) node).getDocumentElement());
                break;
            }

            case Node.ELEMENT_NODE: {
                System.out.print("<");
                System.out.print(node.getNodeName());
                NamedNodeMap attrs = node.getAttributes();
                for (int i = 0; i < attrs.getLength(); i++)
                    printDomTree(attrs.item(i));
                System.out.print(">");
                if (node.hasChildNodes()) {
                    NodeList children = node.getChildNodes();
                    for (int i = 0; i < children.getLength(); i++)
                        printDomTree(children.item(i));
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

    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException, TransformerException {

        File xmlFile = new File("src/main/resources/data.xml");

        InputStream xmlInput = new FileInputStream(xmlFile);

//        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
//        String language = XMLConstants.XML_DTD_NS_URI;

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        Schema xsd = schemaFactory.newSchema(new File("src/main/resources/data.xsd"));

//        SchemaFactory dtdFactory = SchemaFactory.newInstance(XMLConstants.XML_DTD_NS_URI);

//        Schema dtd = dtdFactory.newSchema(new File("src/main/resources/data.dtd"));

        /////////////////////////////////////////SAX///////////////////////////////////////////////////////////////

        System.out.println("*******************SAX*******************");

        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setValidating(true);

        factory.setSchema(xsd);

        factory.setNamespaceAware(true);

        SAXParser saxParser = factory.newSAXParser();

        DataHandler handler = new DataHandler();

        saxParser.parse(xmlFile, handler);

        System.out.println("Points from ArrayList  "+handler.getPoints());

        //////////////////////////////////////////////DOM///////////////////////////////////////////////////////////////

        System.out.println("*******************DOM*******************");

        dom.Counter domCounter = new dom.Counter();

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        documentBuilderFactory.setSchema(xsd);

        documentBuilderFactory.setValidating(true);

        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

        Document document = documentBuilder.parse(xmlFile);

        document.getDocumentElement().normalize();

        domCounter.count(document);

        printDomTree(document);

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        buildChart(((DataHandler) handler).getK(), ((DataHandler) handler).getB());

        createXML();
    }

}
