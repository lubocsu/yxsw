/**
 * 地图初始化
 * @author WX
 */
function BasicMapTool(){
	this.map = null;
	this.plotLayer = null;
	this.drawbrush = null;						//画笔
	//测绘几何服务
	this.geometryService = null;
	//测绘线路上次点击坐标点
	this.preClickPoint = null;
	this.preMarkPoint = null;
	this.preClickTime = null;
	//保存临时图形
	this.arrayOfGraphic = null;
	//是否完成图形编辑
	this.drawFinish = true;
}

BasicMapTool.prototype.init = function(map, geometryService, on){
	this.map = map;
	this.geometryService = geometryService;
	var $this = this;
	//地图初始化完成后添加相关事件
	on(this.map,"load",function(map){
		//初始化画图工具
		$this.drawbrush = new esri.toolbars.Draw($this.map);
		//画图完成事件
		$this.drawbrush.on("draw-end", function(evtObj){
			$this.addGraphicToMap(evtObj);
			// 2016.09.06绘制结束后重新绑定事件
			insIndex.globalObj.mapQuery.mapHandler();
			insIndex.globalObj.mapQuery.highLightLayerHandler();
		});
	});
	//添加测绘
	this.plotLayer = new esri.layers.GraphicsLayer();
	mapObj.layers.plotLayer = this.plotLayer;
	this.map.addLayer(this.plotLayer);
	on(this.map,"click",function(mouseEvent){
		//表示测绘进行中
		if(!$this.drawFinish){
			var targetTime = new Date().getTime();
			if(targetTime-$this.preClickTime>230){
				$this.preClickTime = targetTime;
				var mapPoint = mouseEvent.mapPoint;
				var graphic = new esri.Graphic(mapPoint, new esri.symbol.PictureMarkerSymbol(path+"/image/map/node.png",13,13));
				$this.plotLayer.add(graphic);
				$this.arrayOfGraphic.push(graphic);
				//保存图形：用于删除所有相关图形
				if(!$this.preClickPoint){
					var startMark = new esri.Graphic(mapPoint, new esri.symbol.PictureMarkerSymbol(path+"/image/map/start.png",21,25).setOffset(0,14));
					$this.plotLayer.add(startMark);
					$this.arrayOfGraphic.push(startMark);
				}else{
					$this.markLength(new esri.geometry.Polyline().addPath([[$this.preClickPoint.geometry.x,$this.preClickPoint.geometry.y],[mapPoint.x,mapPoint.y]]), $this.drawFinish);
				}
				$this.preClickPoint = graphic;
			}
		}
	});
	//图层点击事件
	on(this.plotLayer,"click",function(evtObj){
		if($this.drawFinish){
			var attrs = evtObj.graphic.attributes;
			var length = 0;
			var graphics = null;
			//判断graphics属性不为空的时候：删除测绘图标
			if(attrs&&attrs.graphics){
				graphics = attrs.graphics;
				length = graphics.length;
				for(var i=0;i<length;i++){
					$this.plotLayer.remove(graphics[i]);
				}
			}
		}
	});
};
/**
 * 测量
 * @date 2015年3月4日 下午4:01:16
 * @author 吴炫
 * @param geometryType
 */
BasicMapTool.prototype.plotGraphic = function(geometryType){
	this.drawbrush.activate(geometryType);
	this.drawFinish=false;
	this.arrayOfGraphic = new Array();
	this.preClickTime = new Date().getTime();
};
/**
 * 将所化图像添加到地图
 * @date 2015年2月27日 下午4:25:41
 * @author 吴炫
 * @param evtObj
 */
BasicMapTool.prototype.addGraphicToMap = function(evtObj){
	$this = this;
	this.drawFinish = true;
	this.drawbrush.deactivate();
	//根据几何类型添加几何图形
	var geometry = evtObj.geometry;
	var graphic = null;
	switch (geometry.type) {
	case "point":
		graphic = new esri.Graphic(geometry, new esri.symbol.SimpleMarkerSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, 2,
			    new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new esri.Color([255,0,0]), 1), new esri.Color([0,255,0,0.25])));
		this.plotLayer.add(graphic);
		this.arrayOfGraphic.push(graphic);
		var point = geometry;
		var text = "坐标：["+point.x.toFixed(3)+","+point.y.toFixed(3)+"]";
		console.info("["+point.x.toFixed(5)+","+point.y.toFixed(5)+"]");
		var plotMark = new esri.Graphic(point, new esri.symbol.TextSymbol(text).setOffset(0,-20));
		$this.plotLayer.add(plotMark);
		$this.arrayOfGraphic.push(plotMark);
		$this.finishDraw(point);
		break;
	case "polyline":
		graphic = new esri.Graphic(geometry, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new esri.Color([255,0,0]), 2));
		this.plotLayer.add(graphic);
		this.arrayOfGraphic.push(graphic);
		//计算总长度
	    this.markLength(geometry,this.drawFinish);
		break;
	case "polygon":
		graphic = new esri.Graphic(geometry, new esri.symbol.SimpleFillSymbol(esri.symbol.SimpleFillSymbol.STYLE_SOLID, new esri.symbol.SimpleLineSymbol(esri.symbol.SimpleLineSymbol.STYLE_SOLID, new dojo.Color([ 255, 0, 0 ]), 2), new dojo.Color([255,255,0, 0.25])));
		this.plotLayer.add(graphic);
		this.arrayOfGraphic.push(graphic);
		//如果是测绘就显示测量数据
		var areasAndLengthParams = new esri.tasks.AreasAndLengthsParameters();
	    areasAndLengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_KILOMETER;
	    areasAndLengthParams.areaUnit = esri.tasks.GeometryService.UNIT_SQUARE_KILOMETERS;
	    areasAndLengthParams.calculationType = "geodesic";
	    this.geometryService.simplify([geometry], function(simplifiedGeometries) {
			areasAndLengthParams.polygons = simplifiedGeometries;
			$this.geometryService.areasAndLengths(areasAndLengthParams,function(evtObj){
				var area = new Number(evtObj.areas[0]).toFixed(6);
			    var text = "面积：";
				if (area > 1) {
					text += area + "平方公里";
				} else {
					text += area * 1000000 + "平方米";
				}
				var length = evtObj.lengths[0].toFixed(3);
				if (length > 1) {
					text += "    周长：" + length + "千米";
				} else {
					text += "    周长：" + length * 1000 + "米";
				}
				var point = geometry.getCentroid();
				var plotMark = new esri.Graphic(point, new esri.symbol.TextSymbol(text).setOffset(0,-5));
				$this.plotLayer.add(plotMark);
				$this.arrayOfGraphic.push(plotMark);
				if($this.drawFinish){
	    			$this.finishDraw(point);
	    		}
			},function(error){
				var point = geometry.getCentroid();
				var plotMark = new esri.Graphic(point, new esri.symbol.TextSymbol("计算失败").setOffset(0,-5));
				$this.plotLayer.add(plotMark);
				$this.arrayOfGraphic.push(plotMark);
				if($this.drawFinish){
	    			$this.finishDraw(point,$this.drawFinish);
	    		}
			});
	    });
		break;
	}
};
/**
 * 测量长度
 * @date 2015年2月28日 下午5:09:04
 * @author 吴炫
 * @param result
 */
BasicMapTool.prototype.markLength = function(geometry,isFinish){
	var $this = this;
	var lengthParams = new esri.tasks.LengthsParameters();
    lengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_KILOMETER;
    lengthParams.calculationType = "geodesic";
    this.geometryService.simplify([geometry], function(simplifiedGeometries) {
    	lengthParams.polylines = simplifiedGeometries;
    	$this.geometryService.lengths(lengthParams,function(evtObj){
    		var text = "";
			var length = evtObj.lengths[0].toFixed(3);
			if (length > 1) {
				text += length + "千米";
			} else {
				text += length * 1000 + "米";
			}
			if(isFinish){
				$this.plotLayer.remove($this.preMarkPoint);
			}
			var point = geometry.getPoint(0, geometry.paths[0].length-1);
			$this.preMarkPoint = new esri.Graphic(point, new esri.symbol.TextSymbol(text).setOffset(0,-15));
    		$this.plotLayer.add($this.preMarkPoint);
    		$this.arrayOfGraphic.push($this.preMarkPoint);
    		if(isFinish){
    			$this.finishDraw(point);
    		}
    	},function(error){
    		if(isFinish){
				$this.plotLayer.remove($this.preMarkPoint);
			}
    		var point = geometry.getPoint(0, geometry.paths[0].length-1);
    		$this.preMarkPoint = new esri.Graphic(point, new esri.symbol.TextSymbol("计算失败").setOffset(0,-15));
    		$this.plotLayer.add($this.preMarkPoint);
    		$this.arrayOfGraphic.push($this.preMarkPoint);
    		if(isFinish){
    			$this.finishDraw(point);
    		}
    	});
    });
};
/**
 * 添加删除图标
 * @date 2015年3月3日 下午6:25:04
 * @author 吴炫
 * @param point
 */
BasicMapTool.prototype.finishDraw = function(point){
	var deleteMark = new esri.Graphic(point, new esri.symbol.PictureMarkerSymbol(path+"/image/map/delete.png",22,22).setOffset(0,18));
	this.arrayOfGraphic.push(deleteMark);
	deleteMark.setAttributes({
		graphics: new Array().concat(this.arrayOfGraphic)
	});
	this.plotLayer.add(deleteMark);
	//清空临时数据
	$this.preClickPoint = null;
	$this.preMarkPoint = null;
};
/**
 * 清空操作（标会、测距、测面积）
 * @date 2015年3月12日 14:18
 * @author TW
 */
BasicMapTool.prototype.clearOperator = function(){
	this.plotLayer.clear();
	mapObj.layers.HisCoordinatePoint.clear();
	mapObj.layers.HisCoordinateLine.clear();
};
/**
 * 获取多条线（线对象数组）的长度（单位：千米，保留一位小数）
* @date 2015年3月19日 21:36
 * @author TW
 */
BasicMapTool.prototype.getLinesLength = function(polylines,func){
	var lengthParams = new esri.tasks.LengthsParameters();
    lengthParams.polylines = polylines;
    lengthParams.lengthUnit = esri.tasks.GeometryService.UNIT_KILOMETER;  
    lengthParams.geodesic = true;  
    var tempLines = lengthParams.polylines;
    for(var i=0;i<tempLines.length;i++){
    	lengthParams.polylines[i].spatialReference = mapObj.map.spatialReference; 
    }
    console.info(lengthParams);
    mapObj.geometryService.lengths(lengthParams,function(evt){
    	func(evt.lengths);
    }); 
};