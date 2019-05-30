package client;

import interfaces.Conferee;
import interfaces.Registruble;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientGui {
    Registry registry;
    JFrame frame;
    JPanel header, body, footer;
    JTextField host, port, participants, name, surname, organization, report, email;
    JButton register, clear, getInfo, finish;
    Registruble registruble;


    ClientGui() throws RemoteException, NotBoundException, MalformedURLException {
        frame = new JFrame("Conference Client");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(440, 220);

        header = new JPanel();
        header.add(new Label("host: "));
        host = new JTextField(10);
        host.setText("localhost");
        header.add(host);
        header.add(new Label("port: "));
        port = new JTextField(5);
        port.setText("1049");
        header.add(port);
        header.add(new Label("participants: "));
        participants = new JTextField(4);
        participants.setEnabled(false);
        header.add(participants);

        body = new JPanel(new GridLayout(5, 2));
        body.add(new Label("Name:"));
        name = new JTextField(15);
        body.add(name);
        body.add(new Label("Surname:"));
        surname = new JTextField(15);
        body.add(surname);
        body.add(new Label("Organization:"));
        organization = new JTextField(15);
        body.add(organization);
        body.add(new Label("Report:"));
        report = new JTextField(15);
        body.add(report);
        body.add(new Label("Email:"));
        email = new JTextField(15);
        body.add(email);

        footer = new JPanel();
        register = new JButton("Register");
        register.addActionListener((e) -> {
            String localhost = "127.0.0.1";
            String RMI_HOSTNAME = "java.rmi.server.hostname";
            String SERVICE_PATH = "reg";
            System.setProperty(RMI_HOSTNAME, localhost);
            Conferee conferee = new Conferee(
                    name.getText(),
                    surname.getText(),
                    organization.getText(),
                    report.getText(),
                    email.getText()
            );
            try {
                registry = LocateRegistry.getRegistry(host.getText(), Integer.parseInt(port.getText()));
                registruble = (Registruble) registry.lookup(SERVICE_PATH);
                participants.setText("" + registruble.registration(conferee));
            } catch (RemoteException | NotBoundException e1) {
                e1.printStackTrace();
            }

            clear();
        });
        footer.add(register);
        clear = new JButton("Clear");
        clear.addActionListener((e) -> {
            clear();
        });
        footer.add(clear);
        getInfo = new JButton("Get Info");
        getInfo.addActionListener((e) -> {
            try {
                Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
                String result = registruble.getInfo();
                JFileChooser fileChooser = new JFileChooser(new File(".").getCanonicalPath());
                fileChooser.showDialog(frame, "Save");
                File file = fileChooser.getSelectedFile();
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(result);
                fileWriter.flush();

            } catch (IOException | ParserConfigurationException e1) {
                e1.printStackTrace();
            }
        });
        footer.add(getInfo);
        finish = new JButton("Finish");
        finish.addActionListener((e) -> {
            System.exit(0);
        });
        footer.add(finish);

        frame.add(body);
        frame.add(footer, BorderLayout.SOUTH);
        frame.add(header, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        new ClientGui();
    }

    private static Document convertStringToXMLDocument(String xmlString) {
        //Parser that produces DOM object trees from XML content
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        //API to obtain DOM Document instance
        DocumentBuilder builder = null;
        try {
            //Create DocumentBuilder with default configuration
            builder = factory.newDocumentBuilder();

            //Parse the content to Document object
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void clear(){
        name.setText("");
        surname.setText("");
        organization.setText("");
        report.setText("");
        email.setText("");
    }
}
