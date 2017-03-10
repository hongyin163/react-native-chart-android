package cn.mandata.react_native_mpchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.YAxis.AxisDependency;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MPLineChartManager extends MPBarLineChartManager {
    private String CLASS_NAME="MPLineChart";
    private Random random;//用于产生随机数

    private LineChart chart;
    private LineData data;
    private LineDataSet dataSet;
    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected LineChart createViewInstance(ThemedReactContext reactContext) {
        LineChart chart=new LineChart(reactContext);

        // initialise event listener to bind to chart
        new MPChartSelectionEventListener(chart);

        return  chart;
    }

    //{XValues:[],YValues:[{Data:[],Label:""},{}]}
    @ReactProp(name="data")
    public void setData(LineChart chart,ReadableMap rm){

        ReadableArray xArray=rm.getArray("xValues");
        ArrayList<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getString(m));
        }
        ReadableArray ra=rm.getArray("yValues");
        LineData chartData=new LineData(xVals);
        for(int i=0;i<ra.size();i++){
            ReadableMap map=ra.getMap(i);
            ReadableArray data=map.getArray("data");
            String label=map.getString("label");
            float[] vals=new float[data.size()];
            ArrayList<Entry> entries=new ArrayList<Entry>();
            for (int j=0;j<data.size();j++){
                if (!data.isNull(j)) {
                    vals[j]=(float)data.getDouble(j);
                    Entry be=new Entry((float)data.getDouble(j),j);
                    entries.add(be);
                }
            }
            /*BarEntry be=new BarEntry(vals,i);
            entries.add(be);*/
            ReadableMap config= map.getMap("config");
            LineDataSet dataSet=new LineDataSet(entries,label);
            if(config.hasKey("drawCubic")) dataSet.setDrawCubic(config.getBoolean("drawCubic"));
            if(config.hasKey("drawCircles")) dataSet.setDrawCircles(config.getBoolean("drawCircles"));
            if(config.hasKey("circleSize")) dataSet.setCircleSize((float) config.getDouble("circleSize"));
            if(config.hasKey("lineWidth")) dataSet.setLineWidth((float) config.getDouble("lineWidth"));
            if(config.hasKey("valueTextColors")){
                ReadableArray colorsArray = config.getArray("valueTextColors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setValueTextColors(colors);
            }else
            if(config.hasKey("drawValues")) dataSet.setDrawValues(config.getBoolean("drawValues"));
            if(config.hasKey("valueTextColor")) dataSet.setValueTextColor(Color.parseColor(config.getString("valueTextColor")));

            // Text Size for bar value

            if(config.hasKey("valueTextSize")) dataSet.setValueTextSize((float)config.getDouble("valueTextSize"));

            if (config.hasKey("drawCircleHole")) dataSet.setDrawCircleHole(config.getBoolean("drawCircleHole"));
            if(config.hasKey("drawStepped")) dataSet.setDrawStepped(config.getBoolean("drawStepped"));
            if(config.hasKey("colors")){
                ReadableArray colorsArray = config.getArray("colors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setColors(colors);
            }else if (config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }
            if(config.hasKey("circleColors")){
                ReadableArray colorsArray = config.getArray("circleColors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setCircleColors(colors);
            }else if (config.hasKey("circleColor")) {
                int[] colors=new int[]{Color.parseColor(config.getString("circleColor"))};
                dataSet.setCircleColors(colors);
            }
            if (config.hasKey("dashedLine")) {
                String[] dashedLine = config.getString("dashedLine").split(" ");
                dataSet.enableDashedLine(Integer.parseInt(dashedLine[0]), Integer.parseInt(dashedLine[1]), Integer.parseInt(dashedLine[2]));
            }

            if (config.hasKey("drawFill")) dataSet.setDrawFilled(config.getBoolean("drawFill"));
            if (config.hasKey("fillColor")) dataSet.setFillColor(Color.parseColor(config.getString("fillColor")));
            if (config.hasKey("fillAlpha")) dataSet.setFillAlpha((int)(255 * config.getDouble("fillAlpha")));
            if (config.hasKey("bezier")) dataSet.setDrawCubic(config.getBoolean("bezier"));
            if (config.hasKey("axisDependency")) {
                AxisDependency axisDependency = AxisDependency.LEFT;
                if (config.getString("axisDependency").equalsIgnoreCase("RIGHT")) {
                    axisDependency = AxisDependency.RIGHT;
                }
                dataSet.setAxisDependency(axisDependency);
            }

            chartData.addDataSet(dataSet);
        }
        chart.setData(chartData);

        /**
         * Graph animation configurations
         * If no animation config provided, call chart.invalidate()
         * animation configs are maps with the following keys
         * - duration or durationX/durationY in case of animateXY
         * - support for easeFunction yet to be given
         */
        if (rm.hasKey("animateX")) {
            chart.animateX(rm.getMap("animateX").getInt("duration"));
        } else if (rm.hasKey("animateY")) {
            chart.animateY(rm.getMap("animateY").getInt("duration"));
        } else if (rm.hasKey("animateXY")) {
            ReadableMap animationConfig = rm.getMap("animateXY");
            chart.animateXY(
                animationConfig.getInt("durationX"),
                animationConfig.getInt("durationY")
            );
        } else {
            chart.invalidate();
        }
    }
}
