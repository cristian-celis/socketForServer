package co.edu.uptc.model;

import co.edu.uptc.pojo.Data;
import co.edu.uptc.pojo.InformationForImage;
import co.edu.uptc.presenter.Contract;
import co.edu.uptc.presenter.Presenter;
import com.google.gson.Gson;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerModel implements Contract.Model{

    private String[] args;
    private Server server;
    private Contract.Presenter presenter;
    private RouteBuilder routeBuilder;
    private Rectangle rectangle;
    private Data data;
    private InformationForImage image;
    public ManagerModel(Contract.Presenter presenter, String[] args){
        this.presenter = presenter;
        rectangle = new Rectangle(0, 0, 25, 25);
        routeBuilder = new RouteBuilder();
        this.args = args;
        initConnection();
    }
    public Rectangle getRectangle(){
        return rectangle;
    }

    public void initConnection(){
        String type = args[0];
        if (type.equalsIgnoreCase("server")) {
            server = new Server(args[1], Integer.parseInt(args[2]), this);
        }else
            JOptionPane.showMessageDialog(null, "No esta definido como servidor");
    }

    /*
    para recibir
    String json = "";
    Gson gson = new Gson();
    Objeto o = gson.fromJson(json, Objeto.class);
     */

    public void prueba(){
        Gson gson = new Gson();
        //String info = new Gson().toJson(data());
    }

    @Override
    public void imgPath(String ruta){
        try {
            System.out.println("Llego la ruta: " + ruta);
            File file = new File(ruta);
            BufferedImage image = ImageIO.read(file);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(image, "png", baos);
            byte[] bytes = baos.toByteArray();
            //65,535 -> 65,000
            this.image = new InformationForImage(bytes);
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public InformationForImage getImage(){
        return image;
    }

    @Override
    public void setByPoint(int coorX, int coorY){
        server.changes = true;
        this.rectangle.setLocation(coorX, coorY);
    }

    @Override
    public void buildCoordinatesPath(){
        //routeBuilder.fillRouteGaps();
    }
    public int[] getPoints(int position){
        return routeBuilder.getPoint(position);
    }

    public Contract.Presenter getPresenter(){
        return presenter;
    }

    @Override
    public void sendInfo() {
        server.send();
    }
}
