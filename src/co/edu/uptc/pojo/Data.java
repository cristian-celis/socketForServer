package co.edu.uptc.pojo;

public class Data {
    private InformationForRectangle informationForRectangle;
    private InformationForImage informationImage;

    public Data(InformationForRectangle informationForRectangle, InformationForImage informationImage){
        this.informationForRectangle = informationForRectangle;
        this.informationImage = informationImage;
    }

    public InformationForRectangle getInformationForRectangle() {
        return informationForRectangle;
    }

    public void setInformationForRectangle(InformationForRectangle informationForRectangle) {
        this.informationForRectangle = informationForRectangle;
    }

    public InformationForImage getInformationImage() {
        return informationImage;
    }

    public void setInformationImage(InformationForImage informationImage) {
        this.informationImage = informationImage;
    }
}
