require(["esri/map","dojo/on","esri/geometry/Extent","esri/layers/ArcGISTiledMapServiceLayer","esri/toolbars/navigation",
         "esri/layers/GraphicsLayer","esri/layers/FeatureLayer","esri/toolbars/edit","esri/toolbars/draw",
         "esri/symbols/PictureMarkerSymbol","esri/tasks/GeometryService","esri/tasks/LengthsParameters",
         "esri/tasks/AreasAndLengthsParameters","esri/graphic","esri/dijit/InfoWindow","esri/dijit/BasemapLayer",
         "esri/dijit/BasemapGallery","esri/dijit/Basemap","dojox/NodeList/delegate","dojo/domReady!"]);
/**
 * 自定义地图对象(需要jquery支持)
 * 参数 div
 */
function EasyMap(){
	
	/*全局底图存放位置*/
	this.baseLayers = {};
	/*全局注册图层*/
	this.signInLayers = {};
	
	/*服务地址仓库*/
	this.serviceUrl = {
		//市级平台经典地图
		OnlineURL : "http://www.digitalcq.com/RemoteRest/services/CQMap_VEC/MapServer",
		geometryService :"http://sampleserver6.arcgisonline.com/arcgis/rest/services/Utilities/Geometry/GeometryServer"
	};
	
	//全局画图事件对象（处理重复绑定问题）
	this.drawHandle = null;
	this.drawType = {
			POINT:esri.toolbars.Draw.POINT,
			CIRCLE:esri.toolbars.Draw.CIRCLE,
			LINE:esri.toolbars.Draw.LINE,
			POLYGON:esri.toolbars.Draw.POLYGON,
			POLYLINE:esri.toolbars.Draw.POLYLINE,
			FREEHAND_POLYLINE:esri.toolbars.Draw.FREEHAND_POLYLINE,
			FREEHAND_POLYGON:esri.toolbars.Draw.FREEHAND_POLYGON
	};
	
	//全局编辑事件对象（处理重复绑定问题）
	this.editHandle = null;
	this.editType = {
			MOVE:esri.toolbars.Edit.MOVE,
			EDIT_VERTICES:esri.toolbars.Edit.EDIT_VERTICES,
			MOVE_EDIT_VERTICES:esri.toolbars.Edit.MOVE | esri.toolbars.Edit.EDIT_VERTICES
	};
	
	//范围集合
	this.extents = {};
	
	/**
	 * 地图属性初始化
	 * divId 容器id 必填参数
	 * config {url：url, option:option}
	 * url，可选， 为底图服务地址，可以是String，也可以是function
	 * option，可选，为地图初始化参数，参考官方API
	 * onLoadFunc 可选，为onload之后调用的方法
	 * debug,可选，为true时启动调试模式
	 * 返回 原生map对象
	 */
	this.initMap = function(divId, config){
		var map = null;
		//地图初始化
		if(config && config.options){
			map = new esri.Map(divId, config.options);
		}else{
			map = new esri.Map(divId, {logo : false, autoResize : true,isZoomSlider:false});
		}
		
		//画图工具初始化
		map.drawBrush = new esri.toolbars.Draw(map, {showTooltips:true});
		//编辑工具初始化
		map.editBrush = new esri.toolbars.Edit(map);
		//导航工具初始化
		map.navigation = new esri.toolbars.Navigation(map);
		
		//底图初始化
		if(config && config.url){
			this.initBaseLayer(map, divId, config.url);
		}else{
			this.initBaseLayer(map, divId);
		}
		
		/*
		 * 载入中提示信息
		 */
		if(config && config.loading){
			var mapDiv = jQuery("#"+divId);
			var loadHtml = '<span id="loading" style="width:70px;background-color: rgb(235, 164, 58); padding: 0px 10px;display:none; position: absolute; z-index: 999; left: 400px; top: 200px;">载入中...</span>';
			mapDiv.append(loadHtml);
			//如果mapdiv不是定位元素则 包裹一个span用于定位
			var position = mapDiv.css("position");
			if(position != "absolute" && position != "relative"){
				mapDiv.wrap("<span style=\"position: relative;\"></span>");
			}
			var loadSpan = jQuery("#"+divId+" #loading");
			//定位
			var left = (mapDiv.width()-loadSpan.width())/2;
			var top = (mapDiv.height()-loadSpan.height())/2;
			loadSpan.css({
				left:left,
				top:top
			});
			dojo.connect(map,"onUpdateStart",function(){
				//再次定位 显示
				var left = (mapDiv.width()-loadSpan.width())/2;
				var top = (mapDiv.height()-loadSpan.height())/2;
				loadSpan.css({
					left:left,
					top:top,
					display:"inline-block"
				});
			});
			dojo.connect(map,"onUpdateEnd",function(){
				var loadSpan = jQuery("#"+divId+" #loading");
				loadSpan.css("display","none");
			});
		}
		
		//绑定load事件
		var that = this;
		dojo.connect(map,"onLoad",function(map){
			//随窗口变化而变化
			dojo.connect(window, "resize", function(){
				map.resize();
			});
			
			//onLoad 回调方法
			if(config && config.onLoadFunc){
				config.onLoadFunc(map);
			}
			
			//onLoad 调试模式
			if(config && config.debug){
				that.debug(map);
			}
		});
		
		//初始化回调函数
		if(config && config.onInitFunc){
			config.onInitFunc(map);
		}
		return map;
	};
	
	/**
	 * 加载底图
	 * 默认使用市级平台经典地图
	 * 接收String服务地址、function方法获得地图对象
	 */
	this.initBaseLayer = function(map, divId, onlineURL){
		var baseLayer = null;
		//底图（缓冲图层）
		if(typeof onlineURL == "string"){
			baseLayer = new esri.layers.ArcGISTiledMapServiceLayer(onlineURL);
		}else if(typeof onlineURL == "function"){
			baseLayer = onlineURL();
		}else{
			baseLayer = new esri.layers.ArcGISTiledMapServiceLayer(this.serviceUrl.OnlineURL);
		}
		this.baseLayers[divId] = baseLayer;
		map.addLayer(baseLayer);
	};
	
	/*
	 * 返回当前底图对象
	 * divId 为创建地图时的容器id 
	 */
	this.getBaseLayer = function(divId){
		return this.baseLayers[divId].url;
	};
	
	/**
	 * 地图取点功能
	 * 参数trigger 为触发对象,可以使dom对象，也可以是对象id
	 */
	this.pickPoint = function(trigger, map, callback){
		var btn;
		//判断传参类型
		if(dojo.isString(trigger)){
			btn = dojo.byId(trigger);
		}else if(dojo.isObject(trigger)){
			btn = trigger;
		}else{
			return;
		}
		var that = this;
		dojo.connect(btn, "onclick", function(){
			var tb = that.draw;
			tb.activate(esri.toolbars.Draw.POINT);
			dojo.connect(tb,"onDrawEnd",function(mapPoint){
				if(callback){
					callback(map, mapPoint);
				}else{
					that.showPoint(mapPoint);
				}
				//添加完，取消功能
				tb.deactivate();
			});
		});
	};
	
	
	/**
	 * 默认的取点回调函数，传入回调参数时将被替换，也可以独立使用
	 * 参数 mapPoint 可以传入point对象，也可以传入坐标数组如[-122.65, 45.53]
	 * 参数 symbol 为自定义symbol
	 */
	this.showPoint = function(mapPoint, map, defineSymbol){
		var graphic;
		var point;
		var symbol = new esri.symbol.SimpleMarkerSymbol();
		//判断是否传入了自定义的symbol
		if(defineSymbol){
			symbol = defineSymbol;
		}
		//判断是否传入的是坐标
		if(dojo.isArray(mapPoint)){
			point = new esri.geometry.Point(mapPoint, map.spatialReference);
			graphic = new esri.Graphic(point, symbol);
			dojo.connect(map, "onLoad", function(map){
				map.graphics.add(graphic);
			});
			return;
		}
		point = mapPoint;
		graphic = new esri.Graphic(point, symbol);
		map.graphics.add(graphic);
	};
	
	/**
	 * 开启地图的debug模式
	 * 控制台打印信息【地图范围、鼠标坐标】
	 */
	this.debug = function(map){
		//给地图控件添加视图变化监听事件
		dojo.connect(map, "onExtentChange", function(extent){
			var extentInfo = "地图范围：XMin:" + extent.xmin+" YMin:"+extent.ymin+" XMax:"+extent.xmax +" YMax:"+extent.ymax;
			var extentJson = extent.toJson();
			//检查是否有控制台，防止报错
			if (window.console){
			    console.log(extentInfo);
			    console.log(JSON.stringify(extentJson));
			}
		});
		//给地图控件添加载鼠标单击事件
		dojo.connect(map, "onClick", function(event){
			var mp = event.mapPoint;
			var mp2 = event.screenPoint;
			var mouseInfo ="地理坐标："+mp.x+","+mp.y+"----屏幕坐标："+mp2.x+","+mp2.y;
			//检查是否有控制台，防止报错
			if (window.console){
			    console.log(mouseInfo);
			}
		});
		//监控缩放等级
		dojo.connect(map, "onZoomEnd", function(extent, zoomFactor, anchor, level){
			var extentInfo = "地图范围：XMin:" + extent.xmin+" YMin:"+extent.ymin+" XMax:"+extent.xmax +" YMax:"+extent.ymax;
			//检查是否有控制台，防止报错
			if (window.console){
			    console.log(extentInfo);
			    console.log("level:" + level + "	scale:" + map.getScale());
			}
		});
		
	};
	
	/*
	 * 地图定位
	 * obj 可以使坐标数组，点，线，面对象
	 */
	this.centerAt = function(map, obj){
		//传入坐标数组
		if($.isArray(obj)){
			if(obj.length == 0 ) return ;
	    	if(obj.length == 1){
	    		var center = new esri.geometry.Point(obj[0][0], obj[0][1], map.spatialReference);
	    		map.centerAt(center);
	    	}else{
	    		var polylineJson = {
	    				  "paths":[obj],
	    				  "spatialReference":map.spatialReference
	    				  };
	    		var polyline = new esri.geometry.Polyline(polylineJson);
	    		var extent = polyline.getExtent();
	    		map.setExtent(extent.expand(2));
	    	}
		}else{
			//传入点
			if(obj.type == 'point' ){
				var center = new esri.geometry.Point(obj.x, obj.y, map.spatialReference);
	    		map.centerAt(center);
			}
			//传入线和面
			if(obj.type == 'polyline' || obj.type == 'polygon' || obj.type == 'multipoint'){
				var extent = obj.getExtent();
	    		map.setExtent(extent.expand(2));
			}
			//传入范围
			if(obj.type == 'extent'){
	    		map.setExtent(obj.expand(2));
			}
		}
	};
	
	
	/*
	 * 根据坐标数组高亮图层点（可以用于搜索）
	 * points 为坐标数组
	 * layer 为图层对象
	 * options 为可选参数 包括 defaultSymbol highLightSymbol render
	 * defaultSymbol 默认图标
	 * highLightSymbol 高亮图标
	 * render 为自定义渲染函数
	 */
	this.HighLightPoints = function(points, layer, options){
		var defaultSymbol = new esri.symbol.SimpleMarkerSymbol();
		var highLightSymbol = new esri.symbol.SimpleMarkerSymbol(esri.symbol.SimpleMarkerSymbol.STYLE_CIRCLE, 20,
				   new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID,
						   new dojo.Color([255,0,0]), 1),
						   new dojo.Color([0,255,0,0.25]));;
		if(options && options.defaultSymbol){
			defaultSymbol = options.defaultSymbol;
		}
		if(options && options.highLightSymbol){
			highLightSymbol = options.highLightSymbol;
		}
		var graphics = layer.graphics;
		//遍历图形
		$.each(graphics, function(j, graphic){
			if(options && options.render){
				//自定义渲染
				render(graphic, point);
			}else{
				var val = new Array();
				val[0] = (graphic.geometry.x);
				val[1] = (graphic.geometry.y);
				if(EasyMap.inArray(val, points)){
					graphic.setSymbol(highLightSymbol);
				}else{
					graphic.setSymbol(defaultSymbol);
				}
			}
		});
	};
	
	this.HighLightArea = function(paths, layer, options){
		var defaultSymbol = new esri.symbol.SimpleFillSymbol(
				esri.symbol.SimpleFillSymbol.STYLE_SOLID,
				new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,new dojo.Color([ 255, 0, 0 ]), 2), 
				new dojo.Color([ 230, 230, 250, 0.25 ]));
		if(options && options.defaultSymbol){
			defaultSymbol = options.defaultSymbol;
		}
		var selectedSymbol = new esri.symbol.SimpleFillSymbol(
						esri.symbol.SimpleFillSymbol.STYLE_SOLID,
						new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_DASHDOT,new dojo.Color("gray"),2), 
						new dojo.Color([195, 194, 60, 0.50]));
		if(options && options.selectedSymbol){
			selectedSymbol = options.selectedSymbol;
		}
		var json =JSON.stringify(paths);
		var graphics = layer.graphics;
		for(var i in graphics){
		var geometry = graphics[i].geometry;
			if(JSON.stringify(geometry.toJson()) == json){
				graphics[i].setSymbol(selectedSymbol);
			}else{
				graphics[i].setSymbol(defaultSymbol);
			}
		}
	};
	
	/*
	 * 初始化图层
	 * config 可选参数 包括 options onClickFunc onMouseOverFunc
	 * options 初始化参数
	 * onClickFunc 鼠标点击回调函数
	 * onMouseOverFunc 鼠标悬浮回调函数
	 */
	this.initLayer = function(map, config){
		var newlayer = null;
		if(config && config.options){
			newlayer = new esri.layers.GraphicsLayer(config.options);
		}else{
			newlayer = new esri.layers.GraphicsLayer();
		}
		
		var preNum = map.graphicsLayerIds.length;
		map.addLayer(newlayer);
		var nowNum = map.graphicsLayerIds.length;
		//检查图层添加结果
		try {
			if (preNum == nowNum && window.console) {
				throw new Error("图层初始化出现失败！");
			}
		} catch (e) {
			alert(e.message);

		} 
		
		//如果传入注册id则注册到signInLayers对象
		if(config && config.signInId){
			this.signInLayers[config.signInId] = newlayer;
		}
		
		//鼠标点击事件
		if(config && config.onClickFunc){
			dojo.connect(newlayer, 'onClick', function(event){
				config.onClickFunc(event);
				// add by huyi 2015-07-21 但点击巡检点时值停止管网数据查询事件
				map.infoWindow.hide();
				dojo.stopEvent(event);
			});
		}
		//鼠标悬浮事件
		if(config && config.onMouseOverFunc){
			dojo.connect(newlayer, 'onMouseOver', function(event){
				config.onMouseOverFunc(event);
			});
		}
		return newlayer;
	};
	
	/*
	 * 获得图层
	 */
	this.getLayer = function(id, map){
		return map.getLayer(id);
	};
	
	/*
	 * 清理指定地图
	 */
	this.clearMap = function(map){
		//清理graphicLayer图层
		var layerIds = map.graphicsLayerIds;
		jQuery.each(layerIds, function(i, id){
			var layer = map.getLayer(id);
			layer.clear();
		});
		
		//清理graphics图层
		map.graphics.clear();
	};
	
	/*
	 * 加载点
	 * layer 为图层对象
	 * datas 点数据
	 * options 为可选参数 包括 x y symbol render
	 */
	this.loadPoints = function( map, layer, datas, options){
		var xName = (options && options.x)?options.x:'x';
		var yName = (options && options.y)?options.y:'y';
		var symbol = (options && options.symbol)?options.symbol:new esri.symbol.SimpleMarkerSymbol();
		var that = this;
		jQuery.each(datas, function(i, data){
			//如果传入自定义渲染方法，则执行自定义渲染方法
			if(options && options.render){
				options.render(map, layer, data);
			}else{
				var point = new esri.geometry.Point(data[xName], data[yName], map.spatialReference);
				layer.add(new esri.Graphic(point, symbol, data));
			}
		});
	};
	
	/*
	 * 开始绘图
	 * map easymap 初始化的地图对象
	 * type 画图类型
	 * options 可选参数 包括 symbol layer onDrawEndFunc
	 * 
	 */
	this.startDraw = function(map, type, options){
		var that = this;
		var brush = map.drawBrush;
		//关闭之前绑定的事件
		dojo.disconnect(that.drawHandle);
		brush.activate(type);
		
		if(options && options.tipInfo){
			//更改提示信息
			var tooltip = jQuery(".map .tooltip");
			tooltip.css({width:'150px'});
			tooltip.text(options.tipInfo);
		}
		
		if(options && options.cancelOnEsc){
			//ESC 按键，取消操作
			jQuery(document).keyup(function(e){
				var key = e.which;
				if(key == 27){
					console.log("esc");
					brush.deactivate();
				}
			});
		}
		
		//绑定结束操作
		var handle = dojo.connect(brush,"onDrawEnd", function(geometry){
			//执行回调
			if(options && options.onDrawEndFunc){
				options.onDrawEndFunc(geometry, map);
			}else{
				var symbol = null;
				var layer = (options && options.layer)?options.layer:map.graphics;
				if(options && options.symbol){
					symbol = options.symbol;
				}else{
					switch(type){
						case that.drawType.CIRCLE :
							symbol = new esri.symbol.SimpleLineSymbol();
							break;
						case that.drawType.LINE :
							symbol = new esri.symbol.SimpleLineSymbol();
							break;
						case that.drawType.POLYLINE :
							symbol = new esri.symbol.SimpleLineSymbol();
							break;
						case that.drawType.FREEHAND_POLYLINE :
							symbol = new esri.symbol.SimpleLineSymbol();
							break;
						case that.drawType.POLYGON :
							symbol = esri.symbol.SimpleFillSymbol();
							break;
						case that.drawType.FREEHAND_POLYGON :
							symbol = esri.symbol.SimpleFillSymbol();
							break;
						default:
							symbol = new esri.symbol.SimpleMarkerSymbol();
					}
				}
				var graphic = new esri.Graphic(geometry, symbol);
				layer.add(graphic);
				//添加完后回调
				if(options && options.onAddLayerFunc){
					options.onAddLayerFunc(graphic, layer, map);
				}
			}
			//默认自动取消
			if(!(options && options.notAutoCancel)){
				//添加完，取消功能
				brush.deactivate();
			}
		});
		that.drawHandle = handle;
	};
	
	//停止绘图
	this.stopDraw = function(map){
		var brush = map.drawBrush;
		brush.deactivate();
	};
	
	//移动图形
	this.startMove = function(map, graphic, options){
		var that = this;
		var brush = map.editBrush;
		//鼠标跟随事件
		var handle = null;
		//关闭之前绑定的事件
		dojo.disconnect(that.editHandle);
		brush.activate(that.editType.MOVE, graphic);
		//绑定鼠标跟随坐标
		dojo.connect(brush, "onGraphicMoveStart", function(graphic) {
			jQuery("body").append("<div style=\"display: none\" id=\"move_tip\"></div>");
			EasyMap.followMouse("#move_tip");
			
			handle = dojo.connect(map , "onMouseMove", function(evt) {
				var mp = evt.mapPoint;
				dojo.byId("move_tip").innerHTML = mp.x.toFixed(2) + ", " + mp.y.toFixed(2);
			});
		});
		
		//移动结束，保存信息，关闭功能
		dojo.connect(brush, "onGraphicMoveStop", function(graphic) {
			dojo.disconnect(handle);
			jQuery("#move_tip").remove();
			if(options && options.onMoveStopFunc){
				options.onMoveStopFunc(graphic);
			}
			brush.deactivate();
		});
	};
	
	//编辑图形
	this.startEdit = function(map, type, graphic, options){
		var that = this;
		var brush = map.editBrush;
		//关闭之前绑定的事件
		dojo.disconnect(that.editHandle);
		
		if(options && options.options){
			brush.activate(type, graphic, options.options);
		}else{
			brush.activate(type, graphic);
		}
		
		//移动结束
		dojo.connect(brush, "onGraphicMoveStop", function(graphic) {
			if(options && options.onMoveStopFunc){
				options.onMoveStopFunc(graphic);
			}
		});
		//旋转结束
		dojo.connect(brush, "onRotateStop", function(graphic, info) {
			if(options && options.onRotateStopFunc){
				options.onRotateStopFunc(graphic, info);
			}
		});
		//添加节点
		dojo.connect(brush, "onVertexAdd", function(graphic, vertexInfo) {
			if(options && options.onVertexAddFunc){
				options.onVertexAddFunc(graphic, vertexInfo);
			}
		});
		//删除节点
		dojo.connect(brush, "onVertexDelete", function(graphic, vertexInfo) {
			if(options && options.onVertexDeleteFunc){
				options.onVertexDeleteFunc(graphic, vertexInfo);
			}
		});
		
	};
	
	//停止编辑
	this.stopEdit = function(map){
		var brush = map.editBrush;
		brush.deactivate();
	};
	
	/*
	 * 放大地图等级
	 */
	this.zoomIn = function(map){
		var level = map.getLevel();
		map.setLevel(level+1);
	};
	
	/*
	 * 放大地图等级
	 */
	this.zoomOut = function(map){
		var level = map.getLevel();
		map.setLevel(level-1);
	};
	
	/*
	 * 保存范围
	 */
	this.saveExtent = function(name, map){
		var extent = map.extent;
		this.extents[name] = extent;
	};
	
	/*
	 * 获取书签
	 */
	this.getExtent = function(name, map){
		var extent = this.extents[name];
		//传入map则设置范围，没有则直接返回范围对象
		if(map){
			map.setExtent(extent);
		}
		return extent;
	};
	
	/*
	 * 初始化测量
	 */
	this.initMeasure = function(map, options){
		var that = this;
		//默认是注册一个“measureLayer”的测量图层，多个地图时可以传入layerId自定义
		var layerId = (options && options.layerId)? options.layerId: "measureLayer";
		var serviceURL = (options && options.geometryService)?options.geometryService:that.serviceUrl.geometryService;
		var geometryService  = new esri.tasks.GeometryService(serviceURL);
		this.initLayer(map, {
			signInId:layerId,
			onClickFunc:function(event){
				var graphic = event.graphic;
				if(graphic.attributes && graphic.attributes.type == 'delete'){
					var graphics = graphic.attributes.graphics;
					for(var i in graphics){
						that.signInLayers[layerId].remove(graphics[i]);
					}
					that.signInLayers[layerId].remove(graphic);
				}
			}
		});
		
		//如果传入jquery选择器，将自动实现事件绑定
		if(options && (options.lineSel || options.areaSel)){
			if(options.lineSel){
				$(options.lineSel).click(function(){
					that.startMeasure(geometryService, that.drawType.POLYLINE, map, {
						options:options.options
					});
				});
			}
			if(options.areaSel){
				$(options.areaSel).click(function(){
					that.startMeasure(geometryService, that.drawType.POLYGON, map, {
						options:options.options
					});
				});
			}
		};
		
		if(options && options.clearSel){
			$(options.clearSel).click(function(){
				that.clearMeasure(layerId);
			});
		}
	};
	
	/*
	 * 开始测量
	 */
	this.startMeasure = function(geometryService, type, map, options){
		var that = this;
		that.startDraw(map, type, {
			layer: (options && options.layer)? options.layer: that.signInLayers.measureLayer,
			onAddLayerFunc:function(graphic, layer, map){
				that.doMeasure(geometryService, graphic, layer, options.options);
			}
		});
	};
	
	/*
	 * 清除测量图层数据
	 */
	this.clearMeasure = function(layerId){
		var id = layerId?layerId:"measureLayer";
		this.signInLayers[id].clear();
	};
	
	/*
	 * 测算距离
	 */
	this.doMeasure = function(geometryService, graphic, layer, options){
		  if(graphic.geometry.type=="polyline"){//如果为线类型就进行lengths距离测算
			 var objects = new Array();
			 objects.push(graphic);
			 var pointSize = graphic.geometry.paths[0].length;
			 
			 //设置节点图标
			 for(var i=0; i<pointSize; i++){
				 var p = graphic.geometry.getPoint(0, i);
				 var pSymbol = new esri.symbol.SimpleMarkerSymbol().setSize(5);
				 var pPoint = new esri.Graphic(p, pSymbol);
				 layer.add(pPoint);
				 objects.push(pPoint);
			 }
			 
			 //分成多个图形,分别计算路程
			 var polylines = new Array();
			 var geometry = esri.geometry.Polyline(graphic.geometry.toJson());
			 for(var i = (pointSize-1); i>0; i--){
				 polylines.push(esri.geometry.Polyline(geometry.toJson()));
				 geometry.removePoint(0, i);
			 }
			 
			 var lengthParams = new esri.tasks.LengthsParameters();
			 lengthParams.polylines = polylines;
			 lengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_KILOMETER;
			 lengthParams.calculationType = "geodesic";
		     geometryService.lengths(lengthParams, function(result){
		    	 for(var i in result.lengths){
		    		 var length = result.lengths[i].toFixed(3);
			    	 var lengthText = (length > 1)?(length + "千米"):(length * 1000 + "米");
			    	 var str = (i != 0 )?lengthText:("总长:"+lengthText);
			    	 var textSymbol =  new esri.symbol.TextSymbol(str).setColor(
			    			    new dojo.Color([128,0,0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
			    			    new esri.symbol.Font("10pt")) ;
			    	 var index = pointSize-1-i;
			    	 var point = graphic.geometry.getPoint(0, index);
			    	 textSymbol.setOffset(0, -10);
			    	 var label =  new esri.Graphic(point, textSymbol);
			    	 objects.push(label);
			    	 layer.add(label);
		    	 }
		     });
		     
		     //设置起点图标
			 var start = graphic.geometry.getPoint(0, 0);
			 var startSymbol = (options && options.start)?options.start:new esri.symbol.TextSymbol("start").setSize(8).setOffset(-20, 0).setColor(new dojo.Color([128,0,0]));
			 var startPoint = new esri.Graphic(start, startSymbol);
			 layer.add(startPoint);
			 objects.push(startPoint);
		     
		     //设置删除图标
		     var end = graphic.geometry.getPoint(0, pointSize-1);
			 var deleteSymbol = (options && options.del)?options.del:new esri.symbol.TextSymbol("删除").setSize(8).setOffset(15, 15).setColor(new dojo.Color([128,0,0]));
			 var deletePoint = new esri.Graphic(end, deleteSymbol, {type:"delete",graphics:objects});
			 layer.add(deletePoint);
		     
		  }else if(graphic.geometry.type=="polygon"){//如果为面类型需要先进行simplify操作在进行面积测算
			 var objects = new Array();
			 objects.push(graphic);
			 
			 var geometry = graphic.geometry;
		     var areasAndLengthParams = new esri.tasks.AreasAndLengthsParameters();
			 areasAndLengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_KILOMETER;
			 areasAndLengthParams.areaUnit = esri.tasks.GeometryService.UNIT_SQUARE_KILOMETERS;
			 areasAndLengthParams.calculationType = "geodesic";
		     geometryService.simplify([geometry], function(simplifiedGeometries){
		    	 areasAndLengthParams.polygons = simplifiedGeometries;
				 geometryService.areasAndLengths(areasAndLengthParams, function(result){
					 var length = result.lengths[0].toFixed(3);
					 var area = result.areas[0].toFixed(6);
					 var lengthText = (length > 1)?(length + "千米"):(length * 1000 + "米");
					 var areaText = (area > 1)?(area + "平方千米"):(area * 1000000 + "平方米");
					 var str = "面积："+ areaText +" 周长："+ lengthText;
					 var center = geometry.getExtent().getCenter();
					 var textSymbol =  new esri.symbol.TextSymbol(str).setColor(
			    			    new dojo.Color([128,0,0])).setAlign(esri.symbol.Font.ALIGN_START).setFont(
			    			    new esri.symbol.Font("10pt")) ;
			    	 var label =  new esri.Graphic(center, textSymbol);
			    	 layer.add(label);
			    	 objects.push(label);
			    	 
			    	//设置删除图标
					var deleteSymbol = (options && options.del)?options.del:new esri.symbol.TextSymbol("删除").setSize(8).setOffset(0, 15).setColor(new dojo.Color([128,0,0]));
					var deletePoint = new esri.Graphic(center, deleteSymbol, {type:"delete",graphics:objects});
					layer.add(deletePoint);
				 });
		     });
		  };
	};
	
	// 判断iframe隐藏状态下重新打开地图是否加载完成以及完成后的操作
	this.mapIframeOnload = function(map,func){
		if(map.loaded == false){
			map.on("load",function(){
				func();
			});
		}else{
			func();
		}
	};
}

/*----------------------------------------------- 静态方法部分 ------------------------------------------*/
/*
 * 加强版inarray,val支持数组格式
 */
EasyMap.inArray = function (val, arr){
	var flag = false;
	if($.isArray(val)){
		$(arr).each(function(i, item){
			if(val.toString() == item.toString()){
				flag = true;
			}
		});
	}else{
		if($.inArray(val, arr) > -1){
			flag = true;
		}
	}
	return flag;
};

/*
 * 指定元素跟随鼠标移动
 */
EasyMap.followMouse = function (selector){
	$(document).mousemove(function(e){
		var event = e || window.event;
		var div = jQuery(selector);
		div.css({
			display : "block",
			left : document.body.scrollLeft + event.clientX + 10,
			top : document.body.scrollTop + event.clientY + 5,
			position : "absolute"
		});
	});
};

/*
 * 对象复制克隆
 */
EasyMap.deepCopy = function(source) { 
	var result={};
	for (var key in source) {
		result[key] = ((source[key] != null) && (typeof source[key]==='object')) ? EasyMap.deepCopy(source[key]): source[key];
	} 
	return result; 
};

/*
 * 对象复制克隆
 */
EasyMap.dataCopy = function(obj){
	var json = JSON.stringify(obj);
	return JSON.parse(json);
};





