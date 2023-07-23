package co.edu.uptc.pojo;

public class InformationForRectangle {
    private InfoPanel infoPanel;
    private InfoRectangle infoRectangle;

    public InformationForRectangle(InfoPanel infoPanel, InfoRectangle infoRectangle) {
        this.infoPanel = infoPanel;
        this.infoRectangle = infoRectangle;
    }

    public InfoPanel getInfoPanel() {
        return infoPanel;
    }

    public void setInfoPanel(InfoPanel infoPanel) {
        this.infoPanel = infoPanel;
    }

    public InfoRectangle getInfoRectangle() {
        return infoRectangle;
    }

    public void setInfoRectangle(InfoRectangle infoRectangle) {
        this.infoRectangle = infoRectangle;
    }
}
