package mybeans.myApplication;

import mybeans.table.Data;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.util.ArrayList;

public class Handler extends DefaultHandler {

    private boolean isX = false;
    private boolean isY = false;

    private ArrayList<Data> dataArrayList;

    public ArrayList<Data> getDataArrayList(){
        return dataArrayList;
    }

    public Handler(){
        dataArrayList = new ArrayList<Data>();
    }

    @Override
    public void startDocument() throws SAXException {
        isY=false;
        isX=false;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(qName.equals("data")){
            dataArrayList.add(new Data(attributes.getValue("date"),0,0));
        }
        if(qName.equals("x")){
            isX = true;
        }
        if (qName.equals("y")){
            isY = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String s = new String(ch, start, length);
        String[] st=s.split(" ");
        s=st[0];
        if(isX){
            dataArrayList.get(dataArrayList.size()-1).setX(Double.parseDouble(s));
        }
        if(isY){
            dataArrayList.get(dataArrayList.size()-1).setY(Double.parseDouble(s));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
       if (qName.equals("x")){
           isX = false;
       }
       if (qName.equals("y")){
           isY = false;
       }
    }

    @Override
    public void endDocument() throws SAXException {
    }
}
