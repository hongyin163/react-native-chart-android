# react-native-mpchart
react-native-mpchart provide modules to add chart to android,all charts are come from mpandroidchart library,include bar chart ,line chart,combine chart etc.


### Installation

```
npm install react-native-chart-android --save
```

### Add it to your android project

* In `android/setting.gradle`

```gradle
include ':react-native-mpchart'
project(':react-native-mpchart').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-chart-android/android')
```

* In `android/app/build.gradle`

```gradle
...
dependencies {
  ...
  compile project(':react-native-mpchart')
}
```

* Register Module (in MainActivity.java)

```java
import cn.mandata.react_native_mpchart.MPChartPackage;  // <--- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new MPChartPackage()) // <------ add this line to yout MainActivity class
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "AndroidRNSample", null);

    setContentView(mReactRootView);
  }

  ......

}
```

## Example
```javascript
var React = require('react-native');
var { StyleSheet } = React;



var styles = StyleSheet.create({
  containerWebView: {
    flex: 1,
  }
});
```

## License
MIT