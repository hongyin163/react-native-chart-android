package cn.mandata.react_native_mpchart;

import android.graphics.Color;
import android.graphics.Paint;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

import cn.mandata.react_native_mpchart.MPBarLineChartManager;

/**
 * Created by Administrator on 2015/11/6.
 */
public class MPCandleStickChartManager extends MPBarLineChartManager {
    private String CLASS_NAME="MPCandleStickChart";
    private Random random;//用于产生随机数

    private LineChart chart;
    private LineData data;
    private LineDataSet dataSet;
    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected CandleStickChart createViewInstance(ThemedReactContext reactContext) {
        CandleStickChart chart=new CandleStickChart(reactContext);
        return  chart;
    }

    //{Data:[[0,1,2,3,4,5,6],[],[]],Label:""}
    //
//    e[0],日期
//    e[1],开盘
//    e[2],收盘
//    e[3],最高
//    e[4],最低
//    e[5],成交量
//    e[6],涨跌额
//    e[7],涨跌幅
    @ReactProp(name="data")
    public void setData(CandleStickChart chart,ReadableMap rm){
        String label=rm.getString("Label");
        ReadableArray xArray=rm.getArray("Data");
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
        dataSet.setShadowColor(Color.DKGRAY);
        dataSet.setShadowWidth(0.7f);
        dataSet.setDecreasingColor(Color.parseColor("#006030"));
        dataSet.setDecreasingPaintStyle(Paint.Style.FILL);
        dataSet.setIncreasingColor(Color.parseColor("#A50B31"));
        dataSet.setIncreasingPaintStyle(Paint.Style.FILL);
        chartData.addDataSet(dataSet);
        chart.setBackgroundColor(Color.WHITE);
        chart.setMaxVisibleValueCount(50);

        chart.setData(chartData);
    }
}
