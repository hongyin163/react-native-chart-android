package cn.mandata.react_native_mpchart;

import android.graphics.Color;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ReactProp;
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
public class MPBarLineChartManager extends SimpleViewManager<BarLineChartBase> {
    private String CLASS_NAME="MPBarLineChart";
    private Random random;//用于产生随机数

    private BarChart chart;
    private BarData data;
    private BarDataSet dataSet;
    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected BarLineChartBase createViewInstance(ThemedReactContext reactContext) {
        BarChart chart=new BarChart(reactContext);
         //com.facebook.react.uimanager.ViewGroupManager
/*        *//**图表具体设置*//*
        ArrayList<BarEntry> entries = new ArrayList<>();//显示条目
        ArrayList<String> xVals = new ArrayList<String>();//横坐标标签
        random=new Random();//随机数
        for(int i=0;i<12;i++){
            float profit= random.nextFloat()*1000;
            //entries.add(BarEntry(float val,int positon);
            entries.add(new BarEntry(profit,i));
            xVals.add((i+1)+"月");
        }
        dataSet = new BarDataSet(entries, "公司年利润报表");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        data = new BarData(xVals, dataSet);
        chart.setData(data);
        //设置Y方向上动画animateY(int time);
        chart.animateY(3000);
        //图表描述
        chart.setDescription("公司前半年财务报表(单位：万元)");*/
        return  chart;
    }


/*    //{xLabel:"",yLabel:"",xUnit:"",yUnit:"",xValues:[],yValues:[],unit:"",data:[]}
    @ReactProp(name = "config")
    public void setConfig(BarChart chart,ReadableMap data){
        ReadableArray ra=data.getArray("data");
        String label=data.getString("label");

        ArrayList<BarEntry> entries = new ArrayList<>();
        for(int i=0;i<ra.size();i++){
            float v=(float)ra.getDouble(i);
            entries.add(new BarEntry(v,i));
        }
        BarDataSet dataSet = new BarDataSet(entries,label);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData bdata =null;// new BarData(xVals, dataSet);

        chart.setData(bdata);
    }*/
    @ReactProp(name="touchEnabled",defaultBoolean = true)
    public void setTouchEnabled(BarLineChartBase chart,boolean enable){
        chart.setTouchEnabled(enable);
    }
    @ReactProp(name="dragEnabled",defaultBoolean = true)
    public  void  setDragEnabled(BarLineChartBase chart,boolean enable){
        chart.setTouchEnabled(enable);
    }
    @ReactProp(name="scaleEnabled",defaultBoolean = true)
    public  void  setScaleEnabled(BarLineChartBase chart,boolean enable){
        chart.setScaleEnabled(enable);
    }
    @ReactProp(name="scaleXEnabled",defaultBoolean = true)
    public  void  setScaleXEnabled(BarLineChartBase chart,boolean enable){
        chart.setScaleXEnabled(enable);
    }
    @ReactProp(name="scaleYEnabled",defaultBoolean = true)
    public  void  setScaleYEnabled(BarLineChartBase chart,boolean enable){
        chart.setScaleYEnabled(enable);
    }
    @ReactProp(name="pinchZoom",defaultBoolean = true)
    public  void  setPinchZoom(BarLineChartBase chart,boolean enable){
        chart.setPinchZoom(enable);
    }

    @ReactProp(name="doubleTapToZoomEnabled",defaultBoolean = true)
    public  void  setDoubleTapToZoomEnabled(BarLineChartBase chart,boolean enable){
        chart.setDoubleTapToZoomEnabled(enable);
    }

    @ReactProp(name="highlightPerDragEnabled",defaultBoolean = true)
    public  void  setHighlightPerDragEnabled(BarLineChartBase chart,boolean enable){
        chart.setHighlightPerDragEnabled(enable);
    }
    @ReactProp(name="highlightPerTapEnabled",defaultBoolean = true)
    public  void  setHighlightPerTapEnabled(BarLineChartBase chart,boolean enable){
        chart.setHighlightPerTapEnabled(enable);
    }
    @ReactProp(name="dragDecelerationEnabled",defaultBoolean = true)
    public  void  setDragDecelerationEnabled(BarLineChartBase chart,boolean enable){
        chart.setDragDecelerationEnabled(enable);
    }

    @ReactProp(name="dragDecelerationFrictionCoef",defaultFloat = 0.50f)
    public  void  setDragDecelerationFrictionCoef(BarLineChartBase chart,float v){
        chart.setDragDecelerationFrictionCoef(v);
    }
    @ReactProp(name="maxVisibleValueCount",defaultInt = 50)
    public  void  setMaxVisibleValueCount(BarLineChartBase chart,int v){
        chart.setMaxVisibleValueCount(v);
    }
    //{x:{LineColor:"",LineWidth:"",TextColor:"",TextSize:""},y:{}}
    @ReactProp(name="limitLine")
    public  void  setLimitLine(BarLineChartBase chart,ReadableMap v){
        if(v.getMap("x")!=null){
            setAxisLimit(chart.getXAxis(), v.getMap("x"));
        }
        if(v.getMap("y")!=null){
            if(chart.getAxisLeft()!=null) {
                setAxisLimit(chart.getAxisLeft(), v.getMap("y"));
            }
            else if(chart.getAxisRight()!=null){
                setAxisLimit(chart.getAxisRight(), v.getMap("y"));
            }
        }
    }
    private void setAxisLimit(AxisBase axis,ReadableMap x){
        String xLabel=x.getString("Label");
        float xLimit=(float)x.getDouble("Limit");
        String xLineColor=x.getString("LineColor");
        String xTextColor=x.getString("TextColor");
        float xTextSize=(float)x.getDouble("TextSize");
        float LineWidth=(float)x.getDouble("LineWidth");

        LimitLine ll = new LimitLine(xLimit, xLabel);
        ll.setLineColor(Color.parseColor(xLineColor));
        ll.setLineWidth(LineWidth);
        ll.setTextColor(Color.parseColor(xTextColor));
        ll.setTextSize(xTextSize);
        axis.addLimitLine(ll);
    }
    //{x:{},y:{}}
    @ReactProp(name="xAxis")
    public  void  setXAxis(BarLineChartBase chart,ReadableMap v){
        AxisBase x= chart.getXAxis();
        setAxisInfo(x, v);
    }
    @ReactProp(name="yAxisLeft")
    public  void  setYAxisLeft(BarLineChartBase chart,ReadableMap v){
        AxisBase x= chart.getAxisLeft();
        setAxisInfo(x,v);
        setYAxisInfo((YAxis) x, v);
    }
    @ReactProp(name="yAxisRight")
    public  void  setYAxisRight(BarLineChartBase chart,ReadableMap v){
        AxisBase x= chart.getAxisRight();
        setAxisInfo(x,v);
        setYAxisInfo((YAxis) x, v);
    }
    private void setAxisInfo(AxisBase axis,ReadableMap v){
        if(v.hasKey("Enable")) axis.setEnabled(v.getBoolean("Enable"));
        if(v.hasKey("DrawAxisLine")) axis.setDrawAxisLine(v.getBoolean("DrawAxisLine"));

        if(v.hasKey("DrawGridLines")) axis.setDrawGridLines(v.getBoolean("DrawGridLines"));
        if(v.hasKey("DrawLabels")) axis.setDrawLabels(v.getBoolean("DrawLabels"));

        if(v.hasKey("TextColor")) axis.setTextColor(Color.parseColor(v.getString("TextColor")));
        if(v.hasKey("GridColor")) axis.setGridColor(Color.parseColor(v.getString("GridColor")));

        if(v.hasKey("GridLineWidth")) axis.setGridLineWidth((float)v.getDouble("GridLineWidth"));
        if(v.hasKey("AxisLineColor")) axis.setAxisLineColor(Color.parseColor(v.getString("AxisLineColor")));
        if(v.hasKey("AxisLineWidth")) axis.setAxisLineWidth((float)(v.getDouble("AxisLineWidth")));
        if(v.hasKey("GridDashedLine")) {
            ReadableMap gdl=v.getMap("GridDashedLine");
            axis.enableGridDashedLine((float)gdl.getDouble("LineLength"),
                    (float)gdl.getDouble("SpaceLength"),
                    (float)gdl.getDouble("Phase"));
        }
    }

    private  void setYAxisInfo(YAxis axis,ReadableMap v){
        if(v.hasKey("StartAtZero")) axis.setStartAtZero(v.getBoolean("StartAtZero"));
        if(v.hasKey("AxisMaxValue")) axis.setAxisMaxValue((float) v.getDouble("AxisMaxValue"));

        if(v.hasKey("AxisMinValue")) axis.setAxisMinValue((float) v.getDouble("AxisMinValue"));
        if(v.hasKey("Inverted")) axis.setInverted(v.getBoolean("Inverted"));

        if(v.hasKey("SpaceTop")) axis.setSpaceTop((float) (v.getDouble("SpaceTop")));
        if(v.hasKey("SpaceBottom")) axis.setSpaceBottom((float) (v.getDouble("SpaceBottom")));

        if(v.hasKey("ShowOnlyMinMax")) axis.setShowOnlyMinMax(v.getBoolean("ShowOnlyMinMax"));
        if(v.hasKey("LabelCount")) axis.setLabelCount(v.getInt("LabelCount"), true);

        if(v.hasKey("Position")) {
            String name=v.getString("Position");
            axis.setPosition(YAxis.YAxisLabelPosition.valueOf(name));
        }

        if(v.hasKey("ValueFormatter"))
            axis.setValueFormatter(new CustomYAxisValueFormatter(v.getString("ValueFormatter")) );
    }

    //{EnableLeft:true,EnableRight:true}
    @ReactProp(name="yAxis")
    public  void  setYAxis(BarLineChartBase chart,ReadableMap v){
        YAxis x= chart.getAxisLeft();
        setAxisInfo(x,v);
        setYAxisInfo(x,v);
    }
    @ReactProp(name="description")
    public  void  setDescription(BarLineChartBase chart,String v){
       chart.setDescription(v);
    }
    @ReactProp(name="backgroundColor")
    public  void  setBackgroundColor(BarLineChartBase chart,String v){
        chart.setBackgroundColor(Color.parseColor(v));
    }
    @ReactProp(name="drawGridBackground")
    public  void  setDrawGridBackground(BarLineChartBase chart,boolean v){
        chart.setDrawGridBackground(v);
    }
    @ReactProp(name="borderColor")
    public  void  setBorderColor(BarLineChartBase chart,String v){
        chart.setBorderColor(Color.parseColor(v));
    }
    @ReactProp(name="borderWidth")
    public  void  setBorderWidth(BarLineChartBase chart,double v){
        chart.setBorderWidth((float) v);
    }
    @ReactProp(name="gridBackgroundColor")
    public  void  setGridBackgroundColor(BarLineChartBase chart,String v){
        chart.setGridBackgroundColor(Color.parseColor(v));
    }
}
