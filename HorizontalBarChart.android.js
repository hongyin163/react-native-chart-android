import React, {Component, PropTypes} from 'react';
import {requireNativeComponent, View} from 'react-native';
import BarChart from './BarChart';

class HorizontalBarChart extends BarChart {
    render() {
        return (
            <MPHorizontalBarChart {...this.props}/>
        );
    }
}

var MPHorizontalBarChart = requireNativeComponent('MPHorizontalBarChart', HorizontalBarChart);

export default HorizontalBarChart;
