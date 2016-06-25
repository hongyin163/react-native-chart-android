package cn.mandata.react_native_mpchart;

import android.view.MotionEvent;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;


/*
 * implement OnChartValueSelectedListener interface to bridge selection callback
 * events support
 * use eventName 'topSelect' mapping to 'onSelect' callback prop in JS
 */
public class MPChartSelectionEventListener implements OnChartValueSelectedListener {
    private BarLineChartBase chart=null;
    public  MPChartSelectionEventListener(){

    }
    public  MPChartSelectionEventListener(BarLineChartBase chart){
        this.chart=chart;

        // bind selection callback listener to chart
        this.chart.setOnChartValueSelectedListener(this);
    }

    @Override
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        WritableMap event = Arguments.createMap();
        event.putInt("xIndex", e.getXIndex());
        event.putDouble("yValue", e.getVal());

        ThemedReactContext reactContext = (ThemedReactContext)this.chart.getContext();

        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
            this.chart.getId(),
            "topSelect",
            event
        );
    }

    @Override
    public void onNothingSelected() {
        WritableMap event = Arguments.createMap();

        // pass xIndex as -1 for representing no selection
        event.putInt("xIndex", -1);

        ThemedReactContext reactContext = (ThemedReactContext)this.chart.getContext();

        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
            this.chart.getId(),
            "topSelect",
            event
        );
    }
}
