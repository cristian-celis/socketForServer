package co.edu.uptc.model;

import java.awt.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {
    private Connection connection;
    DataOutputStream dataOutputStream;
    private final ManagerModel managerModel;
    String host;
    int port;
    private Rectangle currentRectangle;
    boolean changes;

    public Server(String host, int port, ManagerModel managerModel) {
        changes = false;
        currentRectangle = new Rectangle(0, 1, 25, 25);
        this.host = host;
        this.port = port;
        this.managerModel = managerModel;
    }

    public void innit(String host, int port) {
        connection = new Connection();
        connection.setType("server");
        connection.setPort(port);
        connection.setHost(host);
        connection.connect();
    }

    public void send() {
        innit(host, port);
        try {
            Thread thread = new Thread(() -> {
                try {
                    connection.socket = connection.serverSocket.accept();
                    dataOutputStream = new DataOutputStream(connection.socket.getOutputStream());
                    while (true) {
                        while(!changes)
                            Thread.sleep(10);
                        dataOutputStream.writeUTF(infoToSend());
                    }
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    try {
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (connection.socket != null) {
                            connection.socket.close();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
            thread.start();
            thread.setPriority(Thread.MAX_PRIORITY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String infoToSend() {
        int globalRecX = managerModel.getRectangle().x;
        int globalRecY = managerModel.getRectangle().y;
        currentRectangle.setLocation(globalRecX, globalRecY);
        changes = false;
        return currentRectangle.x + "," + currentRectangle.y;
    }
}