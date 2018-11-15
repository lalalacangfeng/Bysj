/**
 * 
 */

function test(){
//	alert("11！");  
	
	if($("#name").val()==""){
		alert("视频文件名不能为空!")
		return false;
	}
	if($("#descript").val()==""){
		alert("请添加视频描述!")
		return false;
	}
	
	
	//文件类型
	var filetypes = [".asx", ".asf", ".mpg",".wmv",".3gp",".mp4",".mov",".avi",".flv"]; 

	var filepath = document.getElementById("file").value;
//	alert(filepath)
	//正则表达式判断上传视频格式
	if (!document.getElementById("file") || !filepath){
		alert("请上传文件。");
		return;
	}
	var patn = /\.asx$|\.asf$|\.mpg$|\.wmv$|\.3gp$|\.mp4$|\.avi$|\.flv$/i;
	if(!patn.test(filepath)){
		alert("暂不支持该格式。只支持asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等格式文件。");
		filepath = "";
		return;
	}
	else{
		//判断文件上传大小
		var fileSize = document.getElementById("file").files[0].size;
		var size = fileSize/1024;
		var filemaxSize = 500 * 1024 * 1024;//500M
//		alert(fileSize+"   "+size+"   "+filemaxSize);
		if(size > filemaxSize){
			alert('视频大小不能大于' + filemaxsize / 1024 +'M！\r\n', );
			return false;
		}
		else{
//			alert("11！");  
			var form = new FormData(document.getElementById("uploadmedia"));
			$.ajax({
				url:"media/addmedia",
				type:"post",
				dataType:"text",//暂时使用text，待改为json
				data : form,
				async : false,
				cache:false,
				processData:false,
				contentType: false,  
				success:function(result){  
					console.log(result);
					alert("操作成功！");
					//parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
					window.location.href ="media/medias.jsp";
				},  
				error:function(XMLHttpRequest, textStatus, errorThrown){  
					//alert(XMLHttpRequest.status);
					//alert(XMLHttpRequest.readyState);
					//alert(textStatus); 
					alert("网络错误，请重试！！");  
				}  
				
			});
				
		}
			
	}

	
}