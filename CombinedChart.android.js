import React,{ requireNativeComponent, Component, PropTypes, View } from 'react-native';

class CombinedChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let chartData={};
        let children=this.props.children;
        if(children.length){
            for (var i = 0; i < children.length; i++) {
                var child=children[i]
                chartData[child.props.chartType]=child.props.data;
            }
        }else{
            chartData[children.props.chartType]=children.props.data;
        }
        let {
            style,
            data,
            ...other
            }=this.props;
        return (
            <MPCombinedChart
                style={this.props.style}
                {...other}
                data={chartData}/>
        );
    }
}

CombinedChart.propTypes = {
    data:PropTypes.object,
    touchEnabled:PropTypes.bool,
    dragEnabled:PropTypes.bool,
    scaleEnabled:PropTypes.bool,
    scaleXEnabled:PropTypes.bool,
    scaleYEnabled:PropTypes.bool,
    pinchZoom:PropTypes.bool,
    doubleTapToZoomEnabled:PropTypes.bool,
    highlightPerDragEnabled:PropTypes.bool,
    highlightPerTapEnabled:PropTypes.bool,
    dragDecelerationEnabled:PropTypes.bool,
    dragDecelerationFrictionCoef:PropTypes.number,
    maxVisibleValueCount:PropTypes.number,
    limitLine:PropTypes.object,
    description:PropTypes.string,
    backgroundColor:PropTypes.string,
    drawGridBackground:PropTypes.bool,
    gridBackgroundColor:PropTypes.string,
    visibleXRange:PropTypes.array,
    borderColor:PropTypes.string,
    borderWidth:PropTypes.number,
    xAxis:PropTypes.object,
    yAxisLeft:PropTypes.object,
    yAxisRight:PropTypes.object,
    yAxis:PropTypes.object,
    fitScreen:PropTypes.bool,
    chartPadding:PropTypes.string,
    legend:PropTypes.object,
    scaleX: PropTypes.number,
    scaleY: PropTypes.number,
    translateX: PropTypes.number,
    translateY: PropTypes.number,
    rotation: PropTypes.number,
    renderToHardwareTextureAndroid: React.PropTypes.bool,
    onLayout: React.PropTypes.bool,
    accessibilityLiveRegion: React.PropTypes.string,
    accessibilityComponentType: React.PropTypes.string,
    importantForAccessibility: React.PropTypes.string,
    accessibilityLabel: React.PropTypes.string,
    testID: React.PropTypes.string
};

class chart extends Component {
    constructor(props) {
        super(props);
    }
}
chart.propTypes = {
    chartType:PropTypes.string,
    data:PropTypes.object
};
CombinedChart.Chart=chart;

// RIGHT_OF_CHART,
//  RIGHT_OF_CHART_CENTER,
//  RIGHT_OF_CHART_INSIDE,
//  LEFT_OF_CHART,
//  LEFT_OF_CHART_CENTER,
//  LEFT_OF_CHART_INSIDE,
//  BELOW_CHART_LEFT,
//  BELOW_CHART_RIGHT,
//  BELOW_CHART_CENTER,
//  ABOVE_CHART_LEFT,
//  ABOVE_CHART_RIGHT,
//  ABOVE_CHART_CENTER,
//  PIECHART_CENTER;

var MPCombinedChart = requireNativeComponent('MPCombinedChart', CombinedChart);

export default CombinedChart;
