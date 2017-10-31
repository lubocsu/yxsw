package com.upsoft.login.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.upsoft.login.listener.ConfigListener;
import com.upsoft.login.support.webservice.ServiceReceiver;
import com.upsoft.login.support.webservice.SysUtils;
import com.upsoft.system.constant.CommonConstant;
import com.upsoft.system.constant.DictConstant;
import com.upsoft.system.entity.SysUser;
import com.upsoft.system.util.PropertiesUtil;

@Controller
public abstract class BaseController {
	
	private static final Logger logger = Logger.getLogger(BaseController.class);
	protected void putDictConstant(ModelMap map){
		Map<String, String> m = new HashMap<String, String>();
		for (DictConstant dict : DictConstant.values()) {
			m.put(dict.name(), dict.getValue());
		}
		map.put("dict", m);
	}
	
	/**
	 * 获取菜单下界面相关资源的权限
	 * @date 2015年1月28日 下午4:39:59
	 * @author 吴炫
	 * @param userId
	 * @param menuId
	 */
	protected void findMenuResourcePermission(HttpServletRequest request, ModelMap map){
		SysUser user = SysUtils.getLoginSysUser(request);
		//菜单权限编码（不是ID）
		String permid = request.getParameter("permid");
		Map<String,Integer> btnAuths = ServiceReceiver.getBtnPermission(permid, user.getUserId(), request.getContextPath().substring(1));
		// 只要按钮权限值不为0，则表示有按钮权限
		map.addAttribute(CommonConstant.KEY_FOR_IN_MENU_PERMISSION, btnAuths);
	}
	
	/**
	 * 从配置文件中获取地图地址信息绑定到map对象
	 * 子类中调用 super.putMapUrl(request, map);使用
	 * @date 2015年4月22日 下午3:40:13
	 * @author null
	 * @param request
	 * @param map 
	 */
	protected void putMapUrl(HttpServletRequest request, ModelMap map){
		Properties props = ConfigListener.CasConfig;
		String REQUEST_IP = getRequestIp(request);
		String arcgisJs = props.getProperty("arcgis_init_js_url_"+REQUEST_IP);
		String arcgisCss = props.getProperty("arcgis_init_css_url_"+REQUEST_IP);
		String arcgisBaseMap = props.getProperty("arcgis_basemap_url_"+REQUEST_IP);
		String arcgisGeometryService = props.getProperty("arcgis_geometryservice_url_"+REQUEST_IP);
		String arcgisWaterLine = props.getProperty("arcgis_waterline_service_"+REQUEST_IP);
		String arcgisPhotoMap = props.getProperty("arcgis_photo_service_"+REQUEST_IP);
		String arcgisAreaName = props.getProperty("arcgis_areaname_service_"+REQUEST_IP);
		String mobile_gis_server = props.getProperty("mobile_gis_server_"+REQUEST_IP);
		
		String arcgisInitExtent = (String) ConfigListener.CommConfig.get("arcgis_map_extent");//从application-cas.properties中获取
		
//		map.addAttribute("ArcGIS_API_URL", arcgisAPI); 				// init.js中需要（加载本地地图环境时需要额外添加此属性）
		map.addAttribute("ArcGIS_JS_URL", arcgisJs);
		map.addAttribute("ArcGIS_CSS_URL", arcgisCss);
		map.addAttribute("ArcGIS_BaseMap_URL", arcgisBaseMap);		//地图底图服务
		map.addAttribute("ArcGIS_Geometry_Service", arcgisGeometryService);
		map.addAttribute("ArcGIS_Init_Extent", arcgisInitExtent);	//初始化范围
		map.addAttribute("Arcgis_Water_Line", arcgisWaterLine);		//巡检管线服务地址
		map.addAttribute("Arcgis_Photo_Map", arcgisPhotoMap);		//卫星地图服务
		map.addAttribute("Arcgis_Area_Name", arcgisAreaName);		//地名服务
		map.addAttribute("mobile_gis_server",mobile_gis_server);
		logger.debug("APP GIS服务地址："+map.get("mobile_gis_server"));
	}
	
	/**
	 * 获取过滤其他口径的数据量默认值和代表请他口径的 常量
	 * @date 2016年8月18日 上午9:25:42
	 * @author 胡毅
	 * @param request
	 * @param map
	 */
	protected Map<String,String> getDefaultPiperadiusCountAndOhterPiperadius(HttpServletRequest request,ModelMap map){
		Properties configs = PropertiesUtil.getCommonCfgProperties();
		String defaultPiperadius = (String)configs.get("asset_water_line_default_piperadius");
		String waterLineType = (String)configs.get("aseet_water_line");
		String waterPointType = (String)configs.get("asset_water_point");
		if(StringUtils.isBlank(defaultPiperadius)){
			defaultPiperadius = "300";
		}
		map.put("waterLineType", waterLineType);
		map.put("waterPointType", waterPointType);
		Map<String,String> mp = new HashMap<String, String>();
		mp.put("defaultPiperadius", defaultPiperadius);
		mp.put("waterLineType", waterLineType);
		mp.put("waterPointType", waterPointType);
		return mp;
	}
	
	/**
	 * 获取请求ip
	 * 
	 * @date 2017年4月20日11:12:19
	 * @author 刘志华
	 * @param request
	 * @return
	 */
	protected String getRequestIp(HttpServletRequest request) {
		return request.getServerName();
	}
	/**
	 * 获取请求的业务地址 类似http://xxx.xxx.xxx.xx:8088
	 * @date 2017年4月20日11:12:19
	 * @author 刘志华
	 * @param request
	 * @return
	 */
	protected String getLocalAddress(HttpServletRequest request) {
		String url = request.getRequestURL().toString();
		int index = url.indexOf("/", 7);
		int next = url.indexOf("/", index);
		if(next<0){
			return url;
		}else{
			return url.substring(0, next);
		}
		
	}
}
