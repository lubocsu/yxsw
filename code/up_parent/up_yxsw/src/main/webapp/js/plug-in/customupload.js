/**
 * 用于文件异步上传
 * 需引入：<script type="text/javascript" src="${path }/libs/js/form/upload/fileUpload.js"></script>
 *		<script type="text/javascript" src="${path }/libs/js/form/upload/handlers.js"></script>
 * 标准组件格式：<span id="uploadBtn1"></span>
 *          <div id="uploadList1"></div>
 */
// [{fileId,success,msg,oldName}],保存每次异步上传成功后的返回结果
var CustomUploadCallBackData_UseQUI = []; 	
// 修改文件大小限制后需要同时修改后台mvc上传限制
var CustomUploadmaxFileLimitKB = "204800";//（200M）单位KB
var CustomUploadmaxFileLimitTip = "单个文件最大限制200MB,请联系管理员修改文件限制";

function CustomUpload(){
	
	this.module = null;
	this.catalog = null;
	this.config = null;
	this.uploadUrl = null;
	this.deleteUrl = null
	
	/**
	 * module : 模块文件夹名
	 * catalog : 批次时间戳
	 * config ：{
	 * 		btn : '{btn}',
	 *  	listDiv : '{listDiv}'
	 *  	maxSize : 2048     // kb
	 *      fileTypes : '*.jpg;*.jpeg;*.bmp;*.gif;*.png;*.JPG;*.JPEG;*.BMP;*.GIF;*.PNG'
	 * }
	 * 
	 */
	this.init = function(module,catalog,config){
		this.module = module;
		this.catalog = catalog;
		this.config = config;
		this.uploadUrl = "/file/upload?module="+module;
		this.deleteUrl = "/file/delete?module="+module+"&catalog="+catalog+"&fileId="
		this.upload();
	};
	
	this.upload = function (){
		var _this = this;
		var uploadss = $.fileUpload.start({
	        contextPath: path,
	        buttonContainer: _this.config.btn,
	        fileListContainer: _this.config.listDiv,
	        moduleId : _this.module,
	        catalogId: _this.catalog,
	        uploadUrl:_this.uploadUrl,
	        deleteUrl:_this.deleteUrl,
//	        fileSize :_this.config.maxSize,
//			fileTypes : _this.config.fileTypes,
	        // 统一大小和文件类型
	        fileSize :CustomUploadmaxFileLimitKB, 
	        fileTypes :"*.*"
	    });
	};
}