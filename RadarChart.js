import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent, View} from 'react-native';

class RadarChart extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <MPRadarChart {...this.props}/>
        );
    }
}

RadarChart.propTypes = {
    ...View.propTypes,
    data:PropTypes.object,
    webLineWidth:PropTypes.number,
    webLineWidthInner:PropTypes.number,
    webAlpha:PropTypes.number,
    webColor:PropTypes.number,
    webColorInner:PropTypes.number,
    drawWeb:PropTypes.bool,
    dragEnabled:PropTypes.bool,
    legend:PropTypes.object,
    touchEnabled:PropTypes.bool,
    dragDecelerationEnabled:PropTypes.bool,
    dragDecelerationFrictionCoef:PropTypes.number,
    description:PropTypes.string,
    chartPadding:PropTypes.string,
    highlightPerDragEnabled:PropTypes.bool,
    highlightPerTapEnabled:PropTypes.bool,
    skipWebLineCount:PropTypes.number,
}

var MPRadarChart = requireNativeComponent('MPRadarChart', RadarChart);

export default RadarChart;
