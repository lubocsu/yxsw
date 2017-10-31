﻿   //顶级命名
   var yxapp = function(){};

   ///////////////////////ajax方法定义////////////////////////
   //ajax命名
   yxapp.ajax = function(){};
   //ip
   yxapp.basePath = AndroidProjectJSMethod.getBaseData("ip");
   //post请求
   yxapp.ajax.post = function(params){
	 var  defaultParams = {
     url : "",
     data : "",
     // 告诉jQuery不要去处理发送的数据
     processData : true,
     // 告诉jQuery不要去设置Content-Type请求头
     contentType : true,
     async : false,
	 success: function(result) {

				},
	 error: function(result){

				}
     };
      var paramsObj = $.extend(defaultParams,params);
      $.ajax({
				url:  yxapp.basePath+paramsObj.url,
				dataType: "json",
				async: paramsObj.async,
				contentType: paramsObj.contentType,
                processData: paramsObj.processData,
				contentType: "application/json; charset=utf-8",
				type: "post",
				data: JSON.stringify(paramsObj.data),
				cache: false,
				success: function(result) {
				    paramsObj.success(result);
				},
				error: function(result){
				    paramsObj.error(result);
				}
			});
   };


   //get请求
   yxapp.ajax.get = function(params){

	 var  defaultParams = {
     url : "",
     data : "",
     processData : true,
     contentType : true,
     async : false,
	 success: function(result) {

				},
	 error: function(result){

				}
     };

      var paramsObj = $.extend(defaultParams,params);
      $.ajax({
				url:  yxapp.basePath+paramsObj.url,
				async: paramsObj.async,
				contentType: paramsObj.contentType,
				processData: paramsObj.processData,
				dataType: "json",
				contentType: "application/json; charset=utf-8",
				type: "get",
				data: JSON.stringify(paramsObj.data),
				cache: false,
				success: function(result) {
				    paramsObj.success(result);
				},
				error: function(result){
				   paramsObj.error(result);
				}
			});
   };

   yxapp.ajax.postFile = function(params){
   	 var  defaultParams = {
   	 url : "",
   	 data : "",
   	 // 告诉jQuery不要去处理发送的数据
   	 processData : true,
   	 // 告诉jQuery不要去设置Content-Type请求头
   	 contentType : true,
   	 async : false,
   	 success: function(result) {

   				},
   	 error: function(result){

   				}
   	 };
   	  var paramsObj = $.extend(defaultParams,params);
   	  $.ajax({
   				url:  yxapp.basePath+paramsObj.url,
   				dataType: "json",
   				async: paramsObj.async,
   				contentType: paramsObj.contentType,
   	            processData: paramsObj.processData,
   				type: "post",
   					data: paramsObj.data,
   					cache: false,
   					success: function(result) {
   					    paramsObj.success(result);
   					},
   					error: function(result){
   					    paramsObj.error(result);
   					}
   				});
   	   };

    ///////////////////////ajax方法定义结束////////////////////////

    ///////////////////////webview方法定义////////////////////////
    //webview命名
     yxapp.webview = function(){};

    //返回上一页
    yxapp.webview.goBack = function (){
    		AndroidJsInterface.goBack();
    	};

    //关闭页面
    	yxapp.webview.closeWeb = function (){
    		AndroidJsInterface.close();
    	};



    	//左右按钮点击监听
        	yxapp.webview.buttonOnClickListener = {
        		 leftOnclick : function(){
                  },
                 rightOnclick : function(){
                  },
                 menuOnClick : function (data){}
        	};


        //网页参数设置
            	yxapp.webview.setWebParams = function(params){
            	   yxapp.webview.buttonOnClickListener=params;
            	    AndroidJsInterface.setWebParams(
                                                      getValue(params.title),
                                                      getValue(params.textColor),
                                                      getValue(params.backColor),
                                                      getValue(params.size),
                                                      getValue(params.state),
                                                      getValue(params.url),
                                                      getValue(params.type),
                                                      getValue(params.leftImg),
                                                      getValue(params.rightImg),
                                                      (params.menulist==undefined)?null:params.menulist
                                                      );
                	};
                	//展示或隐藏菜单
                yxapp.webview.showOrHideMenu = function (){
                  AndroidJsInterface.showOrHideMenu();
                };

                //公用的按钮点击事件
        	    yxapp.webview.commonButtonOnClickListener = {
        		  buttonOnclick : function(data){
                  }

        	};

			
			
			
                 //确认框展示
	yxapp.webview.showConfirm = function(params){
	yxapp.webview.commonButtonOnClickListener=params;
	  AndroidJsInterface.showConfirm(getValue(params.title),getValue(params.message),getValue(params.buttonA),getValue(params.buttonB));
	};

	 //列表选择框展示
	yxapp.webview.showListSelect = function(params){
	  yxapp.webview.commonButtonOnClickListener=params;
	  AndroidJsInterface.showSelect(getValue(params.title),getValue(params.list),getValue(params.buttonA),getValue(params.buttonB));
	};
    //进度框按钮点击事件
    yxapp.webview.progressButtonOnClick = {
        buttonOnclick : function(data){
                        }
    };

	//显示进度条
	yxapp.webview.showProgress = function(params){
	      yxapp.webview.commonButtonOnClickListener=params;
		   AndroidJsInterface.showProgressDialog(
             getValue(params.type),
             getValue(params.title),
             getValue(params.message),
             getValue(params.max),
             getValue(params.buttonA),
             getValue(params.buttonB),
             getValue(params.state));
	};

	//更新进度条
	yxapp.webview.updateProgress = function(max,pro){
		 AndroidJsInterface.updateProgressDialog(max,pro);
	};

	 //隐藏进度条
	yxapp.webview.hideProgress = function(){
		AndroidJsInterface.hideProgressDialog();
	};
//显示通知栏
	yxapp.webview.showNotification = function (params){
	  AndroidJsInterface.showNotification(
      getValue(params.title),
      getValue(params.message),
      getValue(params.ticker),
      getValue(params.max),
      getValue(params.pro),
      getValue(params.code),
      getValue(params.state));
	};

	//更新通知栏
	yxapp.webview.updateNotification = function (max,pro,code){
	  AndroidJsInterface.updateNotification(max,pro,code);
	};

	//隐藏通知栏
	yxapp.webview.hideNotification = function (code){
	  AndroidJsInterface.hideNotification(code);
	};

	
///////////////////////storage方法定义////////////////////////
	 //存储命名
	 yxapp.storage = function(){};

	 //sql操作
	 yxapp.storage.sql = function (sql){
		 return AndroidProjectJSMethod.excuteSql(sql)
	 };

	 //keyvalue 取
     yxapp.storage.getKeyValue = function(type,key){
      return AndroidJsInterface.searchKeyValue(type,key);
     };
     //keyValue存储
     yxapp.storage.saveKeyValue = function(type,key,value){
      return AndroidJsInterface.saveKeyValue(type,key,value);
     };
     //keyvalue 刪
     yxapp.storage.delKeyValue = function(type,key){
       return  AndroidJsInterface.delKeyValue(type,key);
      };
	///////////////////////storage方法定义结束////////////////////


   ///////////////////////device方法定义////////////////////////
   	 //设备方法命名
   	  yxapp.device = function(){};

   	    //获取照片
           yxapp.device.defaultTakePictureParams =  {
             type: 0, //0-拍照   1-相册
             getPictureUrl : function(data){}
             };

   		//获取照片地址
   		yxapp.device.getPicture = function(obj){
            yxapp.device.defaultTakePictureParams = obj;
            AndroidJsInterface.getPicture(obj.type);
           };
	 //摄像
      yxapp.device.defaultTakeVideoParams = {
          getVideoUrl : function(data){}
     };

	//获取摄像后视频保存的地址
    yxapp.device.takeVideo = function(obj){
    yxapp.device.defaultTakeVideoParams = obj;
    AndroidJsInterface.startVideo();
    };

	//播放视频
	yxapp.device.playVideo = function(videoPath){
     AndroidJsInterface.playVideo(videoPath);
    };



        //录音
         yxapp.device.defaultRecordParams = {
            getRecordUrl:function(data){}
           };

         //开始录音
         yxapp.device.startRecord = function(params){

            yxapp.device.defaultRecordParams=params;
            return AndroidJsInterface.startRecord();
         };

    	 //暂停录音
    	  yxapp.device.pauseRecord = function(){
            return AndroidJsInterface.pauseRecord();
         };
    	 //继续录音
    	  yxapp.device.continueRecord = function(){
            return AndroidJsInterface.continueRecord();
         };
    	 //停止录音
          yxapp.device.stopRecord = function(){
            return AndroidJsInterface.stopRecord();
         };

    	 //播放录音
    	  yxapp.device.playRecord = function(recordUrl){
            AndroidJsInterface.playRecord(recordUrl);
         };

         //拨打电话
        	  yxapp.device.callPhone = function(phoneNumber, type){
                AndroidJsInterface.call(phoneNumber,type);
             };
        	//nfc扫描

             yxapp.device.defaultNfcScanParams = {
                   type :0,//0 直接返回数据  1 跳入另外一个界面展示数据
                   className:".Activity", //Activity全类名
                   getNfcId : function(nfcId){}
               };

        	  yxapp.device.nfcScan = function(params){
        		   var paramsObj = $.extend(yxapp.device.defaultNfcScanParams,params);
        	       AndroidJsInterface.nfcScan(paramsObj.type, paramsObj.className);
               };
               //查看大图
                yxapp.device.showPic = function(picArr,position){
                 AndroidProjectJSMethod.intentToPicDetail(picArr,position);
                };


	///////////////////////base方法定义结束////////////////////


	 ///////////////////////base方法定义////////////////////////
    	 //其它命名
    	 yxapp.base = function(){};

    	 //打开Activity
         yxapp.base.openActivity = function(params) {
    		 AndroidJsInterface.openActivity(
    		 getValue(params.activityClassName),
    		 getValue(params.action),
    		 getValue(params.params));
    	 };

    	 //获取基础参数值
    	 yxapp.base.getBaseData = function(key){
    		 return AndroidProjectJSMethod.getBaseData(key);
    	 };
		 
		 
		 

    	///////////////////////base方法定义结束////////////////////
		
		
		///////////////////////project方法定义////////////////////////
		 //
    	 yxapp.project = function(){};

			//刷新工单数据
			yxapp.project.refreshOrderData = function(){
            AndroidProjectJSMethod.refreshOrderList();
           };
		 
		 
//		  yxapp.project = function(){};

			//返回到主页
			yxapp.project.toIndex = function(){
            AndroidProjectJSMethod.toIndex();
           };
		   
		   
		   //在地图上展示点
			yxapp.project.showPointOnMap = function(lat,lon){
            AndroidProjectJSMethod.showMapLocation(lat,lon);
           };
		   

		    //播放在线视频
			yxapp.project.playVideo = function(url){
            AndroidProjectJSMethod.playVideo(url);
           };
           //跳转到登陆
           yxapp.project.toLogin = function(){
                AndroidProjectJSMethod.toLogin();
           };
           //下载或打开文件
           yxapp.project.downOrOpenFile = function(url){
                    AndroidProjectJSMethod.downOrOpenFile(url);
           };
		///////////////////////project方法定义结束////////////////////////

 function getValue(obj){
        return (obj==undefined)?"":obj;
 };

