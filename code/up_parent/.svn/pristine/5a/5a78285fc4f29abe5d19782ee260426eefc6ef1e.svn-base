function logout(){
	for (var i = 0; i < _owned_pc_systemcodes.length; i++) {
		$.ajax({url:"/"+_owned_pc_systemcodes[i].systemcode+"/login/logOut2",async:false});
//		if(!(("/"+_owned_pc_systemcodes[i].systemcode)==path)){
//		}
	}
	window.location=path+"/login/logOut?tokenId="+tokenId
}