package com.example.bruno.mobloc;

import android.app.Activity;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Vector;

/**
 * Created by Bruno on 6/26/2015.
 */
public final class Data extends Activity {

    private FileOutputStream fos;
    public String FILENAME;

    private Data() {
        FILENAME = "MobLoc.txt";

    }
    public void writeString(String s) {
        try {
            fos = openFileOutput("/sdcard/textfile.txt", MODE_WORLD_WRITEABLE);
            fos.write(s.getBytes());
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
