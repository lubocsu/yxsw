package com.upsoft.systemweb.webservice;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface PlatformWebService {
	
	/**
	 * 用户登陆
	 * @date 2015年1月22日 上午10:20:41
	 * @author 蒋迪
	 * @param loginId			登录账号
	 * @param password		登录密码
	 * @param systemCode	系统编码
	 * @param ipAddress		ip地址
	 * @return WSLoginInfoBean对象
	 */
	public String login(@WebParam(name = "loginId") String loginId, @WebParam(name = "password") String password, 
			@WebParam(name = "systemCode") String systemCode);
	
	/**
	 * 用户登出
	 * @date 2015年1月22日 下午3:02:45
	 * @author 蒋迪
	 * @param token		登录后获取的token
	 * @return 登出成功返回true，失败返回false
	 */
	public boolean logout(@WebParam(name = "token") String token);
	
	/**
	 * 根据token，校验用户是否登录
	 * @date 2015年1月22日 下午3:03:50
	 * @author 蒋迪
	 * @param token
	 * @return 
	 */
	public boolean checkLogin(@WebParam(name = "token") String token);
	
	/**
	 * 根据token查询全部登录信息
	 * @date 2015年2月5日 上午9:13:42
	 * @author 蒋迪
	 * @param token
	 * @return 
	 */
	public String getLoginInfoByToken(@WebParam(name = "token") String token);
	
	/**
	 * 根据token查询用户信息
	 * @date 2015年2月5日 上午9:13:56
	 * @author 蒋迪
	 * @param token
	 * @return 
	 */
	public String getUserInfoByToken(@WebParam(name = "token") String token);
	
	/**
	 * 通过userId获取系统用户
	 * @date 2015年1月21日 下午4:13:30
	 * @author 蒋迪
	 * @param loginId	用户登录账号
	 * @param password	用户密码
	 * @return 	未找到对应用户，返回null
	 */
	public String getUser(@WebParam(name = "userId") String userId);
	
	/**
	 * 通过orgId获取机构对象
	 * @date 2015年8月10日 下午2:45:07
	 * @author null
	 * @param orgId
	 * @return 未找到对应机构，返回null
	 */
	public String getOrg(@WebParam(name = "orgId") String orgId);
	
	/**
	 * 获取字典值{code:value1}
	 * @date 2017年9月2日 上午11:43:01
	 * @author 胡毅
	 * @param parentNodeId
	 * @param code
	 * @return
	 */
	public String getDictValue1(@WebParam(name = "parentNodeId")String parentNodeId,
			@WebParam(name = "code")String code);

	/**
	 * 根据字典类型获取下拉格式数据[{key:"code",value:"data1",value2:"data2",value3:"data3"}]
	 * @date 2017年9月1日 下午5:26:12
	 * @author 胡毅
	 * @param parentNodeId
	 * @return
	 */
	public String getDictSelect(@WebParam(name="parentNodeId")String parentNodeId);
//	
	/**
	 * 返回指定用户在指定系统下可以访问的机构集合
	 * 内容字段：[FAE1459E9AA44ADFA8E138CD60E8BB1B, 402862815e2d0a17015e2d20cbf60003, 402862815e2d0a17015e2d21079a0005]
	 * @date 2016年6月3日 下午4:29:30
	 * @author null
	 * @param userId
	 * @return 
	 */
	public String getPermissionOrgIds(String userId);
	
	/**
	 * 获取按钮权限
	 * 内容字段：{add=1, modify=1, import=1, upfile=1, query=1, downfile=1, export=1, remove=1}
	 * @date 2016年5月27日 下午6:05:44
	 * @author null
	 * @param permissionNo
	 * @param userId
	 * @param sysCode
	 * @return 
	 */
	public String getBtnPermission(@WebParam(name = "permissionNo") String permissionNo, @WebParam(name = "userId")String userId, @WebParam(name = "sysCode")String sysCode);
	
	/**
	 * 返回用户可访问的机构树(数据权限控制)
	 * 2 parentId和parentCode可以指定根节点，不指定则返回所有
	 * 3 parentId和parentCode同时指定，则parentId生效
	 * 字段内容：[{id:"orgid",code:"orgcode":parentId:"parentOrgid",name:"orgname",type:"orgtypeid"}]
	 * @date 2016年6月3日 下午5:38:02
	 * @author null
	 * @param userId
	 * @param parentId
	 * @param parentCode
	 * @return 
	 */
	public String getOrgTreeInPermission(@WebParam(name = "userId")String userId, @WebParam(name = "parentId")String parentId, @WebParam(name = "parentCode")String parentCode);
	
	/**
	 * 返回用户可访问用户下拉数据(根据用户数据中的机构过滤，其中，机构id从数据权限获取)
	 * 字段内容：[{key:"username",value:"userid"}]
	 * @date 2015年8月3日 下午5:01:26
	 * @author null
	 * @param orgId
	 * @return 
	 */
	public String getUserSelectInPermission(@WebParam(name="userId")String userId);
	
	/**
	 * 返回用户机构机构树，其中机构不可以被选中只能展开
	 * orgId不为空时，表示查看指定机构及其自己够用户数据，若该机构不在该用户拥有的权限机构内，则返回空集合
	 * 字段内容：[{"name":"生技科","icon":"../image/company.png","clickExpand":"true","id":"402862815e757ab6015e75c8b00a000d","parentId":"402862815e5ee05d015e5f220dfe0029"},
	 * {"name":"张斌峰","icon":"../image/person.png","clickExpand":"false","id":"402862815e757ab6015e75c99f5f0011","parentId":"402862815e757ab6015e75c8b00a000d"}]
	 * @date 2017年9月12日 下午5:52:17
	 * @param userId
	 * @param orgId 
	 * @author 胡毅
	 * @return
	 */
	@Deprecated
	public String getUserOrgTreeInPermission(String userId,String orgId);
	
	/**
	 * 根据用所在单位（渝西特指登录用户的公司或者厂站Id,其他系统不限制机构ID）
	 * 字段内容：[{"name":"生技科","icon":"../image/company.png","clickExpand":"true","id":"402862815e757ab6015e75c8b00a000d","parentId":"402862815e5ee05d015e5f220dfe0029"},
	 * {"name":"张斌峰","icon":"../image/person.png","clickExpand":"false","id":"402862815e757ab6015e75c99f5f0011","parentId":"402862815e757ab6015e75c8b00a000d"}]
	 * @date 2017年9月12日 下午8:01:29
	 * @author 胡毅
	 * @param orgId
	 * @return
	 */
	public String getUserOrgTreeByOrgId(String orgId,String userId);
	
	/**
	 * 根据机构ID获取当前机构以及下属所有的机构，全部字段
	 * 字段内容：[{"orderno":1,"orgcode":"CQSWJT","orgid":"FAE1459E9AA44ADFA8E138CD60E8BB1B",
	 * 			"enabled":1,"shortspelling":"No_Spell","orgname":"重庆水务集团","parentorgid":"0","orgtypeid":"1"}]
	 * @date 2015年7月29日 下午4:32:37
	 * @author 冉恒鑫
	 * @param orgId
	 * @return
	 */
	public String iterateOrgById(@WebParam(name="orgId")String orgId);
	
	/**
	 * 根据机构编码获取当前机构以及下属所有的机构的编码
	 * 字段内容：["CQSWJT","TEST_20170829_A","TEST_20170829_B"]
	 * @date 2015年8月4日 上午9:31:46
	 * @author null
	 * @param orgCode
	 * @return 
	 */
	public String getChildOrgCodes(@WebParam(name="orgCode")String orgCode);
	
	/**
	 * 根据菜单ID和用户ID获取菜单下的子菜单。JSON树形结构,管理员用户不进行权限控制
	 * @date 2015年7月28日 上午10:06:58
	 * @author 冉恒鑫
	 * @param menuId
	 * @param userId
	 * @return
	 */
	public String queryMenusByMenuIdAndUserId(@WebParam(name="menuId")String menuId,@WebParam(name="userId")String userId);
	
	/**
	 * 获取系统下菜单:JSON树形结构,管理员用户不进行权限控制
	 * 字段内容：[{"orderno": 2,"systemcode": "up_systemweb","isparent": 0, "systemname": "管网后台管理系统",
        		"icon": "/image/menu.png", "functiontype": 2,"parentid": "C2E91F49ACE6479EA519B1CD53AA7720",
        		"url": "/up_systemweb/menu/init","target": "frmright", "name": "菜单管理","id": "1B5F188E013C4122AC93C828BD1EF1BE",
        		"permissionno": "MenuManage" }]
	 * @date 2015年7月24日 下午5:32:29
	 * @author 冉恒鑫
	 * @param userId
	 * @param systemCode
	 * @return
	 */
	public String queryMenusBySysCodeAndUserId(@WebParam(name = "userId")String userId,@WebParam(name = "systemCode")String systemCode);
	
	/**
	 * 在queryMenusBySysCodeAndUserId上查询折叠式菜单JSON数据
	 * 字段内容：[{ orderno=1,systemcode=up_systemweb,isparent=1,systemname=管网后台管理系统,icon=/image/base.png,functiontype=1,parentid=-1,url=null,target=null,param=null,
        		 children=[{orderno=2,systemcode=up_systemweb,isparent=0,systemname=管网后台管理系统,icon=/image/menu.png,functiontype=2,
                			parentid=C2E91F49ACE6479EA519B1CD53AA7720,url=/up_systemweb/menu/init,target=frmright,param=null,name=菜单管理,
                			id=1B5F188E013C4122AC93C828BD1EF1BE,permissionno=MenuManage
            	 }]
            }]
	 * @date 2015年8月10日 下午12:34:53
	 * @author null
	 * @param systemCode
	 * @param userId
	 * @return 
	 */
	public String queryAccordionMenusByCondition(@WebParam(name = "userId")String userId,@WebParam(name = "systemCode")String systemCode);
	
	/**
	 * 根据用户Id 获取用户拥有的角色对应的系统代码
	 * 字段内容：[{"systemcode":"up_systemweb"}]
	 * @date 2017年8月27日 下午2:22:25
	 * @author 胡毅
	 * @param userId
	 * @return
	 */
	public String getSystemCodeByUserIdInRole( @WebParam(name = "userId")String userId);
	
	/**
     * 获取所有已定义的系统编码和名称
     * 字段内容：[{systemcode=up_systemweb, systemname=管网后台管理系统}]
     * @date 2017年8月27日 下午2:26:39
     * @author 胡毅
     * @return
     */
	public String getDefinedSystemCodeAndName();
	
	/**
	 * 根据用户ID从菜单视图中获取系统编码和名称，用于后台系统左侧菜单的tab数据
	 * 字段内容：{"list":[{"code":"up_systemweb","name":"管网后台管理系统"}]}
	 * @date 2017年8月28日 下午4:02:32
	 * @author 胡毅
	 * @param userId
	 * @return
	 */
	public String querySysCodeAndNameByUserId( @WebParam(name = "userId")String userId);
	
	/**
	 * 根据自定义sql和参数查询后台数据
	 * @date 2017年9月2日 上午11:52:30
	 * @author 胡毅
	 * @param sql
	 * @param paramsStr
	 * @return
	 */
	public String getListDataBySql(@WebParam(name = "sql")String sql, @WebParam(name = "paramsStr")String paramsStr);
	
	/**
	 * 保存登录日志
	 * @date 2017年9月2日 下午3:17:52
	 * @author 胡毅
	 * @param loginLog
	 */
	public void saveLoginLog(@WebParam(name="loginLog")String loginLog);
	
	/**
	 * 保存操作日志
	 * @date 2017年9月2日 下午3:29:25
	 * @author 胡毅
	 */
	public void saveLog(@WebParam(name="loginUserStr")String loginUserStr,@WebParam(name="uri")String uri,@WebParam(name="className")String className,
						@WebParam(name="methodName")String methodName,@WebParam(name="contentStr")String contentStr);
	
	public String validUserPwd(String loginId, String oldPwd, String inputPwd);
	
	/**
	 * 更新用户密码，只做更新时用
	 * @date 2017年9月17日 下午4:29:21
	 * @author 胡毅
	 * @param userStr
	 */
	public void saveUser(@WebParam(name="userStr")String userStr);
	
}
