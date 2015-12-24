/* @flow */
'use strict';

var React = require('react-native');
var BarChart=require('./BarChart');
var LineChart=require('./LineChart');
var CandleChart=require('./CandleChart');
var CombinedChart=require('./CombinedChart');
var Button=require('./Button');
var {
  StyleSheet,
  View,
  TouchableOpacity,
  Text,
  Navigator,
  NativeAppEventEmitter
} = React;




var Component = React.createClass({
	onBack:function (argument) {
    	this._nav.pop();
  	},
	componentWillMount: function() {
		var me=this;
		NativeAppEventEmitter.addListener('back', this.onBack);
	},
	renderScene:function (route, navigator) {
		switch(route.name){
			case "main":
				return(
					<View style={styles.container}>
						<Button text={'Bar Chart'} onPress={this.bindSelect('bar')}/>
						<Button text={'Line Chart'} onPress={this.bindSelect('line')}/>
						<Button text={'CandleStick Chart'} onPress={this.bindSelect('candle')}/>
						<Button text={'Combined Chart'} onPress={this.bindSelect('combined')}/>
					</View>
	            );
			break;
			case "bar":
				return (<BarChart nav={navigator}/>)
			break;
			case "line":
				return (<LineChart nav={navigator}/>)
			break;
			case "candle":
				return (<CandleChart nav={navigator}/>)
			break;
			case "combined":
				return (<CombinedChart nav={navigator}/>)
			break;
		}
	},
	bindSelect:function (name) {
		var me=this;
		return function(){
			me.onSelect(name);
		};
	},
	onSelect:function (name) {
		this._nav.push({name:name})
	},
  	render: function() {
	    return (
	    	<Navigator
	            ref={(n)=>this._nav=n}
	            debugOverlay={false}
	            style={{flex:1}}
	            configureScene={(route) =>Navigator.SceneConfigs.PushFromRight}
	            initialRoute={{name:'main'}}
	            renderScene={this.renderScene}/>

	    );
  	}
});


var styles = StyleSheet.create({
	container:{
		flex:1
	}
});


module.exports = Component;
