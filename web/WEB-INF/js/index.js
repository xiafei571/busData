function initPage() {
	console.log("Hello world");
}

function demo_chart_1() {
	var myChart = echarts.init(document.getElementById('all_dep'));

	myChart.setOption({
		title : {
			text : '出発: DEPARTURED(実績日時) - DEPARTURE(予定日時)',
			left : 10
		},
		toolbox : {
			feature : {
				dataZoom : {
					yAxisIndex : false
				},
				saveAsImage : {
					pixelRatio : 2
				}
			}
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'shadow'
			}
		},
		grid : {
			bottom : 90
		},
		dataZoom : [ {
			type : 'inside'
		}, {
			type : 'slider'
		} ],
		xAxis : {
			data : [],
			silent : false,
			splitLine : {
				show : false
			},
			splitArea : {
				show : false
			}
		},
		yAxis : {
			name : 'Time(min)',
			splitArea : {
				show : false
			}
		},
		series : [ {
			type : 'bar',
			data : [],
			// Set `large` for large data amount
			large : true
		} ]
	});

	$.ajax({
		url : "allOfDep",
		success : function(result) {

			myChart.setOption({
				xAxis : {
					data : result.res.dataList
				},
				series : [ {
					data : result.res.valueList
				} ]
			});
		}
	});

}

function demo_chart_2() {
	var myChart = echarts.init(document.getElementById('all_arr'));

	myChart.setOption({
		title : {
			text : '到着: DEPARTURED(実績日時) - DEPARTURE(予定日時)',
			left : 10
		},
		toolbox : {
			feature : {
				dataZoom : {
					yAxisIndex : false
				},
				saveAsImage : {
					pixelRatio : 2
				}
			}
		},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'shadow'
			}
		},
		grid : {
			bottom : 90
		},
		dataZoom : [ {
			type : 'inside'
		}, {
			type : 'slider'
		} ],
		xAxis : {
			data : [],
			silent : false,
			splitLine : {
				show : false
			},
			splitArea : {
				show : false
			}
		},
		yAxis : {
			name : 'Time(min)',
			splitArea : {
				show : false
			}
		},
		series : [ {
			type : 'bar',
			data : [],
			// Set `large` for large data amount
			large : true
		} ]
	});

	$.ajax({
		url : "allOfArr",
		success : function(result) {

			myChart.setOption({
				xAxis : {
					data : result.res.dataList
				},
				series : [ {
					data : result.res.valueList
				} ]
			});
		}
	});

}

function demo_chart_3() {
	// 基于准备好的dom，初始化echarts实例
	var myChart1 = echarts.init(document.getElementById('avg_hour'));
	myChart1.setOption({

		// Make gradient line here

		title : [ {
			left : 'center',
			text : '出発: AVG(実績日時 - 予定日時) / hour'
		}, {
			top : '55%',
			left : 'center',
			text : '到着: AVG(実績日時 - 予定日時) / hour'
		} ],
		tooltip : {
			trigger : 'axis'
		},
		xAxis : [ {
			data : [],
			axisLabel : {
				formatter : '{value}時'
			}
		}, {
			data : [],
			gridIndex : 1,
			axisLabel : {
				formatter : '{value}時'
			}
		} ],
		yAxis : [ {
			name : 'Time(s)',
			splitLine : {
				show : false
			}
		}, {
			name : 'Time(s)',
			splitLine : {
				show : false
			},
			gridIndex : 1
		} ],
		grid : [ {
			bottom : '60%'
		}, {
			top : '60%'
		} ],
		series : [ {
			type : 'line',
			showSymbol : false,
			data : []
		}, {
			type : 'line',
			showSymbol : false,
			data : [],
			xAxisIndex : 1,
			yAxisIndex : 1
		} ]
	});
	$.ajax({
		url : "avgOfDepByHour",
		success : function(result) {
			// 填入数据
			myChart1.setOption({
				visualMap : [ {
					show : false,
					type : 'continuous',
					seriesIndex : 0,
					min : 0,
					max : 400
				}, {
					show : false,
					type : 'continuous',
					seriesIndex : 1,
					dimension : 0,
					min : 0,
					max : result.res.dataList2.length - 1
				} ],
				xAxis : [ {
					data : result.res.dataList1
				}, {
					data : result.res.dataList2,
					gridIndex : 1
				} ],
				series : [ {
					type : 'line',
					showSymbol : false,
					data : result.res.valueList1
				}, {
					type : 'line',
					showSymbol : false,
					data : result.res.valueList2,
					xAxisIndex : 1,
					yAxisIndex : 1
				} ]
			});
		}
	});

}