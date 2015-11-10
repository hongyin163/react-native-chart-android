var React=require('react-native');
var { 
	requireNativeComponent,
	PropTypes 
} = React;

var iface = {
  name: 'CombinedChart',
  propTypes: {
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
	borderColor:PropTypes.string,
	borderWidth:PropTypes.number,
	xAxis:PropTypes.object,
	yAxisLeft:PropTypes.object,
	yAxisRight:PropTypes.object,
	yAxis:PropTypes.object,
	scaleX: PropTypes.number,
	scaleY: PropTypes.number,
	translateX: PropTypes.number,
	translateY: PropTypes.number,
	rotation: PropTypes.number,
  },
};

var  MPCombinedChart= requireNativeComponent('MPCombinedChart', iface);


var CombinedChart = React.createClass({	
  render: function() {
  	var data={};
  	var children=this.props.children;
    if(children.length){
      for (var i = 0; i < children.length; i++) {
        var child=children[i]

        data[child.props.chartType]=child.props.data;

      }
    }else{
       data[children.props.chartType]=children.props.data;
    }

    return (
      <MPCombinedChart 
        style={this.props.style}  
        maxVisibleValueCount={10} 
        xAxis={{DrawGridLines:false,GridLineWidth:0}}
        yAxisRight={{Enable:false}} 
        yAxis={{StartAtZero:false,DrawGridLines:false}}
        drawGridBackground={false}
        backgroundColor={"WHITE"} 
        data={data}/>
    );
  }
});

var chart =React.Component ({
  propTypes:{
    chartType:PropTypes.string,
    data:PropTypes.object,
  }
})


CombinedChart.Chart=chart;

module.exports = CombinedChart;
