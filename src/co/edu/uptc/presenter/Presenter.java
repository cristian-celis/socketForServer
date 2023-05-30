package co.edu.uptc.presenter;

import co.edu.uptc.model.ManagerModel;
import co.edu.uptc.view.ManagerView;

import java.awt.*;

public class Presenter implements Contract.Presenter{
    private ManagerModel model;
    private ManagerView view;
    public Presenter(String[] args){
        model = new ManagerModel(this, args);
        view = new ManagerView(this);
    }

    @Override
    public void sendInfo() {
        model.sendInfo();
    }

    @Override
    public void setByPoint(int coorX, int coorY) {
        model.setByPoint(coorX, coorY);
    }

    @Override
    public void buildCoordinatesPath() {
        model.buildCoordinatesPath();
    }

    @Override
    public void drawNewRec(Rectangle rectangle) {
        view.drawNewRec(rectangle);
    }
}
