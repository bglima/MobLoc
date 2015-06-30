package com.example.bruno.mobloc;

import android.app.Activity;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

/**
 * Created by Bruno on 6/26/2015.
 */
public class Data extends Activity{

    private static Data firstInstance = null;
    private FileWriter writer;
    private String path = Environment.getExternalStorageDirectory() + "/MobLocData/MobLoc.txt";

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

    public boolean cleanFile(){
        boolean deleted = false;
        try {
            File file = new File(path);
            deleted = file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }


}
