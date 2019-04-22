package gui;

import card.operations.AddMetroCardOperation;
import other.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Client {
    private client.Client client;

    private String ipAddress;

    private int port;

    private int number;

    private JFrame frame;
    private JPanel header, footer;

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField numberTextField;

    private JButton calculate, clear, exit, addCard;

    private JTextArea textArea;


    public Client(){
        frame = new JFrame("Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(900,600);
        frame.setLayout(new BorderLayout());

        header = new JPanel();
        header.setLayout(new FlowLayout());
        header.add(new Label("IP Address"));
        ipTextField = new JTextField(10);
        header.add(ipTextField);
        header.add(new Label("Port"));
        portTextField = new JTextField(10);
        header.add(portTextField);
        header.add(new Label("№"));
        numberTextField = new JTextField(10);
        header.add(numberTextField);


        textArea = new JTextArea();
        PrintStream out = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                textArea.append(""+(char)(b & 0xFF));
            }
        });
        System.setOut(out);

        footer = new JPanel();
        footer.setLayout(new FlowLayout());
        calculate = new JButton("Calculate");
        calculate.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate();
            }
        });

        clear = new JButton("Clear");
        clear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.setText("");
            }
        });

        exit = new JButton("Exit");
        exit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        addCard = new JButton("Add Card");
        addCard.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCard();
            }
        });


//        footer.add(addCard);
        footer.add(calculate);
        footer.add(clear);
        footer.add(exit);


        frame.add(header, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void calculate(){
        ipAddress = ipTextField.getText();
        port = Integer.valueOf(portTextField.getText());
        client =  new client.Client(ipAddress, port);
        AddMetroCardOperation op = new AddMetroCardOperation();
        op.getCard().setUser(new User("Petr", "Petrov", "M", "25.12.1968"));
        op.getCard().setSerialNumber("00001");
        op.getCard().setCollege("KhNU");
        op.getCard().setBalance(25);
        client.applyOperation(op);
        client.finish();
    }

    private void addCard(){
        JFrame frame = new JFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(200,100);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Client();
    }
}
