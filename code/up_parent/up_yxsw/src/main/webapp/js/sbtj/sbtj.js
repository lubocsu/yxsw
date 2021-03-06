
function initChart(chartMap){
	console.log(chartMap)
	console.log(chartMap.legend[0])
	console.log(chartMap.xAxis)
	
	var myChart = echarts.init(document.getElementById('main1'));
	
	option = {
			title : {
		        text: '设备统计',
//		        subtext: '纯属虚构',
		    },
			tooltip : {
				trigger: 'axis'
				 //trigger: 'item',
				 //formatter: "{a} <br/> {c} {d}%"
			},
			/*legend: {
				data:['数量','总量','完成数'],
			},*/
			
			legend:{
			data:chartMap.legend[0]
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
			        
		/*	dataZoom :[
			          {
				       type: 'slider',
			           show:true,
			           realtime: true
			           }
			          ] ,*/
			        
			    /*    {"xAxis":["永川污水厂"],"legend":[["过滤器","机泵",null,"风机"]],"dates":[[1,3,0,1]]}*/
	        yAxis : [
	                  {
	                      type: 'value',
	                      name: '数量',
	                      axisLabel: {
	                          formatter: '{value} 个'
	                      }
	                  },
	                  
	           
	                  ],
           series : makeSeries(chartMap)
	};
	
	myChart.setOption(option)
}


function makeSeries(chartMap){
	var series = [];
	var color=['#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3']
	for( var i =0;i<chartMap.dates.length;i++){
		
		series.push({
		
			name:chartMap.legend[0][i],
	    	type:'bar',
	    	//barGap: 6,
	    	barWidth: 10,
	    	//barCategoryGap: '100%',
	    	data:chartMap.dates[i],
	    	itemStyle:{
	    	       normal:{
	    	         color:color[i],
	    	         }
	    	  }
		})
	}
	return series

}
