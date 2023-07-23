package co.edu.uptc.model;

import com.google.gson.Gson;

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
    private int amountSectionImg = 1;

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

    private String sendImage(int sectionImg){
        //byte[] subarreglo = Arrays.copyOfRange(arreglo1, posicionInicial, posicionFinal + 1);
        String info = new Gson().toJson(managerModel.getImage());
        String subcadena = info.substring((sectionImg * 65000), (info.length() < ((sectionImg+1)*65000)? info.length()-1: ((sectionImg+1) * 65000)) + 1);
        System.out.println("Info a enviar -> " + subcadena);
        if (sectionImg == 0){
            return subcadena.length() + "=" + subcadena;
        }
        return subcadena;
    }

    public void send() {
        //System.out.println("ToSend: " + sendImage());
        innit(host, port);
        try {
            Thread thread = new Thread(() -> {
                try {
                    int count = 0;
                    connection.socket = connection.serverSocket.accept();
                    dataOutputStream = new DataOutputStream(connection.socket.getOutputStream());
                    while (count < amountSectionImg) {
                        System.out.println("Envio");
                        /*while(!changes)
                            Thread.sleep(10);
                        ataOutputStream.writeUTF(sendRectangle());*/
                        dataOutputStream.writeUTF(sendImage(count));
                        count++;
                    }
                } catch (IOException e) {
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

    public String sendRectangle() {
        int globalRecX = managerModel.getRectangle().x;
        int globalRecY = managerModel.getRectangle().y;
        currentRectangle.setLocation(globalRecX, globalRecY);
        changes = false;
        return currentRectangle.x + "," + currentRectangle.y;
    }
}