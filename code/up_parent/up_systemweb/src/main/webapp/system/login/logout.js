// 循环清楚所有子系统session，然后调用后台退出方法，注销session和后台认证数据并跳到平台登录页面
function logout(){
	for (var i = 0; i < _owned_pc_systemcodes.length; i++) {
		$.ajax({url:"/"+_owned_pc_systemcodes[i].systemcode+"/login/logOut2",async:false});
//		if(!(("/"+_owned_pc_systemcodes[i].systemcode)==path)){
//		}
	}
	window.location=path+"/login/logOut?tokenId="+tokenId
}