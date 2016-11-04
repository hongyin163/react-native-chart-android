package cn.mandata.react_native_mpchart;

import android.graphics.Color;
import android.graphics.Typeface;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;
import java.util.Random;


public class MPRadarChartManager extends MPPieRadarChartManager {
    private String CLASS_NAME="MPRadarChart";

    @Override
    public String getName() {
        return this.CLASS_NAME;
    }

    @Override
    protected RadarChart createViewInstance(ThemedReactContext reactContext) {
        RadarChart chart=new RadarChart(reactContext);

        // initialise event listener to bind to chart
        // TODO: is it need to move to MPRadarChartSelectionEventListener?
        //new MPPieChartSelectionEventListener(chart);

        return  chart;
    }

//    @Override
//    protected PieChart createViewInstance(ThemedReactContext reactContext) {
//        PieChart chart= new PieChart(reactContext);
//        return chart;
//    }

    @ReactProp(name="webLineWidth",defaultFloat=0.5f)
    public void setWebLineWidth(RadarChart chart,float webLineWidth){
        chart.setWebLineWidth(webLineWidth);
        chart.invalidate();
    }

    @ReactProp(name="webLineWidthInner",defaultFloat=0.3f)
    public void setWebLineWidthInner(RadarChart chart,float webLineWidthInner){
        chart.setWebLineWidthInner(webLineWidthInner);
        chart.invalidate();
    }

    @ReactProp(name="webAlpha",defaultInt=0)
    public void setWebAlpha(RadarChart chart,int webAlpha){
        chart.setWebAlpha(webAlpha);
        chart.invalidate();
    }

    @ReactProp(name="webColor",defaultInt=1)
    public void setWebColor(RadarChart chart, int webColor){
        chart.setWebColor(webColor);
        chart.invalidate();
    }

    @ReactProp(name="webColorInner",defaultInt=1)
    public void setWebColorInner(RadarChart chart,int webColorInner){
        chart.setWebColorInner(webColorInner);
        chart.invalidate();
    }

    @ReactProp(name="drawWeb",defaultBoolean=false)
    public void setDrawWeb(RadarChart chart,boolean drawWeb){
        chart.setDrawWeb(drawWeb);
        chart.invalidate();
    }

    @ReactProp(name="skipWebLineCount",defaultInt=0)
    public void setSkipWebLineCount(RadarChart chart,int skipWebLineCount){
        chart.setSkipWebLineCount(skipWebLineCount);
        chart.invalidate();
    }

    @ReactProp(name="data")
    public void setData(RadarChart chart,ReadableMap rm){

        String name=rm.getString("name");
        ReadableArray mdata=rm.getArray("data");
        ReadableArray parties=rm.getArray("parties");
        ArrayList<Entry> yVals = new ArrayList<Entry>();
        String[] mParties = new String[parties.size()];
        // Adding Parties String
        if(mdata.size()!=parties.size()){
            
        }
        for (int m=0;m<parties.size();m++){
            mParties[m]=parties.getString(m);
        }
        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < parties.size(); i++)
            xVals.add(mParties[i % mParties.length]);

        // Adding Data
        for (int n=0;n<mdata.size();n++){
            yVals.add(new Entry((float)mdata.getDouble(n),n));
        }


        RadarDataSet set = new RadarDataSet(yVals,name);
        set.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set.setDrawFilled(true);
        set.setLineWidth(2f);
        set.setDrawValues(false);
        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set);
        
        RadarData data = new RadarData(xVals, sets);
        data.setValueTextSize(8f);
        data.setDrawValues(true);
        chart.getYAxis().setEnabled(false); 
        chart.setData(data);

        chart.invalidate();


        /*
        float mult = 150;
        int cnt = 9;
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Entry> yVals2 = new ArrayList<Entry>();
        String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I"};
        // IMPORTANT: In a PieChart, no values (Entry) should have the same
        // xIndex (even if from different DataSets), since no values can be
        // drawn above each other.
        for (int i = 0; i < cnt; i++) {
            yVals1.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }

        for (int i = 0; i < cnt; i++) {
            yVals2.add(new Entry((float) (Math.random() * mult) + mult / 2, i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < cnt; i++)
            xVals.add(mParties[i % mParties.length]);

        RadarDataSet set1 = new RadarDataSet(yVals1, "Set 1");
        set1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setFillColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawFilled(true);
        set1.setLineWidth(2f);

        RadarDataSet set2 = new RadarDataSet(yVals2, "Set 2");
        set2.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setFillColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        set2.setDrawFilled(true);
        set2.setLineWidth(2f);

        ArrayList<IRadarDataSet> sets = new ArrayList<IRadarDataSet>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(xVals, sets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);

        chart.setData(data);

        chart.invalidate();
        */
    }
}
