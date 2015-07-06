package com.example.bruno.mobloc;

/**
 * Created by Bruno on 6/30/2015.
 */
public class MagnetData {
    private String timeStamp;
    private double x;
    private double y;
    private double z;

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public MagnetData(String timeStamp, double x, double y, double z) {
        this.timeStamp = timeStamp;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String toString() {
        return "t="+timeStamp+", x="+x+", y ="+y+", z="+z;
    }


}
