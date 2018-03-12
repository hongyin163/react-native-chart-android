import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent, View} from 'react-native';

class PieChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <MPPieChart {...this.props}/>
        );
    }
}

PieChart.propTypes = {
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
    borderColor:PropTypes.string,
    borderWidth:PropTypes.number,
    chartPadding:PropTypes.string,
    legend:PropTypes.object,
    holeRadius: PropTypes.number,
    transparentCircleRadius: PropTypes.number,
    drawSliceText: PropTypes.bool,
    usePercentValues: PropTypes.bool,
    centerText: PropTypes.string,
    centerTextRadiusPercent: PropTypes.number
}

var MPPieChart = requireNativeComponent('MPPieChart', PieChart);

export default PieChart;
