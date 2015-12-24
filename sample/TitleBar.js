/* @flow */
'use strict';

var React = require('react-native');
var Button=require('./Button');
var {
  StyleSheet,
  View,
  NativeAppEventEmitter
} = React;

var Component = React.createClass({
	onBack:function (argument) {		
		 NativeAppEventEmitter.emit('back');
	},
	render: function() {
		return (
		  <View style={styles.container}>
		  	<Button text={"返回"} style={styles.button} onPress={this.onBack}/>
		  </View>
		);
	}
});


var styles = StyleSheet.create({
	container:{
		height:50,
		justifyContent:'flex-start',
		borderBottomWidth:1,
		borderBottomColor:'#e5e5e5'
	},
	button:{
		width:80
	}
});


module.exports = Component;
