package com.example.bruno.mobloc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by Bruno on 7/1/2015.
 */
// Represents line graph
public class LineGraph {
    private int index = 0;

    private XYMultipleSeriesDataset multiData = new XYMultipleSeriesDataset();

    private XYSeries xSeries = new XYSeries("Magnetometer X Readings");
    private XYSeries ySeries = new XYSeries("Magnetometer Y Readings");
    private XYSeries zSeries = new XYSeries("Magnetometer Z Readings");

    private XYSeriesRenderer xRenderer = new XYSeriesRenderer();
    private XYSeriesRenderer yRenderer = new XYSeriesRenderer();
    private XYSeriesRenderer zRenderer = new XYSeriesRenderer();

    private XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

    private GraphicalView view;


    public LineGraph(){
        // add line series dat to xy multi data
        multiData.addSeries(xSeries);
        multiData.addSeries(ySeries);
        multiData.addSeries(zSeries);

        // cusomize renderer
        xRenderer.setColor(Color.RED);
        xRenderer.setPointStyle(PointStyle.SQUARE);
        xRenderer.setFillPoints(true);

        yRenderer.setColor(Color.GREEN);
        yRenderer.setPointStyle(PointStyle.SQUARE);
        yRenderer.setFillPoints(true);

        zRenderer.setColor(Color.BLUE);
        zRenderer.setPointStyle(PointStyle.SQUARE);
        zRenderer.setFillPoints(true);

        for (int i = 0; i < 12; i++) {
            multiRenderer.addYTextLabel(i + 1, ""+i);
        }

        // enable zoom on multi xy renderer
        multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setXTitle("Sample Index");
        multiRenderer.setYTitle("Magnetometer Reading (uT)");
        multiRenderer.setYLabelsColor(0, Color.RED);
        //multiRenderer.setTextTypeface(Typeface.DEFAULT_BOLD);
        multiRenderer.setAxisTitleTextSize(16);

        // add series renderer to multi renderer
        multiRenderer.addSeriesRenderer(xRenderer);
        multiRenderer.addSeriesRenderer(yRenderer);
        multiRenderer.addSeriesRenderer(zRenderer);


    }

    public GraphicalView getView(Context context) {
        // create graphical view
        view = ChartFactory.getLineChartView(context, multiData, multiRenderer);
        return view;
    }

    public void addNewPoints(float i, float j, float k) {
        xSeries.add(index, i);
        ySeries.add(index, j);
        zSeries.add(index, k);
        index+= 1  ;
    }
}
