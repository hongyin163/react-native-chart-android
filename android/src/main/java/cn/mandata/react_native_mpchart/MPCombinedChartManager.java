package cn.mandata.react_native_mpchart;


import android.graphics.Color;
import android.graphics.Paint;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BarLineScatterCandleBubbleDataSet;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.components.YAxis.AxisDependency;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MPCombinedChartManager extends MPBarLineChartManager {
    private String CLASS_NAME="MPCombinedChart";
    @Override
    public String getName() {
        return this.CLASS_NAME;
    }
    protected String[] mMonths = new String[] {
            "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Okt", "Nov", "Dec"
    };
    @Override
    protected CombinedChart createViewInstance(ThemedReactContext reactContext) {
        CombinedChart mChart=new CombinedChart(reactContext);
        return mChart;
    }
    //{xValues:[],yValues:[{data:[],label:"",config:{}},{...}]}
    public LineData getLineData(ReadableMap rm){

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
                vals[j]=(float)data.getDouble(j);
                Entry be=new Entry((float)data.getDouble(j),j);
                entries.add(be);
            }
            /*BarEntry be=new BarEntry(vals,i);
            entries.add(be);*/
            ReadableMap config= map.getMap("config");
            LineDataSet dataSet=new LineDataSet(entries,label);
            if(config.hasKey("drawCircles")) dataSet.setDrawCircles(config.getBoolean("drawCircles"));
            if(config.hasKey("circleSize")) dataSet.setCircleSize((float) config.getDouble("circleSize"));
            if(config.hasKey("lineWidth")) dataSet.setLineWidth((float) config.getDouble("lineWidth"));
            if(config.hasKey("drawValues")) dataSet.setDrawValues(config.getBoolean("drawValues"));
            if(config.hasKey("valueTextColor")) dataSet.setValueTextColor(Color.parseColor(config.getString("valueTextColor")));
            if(config.hasKey("valueTextSize")) dataSet.setValueTextSize((float)config.getDouble("valueTextSize"));

            if(config.hasKey("drawFill")) dataSet.setDrawFilled(config.getBoolean("drawFill"));
            if(config.hasKey("colors")){
                ReadableArray colorsArray = config.getArray("colors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setColors(colors);
            } else if(config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }

            if (config.hasKey("axisDependency")) {
                AxisDependency axisDependency = AxisDependency.LEFT;
                if (config.getString("axisDependency").equalsIgnoreCase("RIGHT")) {
                    axisDependency = AxisDependency.RIGHT;
                }
                dataSet.setAxisDependency(axisDependency);
            }

            chartData.addDataSet(dataSet);
        }
        return  chartData;
    }

    public BarData getBarData(ReadableMap rm){

        ReadableArray xArray=rm.getArray("xValues");
        List<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getString(m));
        }
        ReadableArray ra=rm.getArray("yValues");
        List<IBarDataSet> dataSetList=new ArrayList<IBarDataSet>();
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
            BarDataSet dataSet=new BarDataSet(entries,label);
            ReadableMap config= map.getMap("config");
            if(config.hasKey("drawValues")) dataSet.setDrawValues(config.getBoolean("drawValues"));
            if(config.hasKey("colors")){
                ReadableArray colorsArray = config.getArray("colors");
                ArrayList<Integer> colors = new ArrayList<>();
                for(int c = 0; c < colorsArray.size(); c++){
                    colors.add(Color.parseColor(colorsArray.getString(c)));
                }
                dataSet.setColors(colors);
            }else if(config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }

            if (config.hasKey("axisDependency")) {
                AxisDependency axisDependency = AxisDependency.RIGHT;
                if (config.getString("axisDependency").equalsIgnoreCase("LEFT")) {
                    axisDependency = AxisDependency.LEFT;
                }
                dataSet.setAxisDependency(axisDependency);
            }

            dataSetList.add(dataSet);
        }
        BarData barData=new BarData(xVals,dataSetList);
        return barData;
    }

    public CandleData getCandleData(ReadableMap rm){
        String label=rm.getString("label");
        ReadableArray xArray=rm.getArray("data");
        ArrayList<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getArray(m).getString(0));
        }
        CandleData chartData=new CandleData(xVals);
        ArrayList<CandleEntry> entries=new ArrayList<CandleEntry>();
        for(int i=0;i<xArray.size();i++){
            ReadableArray data=xArray.getArray(i);
            float open= ((float) data.getDouble(1));
            float close= ((float) data.getDouble(2));
            float high= ((float) data.getDouble(3));
            float low=((float) data.getDouble(4));
            CandleEntry be=new CandleEntry(i,high,low,open,close);

            entries.add(be);
        }
        CandleDataSet dataSet=new CandleDataSet(entries,label);

        ReadableMap config= rm.getMap("config");
        if(config.hasKey("color")) {
            dataSet.setColor(Color.parseColor(config.getString("color")));
        }
        if(config.hasKey("shadowColor")) {
            dataSet.setShadowColor(Color.parseColor(config.getString("shadowColor")));
        }
        if(config.hasKey("shadowWidth")) {
            dataSet.setShadowWidth((float) config.getDouble("shadowWidth"));
        }

        if(config.hasKey("decreasingColor")) {
            dataSet.setDecreasingColor(Color.parseColor(config.getString("decreasingColor")));
        }
        if(config.hasKey("decreasingPaintStyle")) {
            dataSet.setDecreasingPaintStyle(Paint.Style.valueOf(config.getString("decreasingPaintStyle")));
        }
        if(config.hasKey("increasingColor")) {
            dataSet.setIncreasingColor(Color.parseColor(config.getString("increasingColor")));
        }
        if(config.hasKey("increasingPaintStyle")) {
            dataSet.setIncreasingPaintStyle(Paint.Style.valueOf(config.getString("increasingPaintStyle")));
        }
        chartData.addDataSet(dataSet);

        return chartData;
    }
    //{line:{data:[],config:[]},bar:{},candle:{}}
    @ReactProp(name="data")
    public void setData(CombinedChart chart,ReadableMap rm){
        CombinedData chartData=null;
        if(rm.hasKey("line")) {
            LineData lineData=getLineData(rm.getMap("line"));
            if(chartData==null) {
                chartData=new CombinedData(lineData.getXVals());
            }
            chartData.setData(lineData);
        }
        if(rm.hasKey("bar")) {
            BarData barData=getBarData(rm.getMap("bar"));
            if(chartData==null) {
                chartData=new CombinedData(barData.getXVals());
            }
            chartData.setData(barData);
        }
        if(rm.hasKey("candle")) {
            CandleData candleData=getCandleData(rm.getMap("candle"));
            if(chartData==null) {
                chartData=new CombinedData(candleData.getXVals());
            }
            chartData.setData(candleData);
        }
        chart.clear();
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

        // chart.invalidate();
    }

    private final int itemcount = 12;
    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
}
