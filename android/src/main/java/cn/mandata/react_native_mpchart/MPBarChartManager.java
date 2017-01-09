package cn.mandata.react_native_mpchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MPBarChartManager extends MPBarLineChartManager {
    private String CLASS_NAME="MPBarChart";
    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected BarChart createViewInstance(ThemedReactContext reactContext) {
        BarChart chart=new BarChart(reactContext);

        // initialise event listener to bind to chart
        new MPChartSelectionEventListener(chart);

        return  chart;
    }

    //{XValues:[],YValues:[{Data:[],Label:""},{}]}
    @ReactProp(name="data")
    public void setData(BarChart chart,ReadableMap rm){

        ReadableArray xArray=rm.getArray("xValues");
        ArrayList<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getString(m));
        }
        ReadableArray ra=rm.getArray("yValues");
        BarData barData=new BarData(xVals);
        for(int i=0;i<ra.size();i++){
            ReadableMap map=ra.getMap(i);
            ReadableArray data=map.getArray("data");
            String label=map.getString("label");
            float[] vals=new float[data.size()];
            ArrayList<BarEntry> entries=new ArrayList<BarEntry>();
            for (int j=0;j<data.size();j++){
                vals[j]=(float)data.getDouble(j);
                BarEntry be=new BarEntry((float)data.getDouble(j),j);
                entries.add(be);
            }
            /*BarEntry be=new BarEntry(vals,i);
            entries.add(be);*/
            BarDataSet dataSet=new BarDataSet(entries,label);
            ReadableMap config= map.getMap("config");

            if(config.hasKey("valueTextColors")){
                ReadableArray colorsArray = config.getArray("valueTextColors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setValueTextColors(colors);
            }else
            if(config.hasKey("valueTextColor")) dataSet.setValueTextColor(Color.parseColor(config.getString("valueTextColor")));
            
            // Text Size for bar value

            if(config.hasKey("valueTextSize")) dataSet.setValueTextSize((float)config.getDouble("valueTextSize"));

            if(config.hasKey("drawValues")) dataSet.setDrawValues(config.getBoolean("drawValues"));
            if(config.hasKey("colors")){
                ReadableArray colorsArray = config.getArray("colors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setColors(colors);
            }else
            if(config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }

            if(config.hasKey("valueFormatter")){
                ReadableMap formatterMap = config.getMap("valueFormatter");
                if(formatterMap.hasKey("type")){
                    String type = formatterMap.getString("type");
                    if("printf".equalsIgnoreCase(type)){
                        String format = "";
                        if(formatterMap.hasKey("format")) format = formatterMap.getString("format");
                        dataSet.setValueFormatter(new PrintfValueFormatter(format));
                    }
                }
            }

            barData.addDataSet(dataSet);

        }
        chart.setData(barData);

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
