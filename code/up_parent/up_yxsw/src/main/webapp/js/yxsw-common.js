/**
 * pis通用JavaScript
 */

jQuery(function(){
	
	/*隐藏展开功能*/
	jQuery(".to_hide_over_content").live("click",function(){
		
		var tipicon = jQuery.trim(jQuery(this).find("span:eq(2)").text());
		if(tipicon=="∨"){
			
			jQuery(this).find("span:eq(0)").text("展开");
			jQuery(this).find("span:eq(2)").text("∧");
			jQuery(this).next(".detail-list, .padding_right5").hide();
		}else{
			jQuery(this).find("span:eq(0)").text("隐藏");
			jQuery(this).find("span:eq(2)").text("∨");
			jQuery(this).next(".detail-list, .padding_right5").show();
		}
	});
});

function getBackUrl(form, grid){
	
	var params = form.serialize();
	console.log(params);
	var backUrl = location.pathname + "?" + form.serialize() + "&pager.pageNo=" + grid.options.page + "&pager.pageSize=" + grid.options.pageSize;;
	return encodeURIComponent(backUrl, "UTF-8");
}