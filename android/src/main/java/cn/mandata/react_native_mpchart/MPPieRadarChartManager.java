package cn.mandata.react_native_mpchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieRadarChartBase;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/12/24.
 */
public class MPPieRadarChartManager extends SimpleViewManager<PieRadarChartBase> {
    private String CLASS_NAME="PieRadarChart";

    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected PieRadarChartBase createViewInstance(ThemedReactContext reactContext) {
        return null;
    }


    @ReactProp(name="touchEnabled",defaultBoolean = true)
    public void setTouchEnabled(PieRadarChartBase chart,boolean enable){
        chart.setTouchEnabled(enable);
    }
    @ReactProp(name="dragEnabled",defaultBoolean = true)
    public  void  setDragEnabled(PieRadarChartBase chart,boolean enable){
        chart.setTouchEnabled(enable);
    }

    @ReactProp(name="highlightPerTapEnabled",defaultBoolean = true)
    public  void  setHighlightPerTapEnabled(PieRadarChartBase chart,boolean enable){
        chart.setHighlightPerTapEnabled(enable);
    }
    @ReactProp(name="dragDecelerationEnabled",defaultBoolean = true)
    public  void  setDragDecelerationEnabled(PieRadarChartBase chart,boolean enable){
        chart.setDragDecelerationEnabled(enable);
    }

    @ReactProp(name="dragDecelerationFrictionCoef",defaultFloat = 0.50f)
    public  void  setDragDecelerationFrictionCoef(PieRadarChartBase chart,float v){
        chart.setDragDecelerationFrictionCoef(v);
    }

    @ReactProp(name="description")
    public  void  setDescription(PieRadarChartBase chart,String v){
        chart.setDescription(v);
    }
    @ReactProp(name="backgroundColor")
    public  void  setBackgroundColor(PieRadarChartBase chart,String v){
        chart.setBackgroundColor(Color.parseColor(v));
    }

    @ReactProp(name="chartPadding")
    public  void  setPadding(PieRadarChartBase chart,String v){
        String[] padding=v.split(" ");
        if(padding.length==1){
            int pad=(Integer.parseInt(padding[0]));
            chart.setPadding(pad,pad,pad,pad);
        }else if(padding.length==2){
            int pad1=(Integer.parseInt(padding[0]));
            int pad2=(Integer.parseInt(padding[1]));
            chart.setPadding(pad2,pad1,pad2,pad1);
        }else if(padding.length==4){
            int pad1=(Integer.parseInt(padding[0]));
            int pad2=(Integer.parseInt(padding[1]));
            int pad3=(Integer.parseInt(padding[0]));
            int pad4=(Integer.parseInt(padding[1]));
            chart.setPadding(pad4,pad1,pad2,pad3);
        }
    }
    @ReactProp(name="legend")
    public void setLegend(PieRadarChartBase chart,ReadableMap v){
        Legend legend=chart.getLegend();
        if(v.hasKey("enable")) legend.setEnabled(v.getBoolean("enable"));
        if(v.hasKey("position"))  legend.setPosition(Legend.LegendPosition.valueOf(v.getString("position")));
        if(v.hasKey("direction"))  legend.setDirection(Legend.LegendDirection.valueOf(v.getString("direction")));

        if(v.hasKey("legendForm"))  legend.setForm(Legend.LegendForm.valueOf(v.getString("legendForm")));

        if(v.hasKey("textColor"))  legend.setTextColor(Color.parseColor(v.getString("textColor")));
        if(v.hasKey("textSize"))  legend.setTextSize((float) v.getDouble("textSize"));
        if(v.hasKey("xOffset"))  legend.setXOffset((float) v.getDouble("xOffset"));
        if(v.hasKey("yOffset"))  legend.setYOffset((float) v.getDouble("yOffset"));

        if(v.hasKey("custom")){
            ReadableMap custom=v.getMap("custom");
            ReadableArray colors=custom.getArray("colors");
            ReadableArray labels=custom.getArray("labels");
            if(colors.size()==labels.size()) {
                int[] cols = new int[colors.size()];
                String[] labs = new String[colors.size()];
                for (int j = 0; j < colors.size(); j++) {
                    cols[j] = Color.parseColor(colors.getString(j));
                    labs[j] = labels.getString(j);
                }
                legend.setCustom(cols,labs);
            }
        }
    }
}
