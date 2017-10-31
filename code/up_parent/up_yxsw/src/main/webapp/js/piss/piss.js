function  initChart (){
	
	 var myChart = echarts.init(document.getElementById('main1'));

     // 指定图表的配置项和数据
     var option = {
         title: {
             text: 'ECharts 入门示例'
         },
         tooltip : {
				trigger: 'axis'
				 //trigger: 'item',
				 //formatter: "{a} <br/> {c} {d}%"
			},
         toolbox: {
             left: 'right',
             feature: {
                 dataZoom: {
                     yAxisIndex: 'none'
                 },
                 restore: {},
                 saveAsImage: {}
             }
         },
        
        /* legend: {
             data:['销量']
         },*/
         
       /*  legend: {
				data:['总量','完成数','按时完成率'],
			},*/
         legend: {
				data:['量1','hh'],
			},
         xAxis: {
        	 
        	
             data: ['衬衫','羊毛衫',"雪纺衫","裤子","高跟鞋",'袜子', ],
             axisTick: {
                 alignWithLabel: true
             }	
        
         },
         yAxis: {},
         series: [{
             name: '量1',
             type: 'bar',
             data: [5, 20, 36, 10, 10, 20]
         }]
     };

     // 使用刚指定的配置项和数据显示图表。
     myChart.setOption(option);
	
}