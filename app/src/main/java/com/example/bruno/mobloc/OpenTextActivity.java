package com.example.bruno.mobloc;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.ejml.simple.SimpleMatrix;


/**
 * Created by Bruno on 6/30/2015.
 */
public class OpenTextActivity extends Activity {
    TextView textArea;
    Button clearBtn;
    Switch switchDataType;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_open);

        textArea = (TextView) findViewById(R.id.openTextView);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        switchDataType = (Switch) findViewById(R.id.switchData);

        textArea.setMovementMethod(new ScrollingMovementMethod());
        view = findViewById(R.id.mainTextView);
        updateTextArea(view);
    }

    public void updateTextArea(View view){
        if(switchDataType.isChecked() ) {
            textArea.setText((Data.getInstance()).returnTextData());
            switchDataType.setChecked(false);
        } else {
            textArea.setText((Data.getInstance()).returnMagnetData());
            switchDataType.setChecked(true);
        }

    }

    public void clearLog(View view){
        if(switchDataType.isChecked() ) {
            (Data.getInstance()).cleanFile(1);
        } else {
            (Data.getInstance()).cleanFile(0);
            switchDataType.setChecked(true);
        }
        updateTextArea(view);
    }


     @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_text_save, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
