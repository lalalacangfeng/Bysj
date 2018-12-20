/**
 * 上传图片
 */

var optionstring = document.getElementById('optionsRadios');// 获取上传类型
var vedio = document.getElementById('vedio');// 视频区域
var picture = document.getElementById('picture');// 图片区域

//判断文件格式
function checkType(fileName){
	//文件类型
    var patn = /\.jpg$|\.png$|\.jpeg$|\.bmp$|\.gif$/i;
	if(!patn.test(fileName)){
		alert("暂不支持改革是");
		return false;
	}
	return true;
}


/**
 * 上传按钮 负责上传视频或图片
 */
function test() {

	alert(form);
	if ($("#name").val() == "") {
		alert("文件名不能为空!");
		return false;
	}
	if ($("#descript").val() == "") {
		alert("请添加描述!");
		return false;
	}

	// 表单
	var form = new FormData(document.getElementById("uploadmedia"));

		// 上传图片
		$.ajax({
			type : "post",
			url : "admin/media?action=pict",
			dataType : "json",
			data : form,
			async : false,
			cache : false,
			processData : false,
			contentType : false,
			success : function(result) {
				console.log(result.ifExist);
				if (result.ifExist == 1) {
					alert("上传成功！");
					window.location.href = "admin/media?action=update";
				} else if (result.ifExist == 2) {
					alert("上传失败！");
				}
				// parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
				// window.location.href ="manage.jsp";
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				// alert(XMLHttpRequest.status);
				// alert(XMLHttpRequest.readyState);
				// alert(textStatus);
				alert("网络错误，请重试！！");
			}

		});

}