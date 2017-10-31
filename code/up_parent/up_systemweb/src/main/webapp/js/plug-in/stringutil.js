function strNVL(str){
	var flag = true;
	if(typeof str != "null"&& typeof str != "undefined"){
		if((str.replace(/(^s*)|(s*$)/g, "").length ==0)){
			flag =false;
		}
	}else{
		flag = false;
	}
	return flag;
}