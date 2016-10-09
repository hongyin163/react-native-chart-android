package cn.mandata.react_native_mpchart;

import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.HorizontalBarChart;

import java.util.ArrayList;
import java.util.Random;

public class MPHorizontalBarChartManager extends MPBarChartManager {
    private String CLASS_NAME="MPHorizontalBarChart";

    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected HorizontalBarChart createViewInstance(ThemedReactContext reactContext) {
        HorizontalBarChart chart=new HorizontalBarChart(reactContext);
        new MPChartSelectionEventListener(chart);
        return  chart;
    }

}
