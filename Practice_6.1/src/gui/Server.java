package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Server {
    private int port;
    private JFrame frame;
    private JPanel header, footer;
    private JTextField textField;
    private JTextArea textArea;
    private JButton start, stop, exit;
    private server.Server server;

    Server(){
        frame = new JFrame("Server");
        frame.setSize(900,600);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        header = new JPanel();
        header.setLayout(new FlowLayout());
        header.add(new Label("Working port"));
        textField = new JTextField(10);
        header.add(textField);
        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(""+(char)(b & 0xFF));
            }
        });
        System.setOut(out);

        textArea = new JTextArea(20,20);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        footer = new JPanel();
        footer.setLayout(new FlowLayout());
        start = new JButton("Start Server");
        start.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startServer();
            }
        });
        footer.add(start);

        stop = new JButton("Stop Server");
        stop.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    server.finish();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        footer.add(stop);

        exit = new JButton("Exit Server");
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void startServer(){
        if(textField.getText().equals("")){
            System.out.println("Type port value!!!");
            return;
        }
        port = Integer.valueOf(textField.getText());
        server = new server.Server(port);
        server.start();
    }

    private void stopServer() throws IOException {
        server.finish();
    }

    private void addMoney(){

    }

    public static void main(String[] args) {
        new Server();
    }
}
