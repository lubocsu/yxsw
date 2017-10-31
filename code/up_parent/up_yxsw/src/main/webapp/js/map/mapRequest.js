(function() {

	var mapRequest = window.mapRequest = {

		init : function() {

		},
		cancelDraw : function() {
			var map = mapObj.map;
			mapObj.easyMap.stopDraw(map);
		},
		/* 开启图例 */
		legendOn : function(options) {
			this.legendSet(options);
			$("#legend").show();
		},
		/* 配置图例参数 */
		legendSet : function(options) {
			var src = path + "/image/map/legend/legend.png";
			if (options && options.src) {
				$("#legend img").attr("src", options.src);
			} else {
				$("#legend img").attr("src", src);
			}

			if (options && options.position) {
				switch (options.position) {
				case 'left':
					$("#legend").css({
						bottom : "35px",
						left : "5px",
						right : "auto"
					});
					break;
				case 'right':
					$("#legend").css({
						bottom : "35px",
						right : "5px",
						left : "auto"
					});
					break;
				}
			} else {
				$("#legend").css({
					bottom : "35px",
					right : "5px",
					left : "auto"
				});
			}
		},
		/* 关闭图例 */
		legendOff : function() {
			$("#legend").hide();
		},
		/** 地图清空 */
		clearMap : function() {
			// 清空注册定时任务
			for ( var item in insIndex.intervals) {
				if (item) {
					clearInterval(eval("insIndex.intervals." + item));
				}
				;
			}
			// 清空所有图层类容
			for ( var item in mapObj.layers) {
				if (item != "basePhotoLayer" && item != "baseLayer" && item != "cqLableLayer" && item != "waterLineLayer") {
					eval('mapObj.layers.' + item).clear();
				}
			}

		},
		/** 地图定位 */
		centerAt : function(map, obj) {
			// 传入坐标数组
			if ($.isArray(obj)) {
				if (obj.length == 0)
					return;
				if (obj.length == 1) {
					var center = new esri.geometry.Point(obj[0][0], obj[0][1], map.spatialReference);
					map.centerAt(center);
				} else {
					var polylineJson = {
						"paths" : [ obj ],
						"spatialReference" : map.spatialReference
					};
					var polyline = new esri.geometry.Polyline(polylineJson);
					var extent = polyline.getExtent();
					map.setExtent(extent.expand(2));
				}
			} else {
				// 传入点
				if (obj.type == 'point') {
					var center = new esri.geometry.Point(obj.x, obj.y, map.spatialReference);
					map.centerAt(center);
				}
				// 传入线和面
				if (obj.type == 'polyline' || obj.type == 'polygon') {
					var extent = obj.getExtent();
					map.setExtent(extent.expand(2));
				}
			}
		},

		/**
		 * 定位并高亮资源2016.09.02
		 * 
		 * @param paths
		 */
		centerAtAndHigtLine : function(geometry, objectid, layerid) {
			if (geometry.paths) {
				// 定位点样式
				var symbol = new esri.symbol.PictureMarkerSymbol(path + '/img/map_marker.png', 32, 32);
				var tipImg = new esri.geometry.Point(geometry.paths[0][0][0], geometry.paths[0][0][1], mapObj.map.spatialReference);
				var graphic = new esri.Graphic(tipImg, symbol, {
					"objectid" : objectid
				});
				graphic.layerid = layerid;
				mapObj.layers.highLightLayer.clear();

				var cls = new esri.symbol.CartographicLineSymbol(esri.symbol.CartographicLineSymbol.STYLE_SOLID, new esri.Color([ 255,0, 0 ]), 5, esri.symbol.CartographicLineSymbol.CAP_ROUND, esri.symbol.CartographicLineSymbol.JOIN_BEVEL, 1);
				var polylineJson = {
					"paths" : geometry.paths,
					"spatialReference" : mapObj.map.spatialReference
				};
				var polyline = new esri.geometry.Polyline(polylineJson);
				mapObj.layers.highLightLayer.add(new esri.Graphic(polyline, cls,  {
					"objectid" : objectid
				}));
				mapObj.layers.highLightLayer.add(graphic);
				this.centerAt(mapObj.map, geometry.paths[0]);
			} else {
				// 定位点样式
				var symbol = new esri.symbol.PictureMarkerSymbol(path + '/img/map_marker.png', 32, 32);
				var tipImg = new esri.geometry.Point(geometry.x, geometry.y, mapObj.map.spatialReference);
				var graphic = new esri.Graphic(tipImg, symbol, {
					"objectid" : objectid
				});
				graphic.layerid = layerid;
				mapObj.layers.highLightLayer.clear();
				mapObj.layers.highLightLayer.add(graphic);
				geometry.type = "point";
				mapObj.map.setZoom(17);
				this.centerAt(mapObj.map, geometry);
			}
		},
	};
	
})();

jQuery(function() {

	jQuery("#map_zoom_in").click(function() {
		mapObj.map.setZoom(mapObj.map.getZoom() + 1);
	});
	jQuery("#map_zoom_out").click(function() {
		mapObj.map.setZoom(mapObj.map.getZoom() - 1);
	});
	// 复位功能，初始化大小
	jQuery("#map_span").click(function() {
		var map = mapObj.map;
		var extent = new esri.geometry.Extent(mapObj.initExtent);
		map.setExtent(extent);
	});
	// 停止绘图，平移工具
	jQuery("#map_hand").click(function() {
		// 取消画笔
		mapRequest.cancelDraw();
	});
	// 标会
	jQuery("#map_point").click(function() {
		dojo.disconnect(window.mapHandler);
		dojo.disconnect(window.highLightHandler);
		mapObj.tool.plotGraphic(esri.toolbars.Draw.POINT);
	});
	// 测距
	jQuery("#map_distance").click(function() {
		dojo.disconnect(window.mapHandler);
		dojo.disconnect(window.highLightHandler);
		mapObj.tool.plotGraphic(esri.toolbars.Draw.POLYLINE);
	});
	// 侧面积
	jQuery("#map_area").click(function() {
		dojo.disconnect(window.mapHandler);
		dojo.disconnect(window.highLightHandler);
		mapObj.tool.plotGraphic(esri.toolbars.Draw.POLYGON);
	});
	// 清空选择
	jQuery("#map_clean").click(function() {
		mapRequest.clearMap();
		jQuery(".divLiSelect").addClass("hidden");
	});
	// 截图
	jQuery("#cut_print").click(function() {
		jQuery(".divLiSelect").addClass("hidden");
		setTimeout(function() {
			sc(0);
		}, 100);
	});

	// 影像图切换
	jQuery("#baseMap").click(function() {
		mapObj.layers.basePhotoLayer.setVisibility(false);
		mapObj.layers.cqLableLayer.setVisibility(false);
	});
	jQuery("#photoMap").click(function() {
		mapObj.layers.basePhotoLayer.setVisibility(true);
		mapObj.layers.cqLableLayer.setVisibility(true);
	});

});

// 
