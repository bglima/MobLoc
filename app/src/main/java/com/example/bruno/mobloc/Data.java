package com.example.bruno.mobloc;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.text.format.Time;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.Vector;

/**
 * Created by Bruno on 6/26/2015.
 */
public class Data extends Activity{

    private static Data firstInstance = null;
    private FileWriter writer;

    public Data() {
    }

    public static Data getInstance(){
        if (firstInstance == null)
        {
            firstInstance = new Data();
        }

        return firstInstance;
    }

    public void writeString(String s){
        try {
            FileWriter writer = new FileWriter(Environment.getExternalStorageDirectory() + "/MobLocData/MobLoc.txt", true);
            writer.append(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
            writer.append("  ->  My new Data line...\n");
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
