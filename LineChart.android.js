
var { requireNativeComponent,PropTypes } = require('react-native');

var iface = {
  name: 'LineChart',
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

module.exports = requireNativeComponent('MPLineChart', iface);