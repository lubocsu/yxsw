//plugin definition
(function($){
    $.fn.extend({
	    menuAccordion: function(options){
	    	var datas = options.datas;
	    	var $this = $(this);
	    	function createNode(menus){
	    		var ul = $('<ul></ul>');
	    		$.each(menus,function(idx,dom){
	    			var li = $('<li>');
	    			$('<a>',{
	    				target: dom.target
	    			}).appendTo(li);
	    			var children = dom.children;
	    			if(children){
	    				createNode(createNode);
	    			}
		    	});
	    	}
	    }
	});
})(jQuery);