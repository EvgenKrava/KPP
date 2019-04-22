package ex_1.udpWork;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    private ActiveUsers userList = null; // список зарегистрированных компьютеров
    private DatagramSocket socket = null; // датаграмный сокет для взаимодействия компьютеров по сети
    private DatagramPacket packet = null; // датаграмный пакет для поучения и отправки информации
    private InetAddress address = null;   // класс, представляющий сетевой адрес компьютера
    private int port = -1;

    public UDPServer(int serverPort) {
        try {
            socket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            e.getStackTrace();
        }
        userList = new ActiveUsers();
    }

    public void work(int bufferSize) {
        try {
            System.out.println("Server start...");
            while (true) {               // бесконечный цикл работы с клиентами
                getUserData(bufferSize); // получение запроса клиента
                log(address, port);      // вывод информации о клиенте на экран
                sendUserData();          // формирование и отправка ответа клиенту
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        } finally {
            System.out.println("Server end...");
            socket.close();
        }
    }

    private void log(InetAddress address, int port) {
        System.out.println("Request from: " + address.getHostAddress() +
                " port: " + port);
    }

    private void getUserData(int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        address = packet.getAddress();
        port = packet.getPort();
        User usr = new User(address, port);
        if (userList.isEmpty()) {
            userList.add(usr);
        } else if (!userList.contains(usr)) {
            userList.add(usr);
        }
        buffer = new byte[bufferSize];
    }

    private void sendUserData() throws IOException {
        byte[] buffer;
        for (int i = 0; i < userList.size(); i++) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(userList.get(i));
            buffer = bout.toByteArray();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
            buffer = "end".getBytes();
            packet = new DatagramPacket(buffer, 0, address, port);
            socket.send(packet);
        }
    }

    public static void main(String[] args) {
        (new UDPServer(1501)).work(256);
    }
}
