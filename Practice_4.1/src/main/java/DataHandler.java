import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class DataHandler extends DefaultHandler {

    private boolean isX, isY;
    private double sumX, sumY, sumX2, sumXY, t;
    private double k, b;
    private int num;
    private ArrayList<Point> points = new ArrayList<Point>();

    public ArrayList<Point> getPoints() {
        return points;
    }

    public double getK() {
        return k;
    }

    public double getB() {
        return b;
    }

    @Override
    public void startDocument()
            throws SAXException {
        System.out.println("Start Document Parsing Process ...");
        sumX = 0; sumY = 0; sumX2 = 0; sumXY = 0; t = 0; num = 0;
    }

    @Override
    public void endDocument()
    throws SAXException {
        System.out.println("End Document Parsing Process ...");
        num /= 2;
        k = (sumXY - sumX * sumY / num) / (sumX2 - sumX * sumX / num);
        b = sumY / num - k * sumX / num;
        System.out.println("k: " + k + "\t" + "b: " + b);
    }

    @Override
    public void startElement(String uri, String name, String qName, Attributes attrs)
            throws SAXException {
        System.out.println("Начало обработки элемента: " + qName);
        if (qName.equals("x")) {
            isX = true;
            points.add(new Point());
        } else if (qName.equals("y")) {
            isY = true;
        }
        if (attrs.getLength() > 0) {
            for (int i = 0; i < attrs.getLength(); i++)
                System.out.println("\t" + attrs.getLocalName(i) + " : " +
                        attrs.getValue(i));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        System.out.println("Конец обработки элемента: " + qName);

        if (qName.equals("x")) {
            isX = false;
            num += 1;
        } else if (qName.equals("y")) {
            isY = false;
            t = 0;
            num += 1;
        }
    }
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String str = new String(ch, start, length).trim();
        if (str.trim().length() > 0)
            System.out.println("\tЗначение: " + str);
        double tmp = 0;
        if (isX) {
            tmp = Double.parseDouble(str);
            points.get(points.size()-1).setX(tmp);
            sumX += tmp;
            sumX2 += tmp * tmp;
            t = tmp;

        } else if (isY) {
            tmp = Double.parseDouble(str);
            points.get(points.size()-1).setY(tmp);
            sumY += tmp;
            t = t * tmp;
            sumXY += t;
        }
    }

    public void warning(SAXParseException ex) {
        System.err.println("Warning: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = "
                + ex.getColumnNumber());
    }

    public void error(SAXParseException ex) {
        System.err.println("Error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = "
                + ex.getColumnNumber());
    }

    public void fatalError(SAXParseException ex) {
        System.err.println("Fatal error: " + ex);
        System.err.println("line = " + ex.getLineNumber() + " col = "
                + ex.getColumnNumber());
    }
}
