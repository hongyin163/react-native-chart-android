package cn.mandata.react_native_mpchart;

import android.support.annotation.Nullable;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by Administrator on 2015/11/6.
 */
public class ChartViewManager  extends SimpleViewManager<ChartView> {
    public static final String REACT_CLASS = "MPChart";
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @Override
    protected ChartView createViewInstance(ThemedReactContext reactContext) {
        ChartView chart= new ChartView(reactContext);
        chart.setExampleDimension(50);
        return  chart;
    }

    @ReactProp(name = "data")
    public void setData(ChartView view, @Nullable String text) {
        view.setData(text);
    }

}
