package co.edu.uptc.view;

import co.edu.uptc.presenter.Contract;

import javax.swing.*;
import java.awt.*;

public class ManagerView extends JFrame implements Contract.View{
    private Contract.Presenter presenter;
    private ServerPanel serverPanel;
    private JPanel panelOptions;
    private JButton start, stop;

    public ManagerView(Contract.Presenter presenter) {
        this.presenter = presenter;
        this.setTitle("Server");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(900, 700);
        initComponents();
        this.setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        serverPanel = new ServerPanel(this);
        panelOptions = new JPanel();
        start = new JButton("Start");
        stop = new JButton("Stop");

        panelOptions.add(start);
        panelOptions.add(stop);

        panelOptions.setPreferredSize(new Dimension(200, 700));
        serverPanel.setPreferredSize(new Dimension(700, 700));

        this.add(panelOptions, BorderLayout.WEST);
        this.add(serverPanel, BorderLayout.CENTER);

        setActionListeners();
    }

    private void setActionListeners(){
        start.addActionListener(e ->{
            serverPanel.isRunning = true;
            System.out.println("Iniciando");
            presenter.sendInfo();
        });
        stop.addActionListener(e->{
            serverPanel.isRunning = false;
        });
    }

    public Contract.Presenter getPresenter(){
        return presenter;
    }


    @Override
    public void drawNewRec(Rectangle rectangle) {
        serverPanel.drawNewRec(rectangle);
    }
}
