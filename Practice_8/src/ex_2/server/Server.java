package ex_2.server;

import ex_2.remote.Conferee;
import ex_2.remote.ConfereeAdded;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    JFrame frame;

    private JPanel header;
    private JTextField host;
    private JTextField port;
    private JTextField participants;

    private JTextArea textArea;

    private JPanel footer;
    private JButton start;
    private JButton stop;
    private JButton save;
    private JButton load;
    private JButton exit;




    Server() throws RemoteException, NamingException {
        frame = new JFrame("RMI Server");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        header = new JPanel();
        header.add(new JLabel("host: "));
        host = new JTextField(10);
        host.setText("rmi://localhost/");
        header.add(host);
        header.add(new JLabel("port: "));
        port = new JTextField(10);
        header.add(port);
        header.add(new JLabel("participants"));
        participants = new JTextField(10);
        header.add(participants);

        textArea = new JTextArea();
        frame.add(new JScrollPane(textArea));


        footer = new JPanel();
        start = new JButton("start");
        start.addActionListener((e)->{
            stop.setEnabled(true);
            load.setEnabled(true);
            start.setEnabled(false);
            try {
                ConfereeAdded confereeAdded=new RegisterConferees();
                Naming.rebind("rmi://localhost:1099/add", confereeAdded);

            } catch (RemoteException | MalformedURLException ex) {
                ex.printStackTrace();
            }
        });
        footer.add(start);
        stop = new JButton("stop");
        stop.setEnabled(false);
        stop.addActionListener((e)->{
            //                context.unbind(port.getText());
        });
        footer.add(stop);

        save = new JButton("save");
        save.addActionListener((e)->{

        });
        footer.add(save);
        load = new JButton("load");
        load.setEnabled(false);
        load.addActionListener((e)->{

        });
        footer.add(load);
        exit = new JButton("exit");
        exit.addActionListener((e)->{
            //                context.close();
            System.exit(0);
        });
        footer.add(exit);
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws NamingException, RemoteException {
        new Server();
    }
}
