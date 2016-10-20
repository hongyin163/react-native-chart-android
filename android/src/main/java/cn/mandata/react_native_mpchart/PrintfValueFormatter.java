package cn.mandata.react_native_mpchart;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import android.util.Log;

public class PrintfValueFormatter implements ValueFormatter {

  private String format = "";

  public PrintfValueFormatter(String format){
    if(format != null) this.format = format;
  }

  @Override
  public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
    return String.format(format, value);
  }
}
