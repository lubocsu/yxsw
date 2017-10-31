
function initChart(chartMap){
	
	var myChart = echarts.init(document.getElementById('main1'));
	
	option = {
			title : {
		        text: '人员巡检情况统计',
//		        subtext: '纯属虚构',
		    },
			tooltip : {
				trigger: 'axis'
				 //trigger: 'item',
				 //formatter: "{a} <br/> {c} {d}%"
			},
			legend: {
				data:['总量','完成数','按时完成率'],
			},
			/*toolbox: {
				show : true,
				feature : {
					magicType : {show: false, type: ['line', 'bar']},
//					restore : {show: false},
					saveAsImage : {show: true}
				},
				right:18
			},*/
			
			  toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataZoom : {show: true},
			            dataView : {show: true, readOnly: false},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			calculable : true,
			xAxis : [
			         {
			        	 type : 'category',
				         data : chartMap.xAxis
			         }
			        ],
	        yAxis : [
	                  {
	                      type: 'value',
	                      name: '总量',
	                      axisLabel: {
	                          formatter: '{value} 个'
	                      }
	                  },
	                  
	               /*   {
	                      type: 'value',
	                      name: '完成数',
	                      axisLabel: {
	                          formatter: '{value} 个'
	                      }
	                  },
	                  */
	                  
	                  {
	                      type: 'value',
	                      name: '按时完成率',
	                      min: 0,
	                      max: 100,
	                      interval: 10,
	                      axisLabel: {
	                          formatter: '{value} %'
	                      }
	                  }
	                  ],
           series : makeSeries1(chartMap)
	};
	
	myChart.setOption(option)
}

function makeSeries1(chartMap){
	var series = [];
	series.push({
		name:"总量",
    	type:'bar',
    	data:chartMap.dates[0]
	})
	
	series.push({
		name:"完成数",
    	type:'bar',
    	data:chartMap.dates[1]	
		
	})
	series.push({
		name:"按时完成率",
    	type:'line',
    	yAxisIndex: 1,
    	data:chartMap.dates[2]
	})
	
	
	return series;
}
