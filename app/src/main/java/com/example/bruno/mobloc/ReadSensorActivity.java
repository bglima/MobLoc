package com.example.bruno.mobloc;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;
import java.util.Calendar;


public class ReadSensorActivity extends Activity implements SensorEventListener, View.OnClickListener {
    private Button startBtn, stopBtn, uploadBtn;
    private LinearLayout layout;
    private static GraphicalView view;

    private SensorManager sensorManager;
    private boolean started = false;
    private ArrayList <MagnetData> sensorData;
    private View mChart;
    private LineGraph lineGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_sensor);
    }

    @Override
    protected void onStart() {
        super.onStart();

        layout = (LinearLayout) findViewById(R.id.chart_container);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensorData = new ArrayList();

        startBtn = (Button) findViewById(R.id.startBtn);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        uploadBtn = (Button) findViewById(R.id.uploadButton);
        startBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);
        uploadBtn.setOnClickListener(this);
        startBtn.setEnabled(true);
        stopBtn.setEnabled(false);

        if(sensorData == null || sensorData.size() == 0) {
            uploadBtn.setEnabled(false);
        }


        lineGraph = new LineGraph();

        view = lineGraph.getView(this);
        layout.addView(view);
    }

    @Override
    protected void onResume(){
       super.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (started==true) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_sensor, menu);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startBtn:
                // start reading
                startBtn.setEnabled(false);
                stopBtn.setEnabled(true);
                uploadBtn.setEnabled(false);
                // save previous data if avaliable...
                started = true;
                Sensor magnet = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                sensorManager.registerListener(this, magnet, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.stopBtn:
                // stop reading
                startBtn.setEnabled(true);
                stopBtn.setEnabled(false);
                uploadBtn.setEnabled(true);
                started = false;
                sensorManager.unregisterListener(this);
                // show data in chart

                break;
            case R.id.uploadButton:
                Data.getInstance().writeMagData(sensorData);
                // upload data to server
                break;

            default:
                break;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (started) {
            MagnetData newData = new MagnetData(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()),
                                    event.values[0], event.values[1], event.values[2]);

            lineGraph.addNewPoints(event.values[0], event.values[1], event.values[2]);
            sensorData.add(newData);
            view.repaint();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
