package co.edu.uptc.pojo;

public class InformationForImage {
    private byte[] imgBytes;

    public InformationForImage(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }

    public byte[] getImgBytes() {
        return imgBytes;
    }

    public void setImgBytes(byte[] imgBytes) {
        this.imgBytes = imgBytes;
    }
}

