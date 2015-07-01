package com.example.bruno.mobloc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.ArrayList;

/**
 * Created by Bruno on 7/1/2015.
 */
// Represents line graph
public class LineGraph {
    private int index = 0;

    private TimeSeries dataset = new TimeSeries("Rain Fall");
    private XYMultipleSeriesDataset multiData = new XYMultipleSeriesDataset();

    private XYSeriesRenderer renderer = new XYSeriesRenderer();

    private XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();

    private GraphicalView view;

    public LineGraph(){
        // add line series dat to xy multi data
        multiData.addSeries(dataset);

        // cusomize renderer
        renderer.setColor(Color.RED);
        renderer.setPointStyle(PointStyle.SQUARE);
        renderer.setFillPoints(true);

        for (int i = 0; i < 12; i++) {
            multiRenderer.addYTextLabel(i + 1, ""+i);
        }

        // enable zoom on multi xy renderer
       multiRenderer.setZoomButtonsVisible(true);
        multiRenderer.setXTitle("Day");
        multiRenderer.setYTitle("Temperature");
        multiRenderer.setYLabelsColor(0, Color.RED);
        //multiRenderer.setTextTypeface(Typeface.DEFAULT_BOLD);
        multiRenderer.setAxisTitleTextSize(16);

        // add series renderer to multi renderer
        multiRenderer.addSeriesRenderer(renderer);

    }

    public GraphicalView getView(Context context) {
        // create graphical view
        view = ChartFactory.getLineChartView(context, multiData, multiRenderer);
        return view;
    }

    public void addNewPoints(float i) {
        dataset.add(index, i);
        index+= 1  ;
    }
}
