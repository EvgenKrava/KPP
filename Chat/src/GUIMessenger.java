import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Proxy;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GUIMessenger {
    private Messanger messanger;
    private JFrame frame;
    private JTextField textFieldMsg, addressTextField, portTextField, nameTextField;
    private JTextArea textArea;
    private JPanel header, body, footer, left;
    private JButton send, connect, disconnect, clear, exit;

    public GUIMessenger() {
        frame = new JFrame("Messenger");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        header = new JPanel(new FlowLayout());
        textFieldMsg = new JTextField(30);
        header.add(textFieldMsg);
        send = new JButton("Отправить");
        send.addActionListener((e) -> {
            messanger.send();
        });
        send.setEnabled(false);
        header.add(send);

        body = new JPanel(new GridLayout(1, 2, 10, 10));
        left = new JPanel(new GridLayout(3, 2, 40, 40));
        left.add(new Label("Адрес"));
        addressTextField = new JTextField("224.0.0.1",6);
        left.add(addressTextField);
        left.add(new Label("Порт"));
        portTextField = new JTextField("8080",6);
        left.add(portTextField);
        left.add(new Label("Имя"));
        nameTextField = new JTextField("Evgen",6);
        left.add(nameTextField);
        left.setBounds(20, 20, 20, 20);
        body.add(left);
        textArea = new JTextArea();
        body.add(new JScrollPane(textArea));

        footer = new JPanel();
        connect = new JButton("Соеденить");
        connect.addActionListener((e) -> {
            UITasks ui = (UITasks) Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[]{UITasks.class},
                    new EDTInvocationHandler(new UITasksImpl()));
            try {
                messanger = new MessangerImpl(InetAddress.getByName(addressTextField.getText()), Integer.valueOf(portTextField.getText()), nameTextField.getText(), ui);
                messanger.start();
                connect.setEnabled(false);
            } catch (UnknownHostException ex) {
                ex.printStackTrace();
                textArea.setText(textArea.getText() + "\nНеверный адрес");
            }
            send.setEnabled(true);
            disconnect.setEnabled(true);
        });
        footer.add(connect);

        disconnect = new JButton("Разъеденить");
        disconnect.addActionListener((e -> {
            messanger.stop();
            disconnect.setEnabled(false);
            send.setEnabled(false);
            connect.setEnabled(true);
        }));
        disconnect.setEnabled(false);
        footer.add(disconnect);

        clear = new JButton("Очистить");
        clear.addActionListener((e)->{
            textArea.setText("");
        });
        footer.add(clear);

        exit = new JButton("Выход");
        exit.addActionListener((e)->{
            System.exit(0);
        });
        footer.add(exit);

        frame.add(footer, BorderLayout.SOUTH);
        frame.add(body);
        frame.add(header, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private class UITasksImpl implements UITasks {
        @Override
        public String getMessage() {
            String res = textFieldMsg.getText();
            textFieldMsg.setText("");
            return res;
        }

        @Override
        public void setText(String txt) {
            textArea.append(txt + "\n");
        }
    }

}
