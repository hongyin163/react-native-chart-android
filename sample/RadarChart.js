/* @flow */
'use strict';

var React = require('react-native');
var TitleBar=require('./TitleBar');
var {
	BarChart,
	CombinedChart
}=require('../index.android');
var {
  StyleSheet,
  View,
  Text
} = React;

var Component = React.createClass({
	getRadarData:function (argument) {
	    var radarData={
			name:'2016-11-4',
			data:[0.5,0.6,0.7,0.4,0.55],
			parties:['Sugar','Carl','Vitamin','Water','Others'],
		};	
		return radarData;
	},
	render: function() {
		return (
			<View style={styles.container}>
				<View style={styles.chartContainer}>
					<RadarChart 
					style={{flex:1}}
					data={this.getLineData()}
					webLineWidth={1}
					/>
				</View>
			</View>
		);
	}
});

var styles = StyleSheet.create({
	container:{
		flex:1
	},
	chartContainer:{
		flex:1
	},
	chart:{
		flex:1
	}
});

module.exports = Component;
