/**
 * ajax文件上传公共组件(需引入jQuery-2.1.4.min.js和ajaxfileupload.js ajaxfileupload.js已经改造过支持多附件同时上传 )
 * @param callback 回调函数
 * @param filegroup	附件所属组(用于上传一组附件 非必填项)
 */
function ajaxFileUpload(callback,filegroup) {
	if(typeof(filegroup) == "undefined" || filegroup == "" || filegroup == null || filegroup == "null"){
		filegroup = "";
	}
	var ids = new Array();
	$("input[type='file']").each(function(i){
		var file = $(this);
		var value = file.val();
		if(typeof(value) == "undefined" || value == "" || value == null || value == "null"){
			return;
		}
		var id = file.attr("id");
		var name = file.attr("name");
		if(typeof(id) != "undefined" && id != "" && id != null  && id != 'null' && typeof(name) != "undefined" && name != "" && name != null  && name != 'null'){
			ids.push(id);
		}
	});
	
    $.ajaxFileUpload
    (
        {
            url: '/selforder/api/fileutil?method=upload&callback='+callback+'&filegroup='+filegroup, //用于文件上传的服务器端请求地址
            secureuri: false, //一般设置为false
            //fileElementId: 'file1', // 原来只支持一个附件上传 文件上传空间的id属性  <input type="file" id="file" name="file" />
            fileElementId: ids, // 原来只支持一个附件上传 文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json' //返回值类型 一般设置为json
        }
    )
}

/**
 * 日期格式化操作  原创链接http://blog.csdn.net/vbangle/article/details/5643091/
 * @param fmt
 * @returns
 */
//对Date的扩展，将 Date 转化为指定格式的String 
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) 
{ //author: meizz 
var o = { 
 "M+" : this.getMonth()+1,                 //月份 
 "d+" : this.getDate(),                    //日 
 "h+" : this.getHours(),                   //小时 
 "m+" : this.getMinutes(),                 //分 
 "s+" : this.getSeconds(),                 //秒 
 "q+" : Math.floor((this.getMonth()+3)/3), //季度 
 "S"  : this.getMilliseconds()             //毫秒 
}; 
if(/(y+)/.test(fmt)) 
 fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
for(var k in o) 
 if(new RegExp("("+ k +")").test(fmt)) 
fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
return fmt; 
}

/**
 * 根据时间戳和日期格式返回字符串日期
 */
function getDateByTime(time,format){
	if(checkValue(time)){
		var date = new Date();
		date.setTime(time);
		var dateStr = date.Format(format);
		return dateStr;
	}else{
		return "";
	}
}

/**
 * 验证值
 * @param value 待验证的值
 * @param remark 验证失败后的提示
 * @returns true 验证成功   false 验证失败  checkValueWithInfo
 */
function checkValueWithInfo(value,remark){
	//默认提示
	if(typeof(remark) == "undefined" || remark == null || remark == "" || remark == "null"){
		remark = "参数验证失败！";
	}
	if(typeof(value) != "undefined" && value != null && value != "" && value != "null"){
		return true;
	}else{
		layer.msg(remark,{icon:5});
		return false;
	}
}

/**
 * 验证值
 * @param value
 * @returns
 */
function checkValue(value){
	if(typeof(value) != "undefined" && value != null && value != "" && value != "null"){
		return true;
	}else{
		return false;
	}
}

/**
 * 显示请求执行结果
 * @param retCode  0 成功  -1 失败
 * @param message  执行结果的详细描述
 */
function showResult(retCode,message){
	if(retCode < 0){
		layer.msg(message,{icon:5});
	}else{
		layer.msg(message,{icon:6});
	}
}

/**
 * json日期对象转javascript标准日期对象
 * @param jsonData
 * @returns {String}
 * eg：json日期对象：{"date":30,"day":3,"hours":0,"minutes":0,"month" :0,"seconds":0,"time":1201622400000,"timezoneOffset":-480,"year":108}
 * 根据time值转换为标准格式日期：var date = new Date( parseInt( time )); = 2015-3-5 12:12:23
 */
function formatDate(jsonData){
	var time = jsonData.time;
    var date = new Date(time);
    var str = date.getFullYear() + "-";
    str += ((date.getMonth()+1)<10?("0"+(date.getMonth()+1)):(date.getMonth()+1)) +"-" ;
    str += (date.getDate()<10?("0"+ date.getDate()): date.getDate()) +" ";
    str += (date.getHours()<10?("0"+date.getHours()):date.getHours()) + ":";
    str += (date.getMinutes()<10?("0"+date.getMinutes()):date.getMinutes()) + ":";
    str += date.getSeconds()<10?("0"+date.getSeconds()):date.getSeconds();
    return str;
}

function formatDateNoWithTime(jsonData){
	var time = jsonData.time;
    var date = new Date(time);
    var str = date.getFullYear() + "-";
    str += ((date.getMonth()+1)<10?("0"+(date.getMonth()+1)):(date.getMonth()+1)) +"-" ;
    str += (date.getDate()<10?("0"+ date.getDate()): date.getDate());
    return str;
}

/**
 * 检查附件格式及大小
 * @returns
 */
function checkFile(file,showid){
	// 判断是否为IE浏览器： /msie/i.test(navigator.userAgent) 为一个简单正则
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    var filepath = "";
    var fileSize = 0;
    //判断附件格式
    var str = $(file).val();
    var reg = ".*\\.(jpg|png|gif|JPG|PNG|GIF|swf)";
    if(str.length>0){
    	var r = str.match(reg);
    	if(r == null){
    		layer.msg("只能上传.jpg、.png、.gif类文件！",{icon:5});
    		clearInputFile(file);
    		return false;
	    }
    }
    var r = str.match(reg);
    if (isIE && !file.files) {    // IE浏览器
    	filepath = file.value; // 获得上传文件的绝对路径
        /**
         * ActiveXObject 对象为IE和Opera所兼容的JS对象
         * 用法：
         *         var newObj = new ActiveXObject( servername.typename[, location])
         *         其中newObj是必选项。返回 ActiveXObject对象 的变量名。
         *        servername是必选项。提供该对象的应用程序的名称。
         *        typename是必选项。要创建的对象的类型或类。
         *        location是可选项。创建该对象的网络服务器的名称。
         *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
         *    Scripting.FileSystemObject 为 IIS 内置组件，用于操作磁盘、文件夹或文本文件，
         *    其中返回的 newObj 方法和属性非常的多
         *    如：var file = newObj.CreateTextFile("C:\test.txt", true) 第二个参表示目标文件存在时是否覆盖
         *    file.Write("写入内容");    file.Close();
         */
        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
        // GetFile(path) 方法从磁盘获取一个文件并返回。
        var file = fileSystem.GetFile(filePath);
        fileSize = file.Size;    // 文件大小，单位：b
    }
    else {    // 非IE浏览器
        fileSize = file.files[0].size;
    }
    var size = fileSize/1024/1024;
    if (size > 1) {
    	layer.msg("上传的附件大小不能超过1M",{icon:5});
    	clearInputFile(file);
    	return false;
    }else{
    	//如果显示图片的元素ID存在，则显示出所选的附件
    	if(checkValue(showid)){
    		var reader = new FileReader();
        	reader.readAsDataURL(file.files[0]);
        	reader.onload = function(){
        		$("#"+showid).attr("src",reader.result);
        	};
    	}
    	return true
    }
}

/*
 * 情况file元素中内容
 */
function clearInputFile(f){  
    if(f.value){  
        try{  
            f.value = ''; //for IE11, latest Chrome/Firefox/Opera...  
        }catch(err){  
        }  
        if(f.value){ //for IE5 ~ IE10  
            var form = document.createElement('form'), ref = f.nextSibling, p = f.parentNode;  
            form.appendChild(f);  
            form.reset();  
            p.insertBefore(f,ref);  
        }  
    }  
} 

/**
 * 将内容复制至粘贴板
 * @param elmentId
 * @param value
 * @returns
 * 使用要求：需引入jquery和jquery.zclip.min.js
 * 使用时直接传入触发复制的元素ID和需要复制的内容
 */
function copyText(elmentId,value){
	$('#'+elmentId).zclip({
        path: cxtPath+"/js/ZeroClipboard.swf",
        copy: function(){
	        return value;
	　 　}, 
		afterCopy: function(){//复制成功 
			layer.msg("内容已复制至粘贴板",{icon:6});
		}
    });
}
