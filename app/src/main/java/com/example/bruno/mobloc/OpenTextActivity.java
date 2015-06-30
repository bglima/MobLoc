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
import android.widget.TextView;

/**
 * Created by Bruno on 6/30/2015.
 */
public class OpenTextActivity extends Activity {
    TextView textArea;
    Button clearBtn;
    Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_open);

        textArea = (TextView) findViewById(R.id.openTextView);
        clearBtn = (Button) findViewById(R.id.clearBtn);
        exitBtn = (Button) findViewById(R.id.exitBtn);

        textArea.setMovementMethod(new ScrollingMovementMethod());
        updateTextArea();

    }

    public void updateTextArea(){
        textArea.setText((Data.getInstance()).returnTextData());
    }

    public void clearLog(View view){
        (Data.getInstance()).cleanFile();
        updateTextArea();
    }

    public void exit(View view){
        finish();
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
