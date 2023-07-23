package co.edu.uptc.presenter;

import java.awt.*;

public interface Contract {
    interface Model{
        void setByPoint(int coorX, int coorY);
        void buildCoordinatesPath();
        void sendInfo();
        void imgPath(String ruta);
    }
    interface View{
        void drawNewRec(Rectangle rectangle);
    }
    interface Presenter{
        void sendInfo();
        void setByPoint(int coorX, int coorY);
        void buildCoordinatesPath();
        void drawNewRec(Rectangle rectangle);
        void imgPath(String ruta);
    }
}
