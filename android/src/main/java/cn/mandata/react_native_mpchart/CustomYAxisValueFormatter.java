package cn.mandata.react_native_mpchart;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;

/**
 * Created by Administrator on 2015/11/7.
 */
public class CustomYAxisValueFormatter implements YAxisValueFormatter {
   private String FormAt="";
   public   CustomYAxisValueFormatter(String formAt){
       this.FormAt=formAt;
   }
    @Override
    public String getFormattedValue(float v, YAxis yAxis) {
        return String.format(FormAt,v);
    }
}
