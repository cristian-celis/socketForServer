package co.edu.uptc.view;

import co.edu.uptc.model.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class ServerPanel extends JPanel implements MouseListener, MouseMotionListener {
    private ManagerView view;
    private Rectangle rectangle;
    boolean isRunning = false;

    public ServerPanel(ManagerView view) {
        this.setBackground(new Color(215, 219, 221));
        this.view = view;
        initComponent();
    }

    private void initComponent() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        rectangle = new Rectangle(0, 0, 25, 25);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.draw(rectangle);
    }

    private boolean isReading = false;
    private boolean onRectangle = false;

    @Override
    public void mousePressed(MouseEvent e) {
        onRectangle = rectangle.contains(e.getX(), e.getY());
        if (e.getButton() == MouseEvent.BUTTON1 && onRectangle) {
            isReading = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (onRectangle) {
            view.getPresenter().buildCoordinatesPath();
            isReading = false;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics2D g;
        if (isRunning && isReading) {
            view.getPresenter().setByPoint(e.getX(), e.getY());
        }
    }

    public void drawNewRec(Rectangle newRec){
        /*Graphics2D g = (Graphics2D) getGraphics();
        rectangle.setLocation(rectangle.x, rectangle.y);
        g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);*/
        this.rectangle = newRec;
        this.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
     }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
