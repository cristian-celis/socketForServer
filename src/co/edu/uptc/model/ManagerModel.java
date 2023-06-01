package co.edu.uptc.model;

import co.edu.uptc.presenter.Contract;
import co.edu.uptc.presenter.Presenter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ManagerModel implements Contract.Model{

    private String[] args;
    private Server server;
    private Contract.Presenter presenter;
    private RouteBuilder routeBuilder;
    private Rectangle rectangle;
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

    @Override
    public void setByPoint(int coorX, int coorY){
        System.out.println("Nueva posicion -> x: " + coorX + ", y: " + coorY);
        server.changes = true;
        this.rectangle.setLocation(coorX, coorY);
        //routeBuilder.setByPoint(coorX, coorY);
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
