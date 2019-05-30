package server;

import interfaces.Conferee;
import interfaces.Registruble;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class ServerGui extends UnicastRemoteObject implements Registruble {
    ArrayList<Conferee> conferees;
    private Registry registry;
    private Registruble registruble;
    private JFrame frame;
    private JTextField host, port, participants;
    private JTextArea textArea;
    private JPanel header, footer;
    private JButton start, stop, save, load, exit;

    ServerGui() throws RemoteException, UnknownHostException {
        super();
        conferees = new ArrayList<>();
        frame = new JFrame("Conference Server");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(440, 220);

        header = new JPanel();
        header.add(new Label("host:"));
        host = new JTextField(10);
        host.setText(InetAddress.getLocalHost().getHostAddress());
        header.add(host);
        header.add(new Label("port:"));
        port = new JTextField(5);
        header.add(port);
        port.setText("1049");
        header.add(new Label("participants:"));
        participants = new JTextField(4);
        header.add(participants);
        participants.setEnabled(false);

        footer = new JPanel();
        start = new JButton("Start");
        start.addActionListener((e) -> {
            save.setEnabled(true);
            stop.setEnabled(true);
            start.setEnabled(false);
            String localhost = "127.0.0.1";
            String RMI_HOSTNAME = "java.rmi.server.hostname";
            String serviceName = "reg";
            System.setProperty(RMI_HOSTNAME, localhost);
            try {
                if (registry == null)
                    registry = LocateRegistry.createRegistry(Integer.parseInt(port.getText()));
                registruble = this;
                registry.rebind("reg", registruble);
            } catch (RemoteException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        footer.add(start);
        stop = new JButton("Stop");
        stop.addActionListener((e) -> {
            try {
                start.setEnabled(true);
                stop.setEnabled(false);
                save.setEnabled(false);
                registry.unbind("reg");
            } catch (RemoteException | NotBoundException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        stop.setEnabled(false);
        footer.add(stop);

        save = new JButton("Save");
        save.addActionListener((e) -> {
            try {
                JFileChooser fileChooser = new JFileChooser(new File(".").getCanonicalPath());
                fileChooser.showDialog(frame, "Save");
                File file = fileChooser.getSelectedFile();
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
                Result output = new StreamResult(file);
                Source input = new DOMSource(ArrayListToDocument(conferees));
                transformer.transform(input, output);
            } catch (ParserConfigurationException | IOException | TransformerException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        save.setEnabled(false);
        footer.add(save);

        load = new JButton("Load");
        load.addActionListener((e) -> {
            try {
                JFileChooser fileChooser = new JFileChooser(new File(".").getCanonicalPath());
                fileChooser.showDialog(frame, "Load");
                File file = fileChooser.getSelectedFile();
                conferees = DocToArray(DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file));
            } catch (SAXException | IOException | ParserConfigurationException e1) {
                JOptionPane.showMessageDialog(frame, e1, "Error", JOptionPane.ERROR_MESSAGE);
            }
            print();
        });
        footer.add(load);
        exit = new JButton("Exit");
        exit.addActionListener((e) -> {
            System.exit(0);
        });
        footer.add(exit);

        textArea = new JTextArea();
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
        frame.add(new JScrollPane(textArea));
        frame.setVisible(true);
    }

    public static void main(String[] args) throws RemoteException, UnknownHostException {
        new ServerGui();
    }

    public static ArrayList<Conferee> DocToArray(Document document) {
        ArrayList<Conferee> conferees = new ArrayList<>();
        Element RegisteredConferees = (Element) document.getFirstChild();
        NodeList names = RegisteredConferees.getElementsByTagName("name");
        NodeList surnames = RegisteredConferees.getElementsByTagName("surname");
        NodeList organisations = RegisteredConferees.getElementsByTagName("organization");
        NodeList reports = RegisteredConferees.getElementsByTagName("report");
        NodeList emails = RegisteredConferees.getElementsByTagName("email");
        for (int i = 0; i < names.getLength(); i++) {
            conferees.add(new Conferee(
                    names.item(i).getTextContent(),
                    surnames.item(i).getTextContent(),
                    organisations.item(i).getTextContent(),
                    reports.item(i).getTextContent(),
                    emails.item(i).getTextContent()
            ));
        }

        return conferees;
    }

    public static Document ArrayListToDocument(ArrayList<Conferee> conferees) throws ParserConfigurationException {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element root = document.createElement("RegisteredConferees");
        for (Conferee c : conferees) {
            Element conferee = document.createElement("Conferee");
            Element name = document.createElement("name");
            name.setTextContent(c.getName());
            Element surname = document.createElement("surname");
            surname.setTextContent(c.getSurname());
            Element organization = document.createElement("organization");
            organization.setTextContent(c.getOrganization());
            Element report = document.createElement("report");
            report.setTextContent(c.getReport());
            Element email = document.createElement("email");
            email.setTextContent(c.getEmail());
            conferee.appendChild(name);
            conferee.appendChild(surname);
            conferee.appendChild(organization);
            conferee.appendChild(report);
            conferee.appendChild(email);
            root.appendChild(conferee);
        }
        document.appendChild(root);
        document.normalizeDocument();
        return document;
    }

    public static Document DocumentFromXMLFile(File file) throws ParserConfigurationException, IOException, SAXException {
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
    }

    public void print() {
        participants.setText("" + conferees.size());
        textArea.setText("Register Conferees");
        int i = 1;
        for (Conferee c : conferees) {
            textArea.setText(textArea.getText() + "\n" + i + ")" + c.toString());
            i++;
        }
    }

    @Override
    public int registration(Conferee conferee) throws RemoteException {
        conferees.add(conferee);
        print();
        return conferees.size();
    }

    @Override
    public String getInfo() throws RemoteException{
        try {
            Document document  = ArrayListToDocument(conferees);
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            return writer.toString();
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
        return conferees.toString();
    }
}
