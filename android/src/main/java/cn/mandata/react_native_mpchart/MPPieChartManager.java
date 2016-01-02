package cn.mandata.react_native_mpchart;

import com.facebook.react.uimanager.ThemedReactContext;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.charts.PieRadarChartBase;

/**
 * Created by Administrator on 2015/12/24.
 */
public class MPPieChartManager extends MPPieRadarChartManager {
    private String CLASS_NAME="MPPieChart";

    @Override
    public String getName() {
        return this.CLASS_NAME;
    }
    @Override
    protected PieChart createViewInstance(ThemedReactContext reactContext) {
        PieChart chart= new PieChart(reactContext);
        chart.setOnChartGestureListener(new MPChartEventListener());
        return chart;
    }
}
