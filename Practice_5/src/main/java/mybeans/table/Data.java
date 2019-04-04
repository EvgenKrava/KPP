package mybeans.table;

public class Data {
    private String date;
    private double x;
    private double y;


    public Data(){
        date = null;
        x=0;
        y=0;
    }

    public Data(String date, double x, double y){
        this.date = date;
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getDate() {
        return date;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
