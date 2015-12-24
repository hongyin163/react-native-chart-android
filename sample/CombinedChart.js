/* @flow */
'use strict';

var React = require('react-native');
var TitleBar=require('./TitleBar');
var {
	CombinedChart
}=require('../index.android');
var {
  StyleSheet,
  View,
  Text
} = React;

var Component = React.createClass({
	getData:function (argument) {
		var data={
			xValues:['1','2','3'],
			yValues:[
				{
					data:[4.0,5.0,6.0],
					label:'test1',
					config:{
						color:'blue'
					}
				}
			]
		};	
		return data;
	},
	getRandomData:function (argument) {
		var data={};
		data['xValues']=[];
		data['yValues']=[
			{
				data:[],
				label:'test1',
				config:{
					color:'blue'
				}
			}
		];
		for (var i = 0; i < 500; i++) {
			data.xValues.push(i+'');
			data.yValues[0].data.push(Math.random()*100);
		};
		return data;
	},
	render: function() {
		return (
			<View style={styles.container}>
				<TitleBar/>
				<View style={styles.chartContainer}>					
					<CombinedChart 
						style={{flex:1}} 
						data={this.getRandomData()}
						visibleXRange={[0,30]}
						maxVisibleValueCount={50} 
				        xAxis={{drawGridLines:false,gridLineWidth:1,position:"BOTTOM"}}
				        yAxisRight={{enable:false}} 
				        yAxis={{startAtZero:false,drawGridLines:false,position:"INSIDE_CHART"}}
				        drawGridBackground={false}
				        backgroundColor={"WHITE"} 
				        description={"测试"}
				        legend={{enable:true,position:'ABOVE_CHART_LEFT',direction:"LEFT_TO_RIGHT"}}>					        
							<CombinedChart.Chart chartType={"line"} data={this.getRandomData()} />		
							<CombinedChart.Chart chartType={"bar"} data={this.getRandomData()} />				
				    </CombinedChart>
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
