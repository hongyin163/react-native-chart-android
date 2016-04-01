import React,{ requireNativeComponent, Component, PropTypes, View } from 'react-native';

class CandleStickChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <MPCandleStickChart {...this.props}/>
        );
    }
}

CandleStickChart.propTypes = {
    ...View.propTypes,
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
    legend:PropTypes.object
}

var MPCandleStickChart = requireNativeComponent('MPCandleStickChart', CandleStickChart);

export default CandleStickChart;