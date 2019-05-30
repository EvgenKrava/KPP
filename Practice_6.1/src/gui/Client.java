package gui;

import card.MetroCard;
import card.User;
import card.operations.AddMetroCardOperation;
import card.operations.AddMoneyOperation;
import card.operations.GetMoneyOperation;
import card.operations.ShowBalanceMetroCardOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Client {
    MetroCard metroCard;

    private client.Client client;

    private String ipAddress;

    private int port;

    private String serialNumber;

    private JFrame frame;
    private JPanel header, footer;

    private JTextField ipTextField;
    private JTextField portTextField;
    private JTextField numberTextField;

    private JButton connect, addCard, addMoney, getMoney, removeCard, showBalance,clear, exit;

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


        connect = new JButton("Connect");
        connect.addActionListener((e)->{
            this.port = Integer.valueOf(portTextField.getText());
            this.ipAddress = ipTextField.getText();
            if(client==null)
                client = new client.Client(ipAddress, port);
        });
        footer.add(connect);

        addMoney = new JButton("Add money");
        addMoney.addActionListener((e)->{
            addMoney();
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
                serialNumber = numberTextField.getText();
                addCard();
                if(metroCard!=null) {
                    client.request(new AddMetroCardOperation(metroCard));
                }
            }
        });

        showBalance = new JButton("Show Balance");
        showBalance.addActionListener((e)->{
            this.showBalance();
        });

        getMoney = new JButton("Get Money");
        getMoney.addActionListener((e)->{
            getMoney();
        });


        footer.add(addCard);
        footer.add(showBalance);
        footer.add(addMoney);
        footer.add(getMoney);
        footer.add(clear);
        footer.add(exit);


        frame.add(header, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }



    private void addCard(){
        frame.setEnabled(false);
        JFrame frame = new JFrame("Adding Card");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300,300);
        JPanel header = new JPanel(new GridLayout(5,2,5,5));

        header.add(new Label("Name"));
        JTextField name = new JTextField(10);
        header.add(name);

        header.add(new Label("Surname"));
        JTextField surname = new JTextField(10);
        header.add(surname);

        header.add(new Label("Sex"));
        JTextField sex = new JTextField(10);
        header.add(sex);

        header.add(new Label("Birthday"));
        JTextField birthday = new JTextField(10);
        header.add(birthday);

        header.add(new Label("College"));
        JTextField college = new JTextField(10);
        header.add(college);
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((e -> {
            metroCard = null;
            frame.dispose();
            this.frame.setEnabled(true);
        }));
        JButton ok = new JButton("ok");
        ok.addActionListener((e -> {
            metroCard = createMetroCard(serialNumber, name.getText(), surname.getText(), sex.getText(), birthday.getText(), college.getText());
            client.request(new AddMetroCardOperation(metroCard));
            frame.dispose();
            this.frame.setEnabled(true);

        }));


        footer.add(ok);
        footer.add(cancel);
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void showBalance(){
            client.request(new ShowBalanceMetroCardOperation(serialNumber));
    }

    private void addMoney(){
        frame.setEnabled(false);
        JFrame frame = new JFrame("Adding Money");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300,150);
        JPanel header = new JPanel(new GridLayout(1,2,5,5));

        header.add(new Label("Money"));
        JTextField money = new JTextField(10);
        header.add(money);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((e -> {
            frame.dispose();
            this.frame.setEnabled(true);
        }));

        JButton ok = new JButton("ok");
        ok.addActionListener((e -> {
            this.client.request(new AddMoneyOperation(serialNumber, Double.valueOf(money.getText())));
            frame.dispose();
            this.frame.setEnabled(true);

        }));


        footer.add(ok);
        footer.add(cancel);
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void getMoney(){
        frame.setEnabled(false);
        JFrame frame = new JFrame("Getting Money");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(300,150);
        JPanel header = new JPanel(new GridLayout(1,2,5,5));

        header.add(new Label("Money"));
        JTextField money = new JTextField(10);
        header.add(money);

        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton cancel = new JButton("Cancel");
        cancel.addActionListener((e -> {
            frame.dispose();
            this.frame.setEnabled(true);
        }));

        JButton ok = new JButton("ok");
        ok.addActionListener((e -> {
            this.client.request(new GetMoneyOperation(serialNumber, Double.valueOf(money.getText())));
            frame.dispose();
            this.frame.setEnabled(true);

        }));


        footer.add(ok);
        footer.add(cancel);
        frame.add(header, BorderLayout.NORTH);
        frame.add(footer, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private MetroCard createMetroCard(String serialNumber, String name, String surname, String sex, String birthday, String college){
        return new MetroCard(serialNumber,new User(name, surname, sex, birthday), college);
    }

    public static void main(String[] args) {
        new Client();
    }
}
