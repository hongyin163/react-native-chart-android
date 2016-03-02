package cn.mandata.react_native_mpchart;


import android.graphics.Color;
import android.graphics.Paint;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ReactPropGroup;
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
       // return  chart;

       /* mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[] {
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.BUBBLE, CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE, CombinedChart.DrawOrder.SCATTER
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);

        CombinedData data = new CombinedData(mMonths);

        data.setData(generateLineData());
        data.setData(generateBarData());
//        data.setData(generateBubbleData());
//         data.setData(generateScatterData());
//         data.setData(generateCandleData());

        mChart.setData(data);
        mChart.invalidate();*/

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
            if(config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }
            chartData.addDataSet(dataSet);
        }
        return  chartData;
    }

    public BarData getBarData(ReadableMap rm){

        ReadableArray xArray=rm.getArray("xValues");
        ArrayList<String> xVals=new ArrayList<String>();
        for(int m=0;m<xArray.size();m++){
            xVals.add(xArray.getString(m));
        }
        ReadableArray ra=rm.getArray("yValues");
        ArrayList<BarDataSet> dataSetList=new ArrayList<BarDataSet>();
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
            if(config.hasKey("color")) {
                int[] colors=new int[]{Color.parseColor(config.getString("color"))};
                dataSet.setColors(colors);
            }
            dataSetList.add(dataSet);
        }
        BarData barData=new BarData(xVals,dataSetList);
        return barData;

      /*  int n=5;
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            xVals.add((i+1990) + "");
        }

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals3 = new ArrayList<BarEntry>();

        float mult = 1000f;

        for (int i = 0; i < n; i++) {
            float val = (float) (Math.random() * mult) + 3;
            yVals1.add(new BarEntry(val, i));
        }

        for (int i = 0; i < n; i++) {
            float val = (float) (Math.random() * mult) + 3;
            yVals2.add(new BarEntry(val, i));
        }

        for (int i = 0; i < n; i++) {
            float val = (float) (Math.random() * mult) + 3;
            yVals3.add(new BarEntry(val, i));
        }

        // create 3 datasets with different types
        BarDataSet set1 = new BarDataSet(yVals1, "Company A");
        // set1.setColors(ColorTemplate.createColors(getApplicationContext(),
        // ColorTemplate.FRESH_COLORS));
        set1.setColor(Color.rgb(104, 241, 175));
        BarDataSet set2 = new BarDataSet(yVals2, "Company B");
        set2.setColor(Color.rgb(164, 228, 251));
        BarDataSet set3 = new BarDataSet(yVals3, "Company C");
        set3.setColor(Color.rgb(242, 247, 158));

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BarData data = new BarData(xVals, dataSets);
//        data.setValueFormatter(new LargeValueFormatter());

        // add space between the dataset groups in percent of bar-width
        data.setGroupSpace(80f);
        return data;*/
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
        chart.invalidate();
    }

    private final int itemcount = 12;
    private float getRandom(float range, float startsfrom) {
        return (float) (Math.random() * range) + startsfrom;
    }
    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new Entry(getRandom(15, 10), index));

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleSize(5f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setDrawCubic(true);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.rgb(240, 238, 70));

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {

        BarData d = new BarData();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < itemcount; index++)
            entries.add(new BarEntry(getRandom(15, 30), index));

        BarDataSet set = new BarDataSet(entries, "Bar DataSet");
        set.setColor(Color.rgb(60, 220, 78));
        set.setValueTextColor(Color.rgb(60, 220, 78));
        set.setValueTextSize(10f);
        d.addDataSet(set);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        return d;
    }

}
