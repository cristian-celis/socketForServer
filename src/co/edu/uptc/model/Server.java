package co.edu.uptc.model;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;

public class Server {
    private Connection conection;
    DataOutputStream dataOutputStream;
    DataInputStream dataInputStream;
    private ManagerModel managerModel;
    private int count = 0;
    String host;
    int port;

    public Server(String host, int port, ManagerModel managerModel) {
        this.host = host;
        this.port = port;
        this.managerModel = managerModel;
    }

    public void innit(String host, int port) {
        conection = new Connection();
        conection.setType("server");
        conection.setPort(port);
        conection.setHost(host);
        conection.connect();
    }

    public void send() {
        innit(host, port);
        try {
            Thread thread = new Thread() {
                @Override
                public void run() {
                    try {
                        conection.socket = conection.serverSocket.accept();
                        dataOutputStream = new DataOutputStream(conection.socket.getOutputStream());
                        while (true) {
                            System.out.println("Envia -> " + infoToSend(count));
                            dataOutputStream.writeUTF(infoToSend(count));
                            dataOutputStream.flush();
                            count++;
                            sleep(1000);
                        }
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            if (dataOutputStream != null) {
                                dataOutputStream.close();
                            }
                            if (conection.socket != null) {
                                conection.socket.close();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            };
            thread.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String infoToSend(int position){
        int[] points = managerModel.getPoints(position);
        String toReturn = points[0] == -1? points[1] + "," + points[2] : points[0] + "," + points[1];
        paintRec(toReturn);
        return toReturn;
    }

    public void paintRec(String points){
        String[] coordenates = points.split(",");
        Rectangle rectangle = new Rectangle(Integer.parseInt(coordenates[0]), Integer.parseInt(coordenates[1]), 25,25);
        managerModel.getPresenter().drawNewRec(rectangle);
    }
}