package Service;

public class History {
    private int ID;
    private double X좌표;
    private double Y좌표;
    private String 조회일자;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getX좌표() {
        return X좌표;
    }

    public void setX좌표(double x좌표) {
        X좌표 = x좌표;
    }

    public double getY좌표() {
        return Y좌표;
    }

    public void setY좌표(double y좌표) {
        Y좌표 = y좌표;
    }

    public String get조회일자() {
        return 조회일자;
    }

    public void set조회일자(String 조회일자) {
        this.조회일자 = 조회일자;
    }
}
