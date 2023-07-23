package co.edu.uptc.pojo;

import java.awt.*;

public class InfoRectangle {
    private Rectangle rectangle;
    private int colorRectangle;

    public InfoRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getColorRectangle() {
        return colorRectangle;
    }

    public void setColorRectangle(int colorRectangle) {
        this.colorRectangle = colorRectangle;
    }
}
