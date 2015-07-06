package com.example.bruno.mobloc;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Bruno on 6/26/2015.
 */
public class Data extends Activity{

    private static Data firstInstance = null;
    private String path = Environment.getExternalStorageDirectory() + "/MobLocData/MobLoc.txt";
    private String sensorPath = Environment.getExternalStorageDirectory() + "/MobLocData/SensorReadings.txt";

    public static Data getInstance(){
        if (firstInstance == null)
        {
            firstInstance = new Data();
        }

        return firstInstance;
    }

    public void writeString(String s){
        try {
            FileWriter writer = new FileWriter(path, true);
            writer.append(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
            writer.append("  ->  " + s + "\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeMagData(ArrayList <MagnetData> array) {
        try {
            FileWriter writer = new FileWriter(sensorPath, true);

            writer.append(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
            writer.append("  -> Starting to show sensor readings below... " + "\n");
            for(MagnetData temp : array) {
                writer.append(temp.getTimeStamp());
                writer.append("  X:"+ String.format("%.2f", temp.getX())+", Y:"+String.format("%.2f", temp.getY()) + ", Z: " + String.format("%.2f", temp.getZ()) + "\n");
            }
            writer.append("\n");

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String returnMagnetData(){
        String result = "";
        String line;
        try {
            FileReader reader = new FileReader(sensorPath);
            BufferedReader br = new BufferedReader(reader);

            while((line = br.readLine()) != null)
                result += line + "\n";

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String returnTextData(){
        String result = "";
        String line;
        try {
            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);

            while((line = br.readLine()) != null)
                result += line + "\n";

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean cleanFile(int op){
        boolean deleted = false;
        String tempPath = "";

        switch (op) {
            case 0:
                tempPath = path;
                break;
            case 1:
                tempPath = sensorPath;
                break;
        }

        try {
            File file = new File(tempPath);
            deleted = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }


}
