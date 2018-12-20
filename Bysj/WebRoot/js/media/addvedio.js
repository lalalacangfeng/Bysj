/**
 * 添加视频js
 */
var optionstring = document.getElementById('optionsRadios');//获取上传类型
var vedio = document.getElementById('vedio');//视频区域
var picture = document.getElementById('picture');//图片区域

/**
 * 上传按钮
 * 负责上传视频或图片
 */
function test(){

    if($("#name").val()==""){
        alert("文件名不能为空!");
        return false;
    }
    if($("#descript").val()==""){
        alert("请添加描述!");
        return false;
    }
    
    //文件类型
    var patn = /\.asx$|\.asf$|\.mpg$|\.wmv$|\.3gp$|\.mp4$|\.avi$|\.flv$/i;
    //var filetypes = [".asx", ".asf", ".mpg",".wmv",".3gp",".mp4",".mov",".avi",".flv"]; 

    var filepath = document.getElementById("file").value;
    //  alert(filepath)
    
    //正则表达式判断上传视频格式
    if (!document.getElementById("file") || !filepath){
        alert("请上传文件。");
        return;
    }
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
//          alert(fileSize+"   "+size+"   "+filemaxSize);
        if(size > filemaxSize){
            alert('视频大小不能大于' + filemaxsize / 1024 +'M！\r\n' );
            return false;
        }
        else{
            var bars = document.getElementById('progressbar');//进度条
            bars.style.display = "";//显示进度条
            // alert("上传视频");
            var form = new FormData(document.getElementById("uploadmedia"));
            $.ajax({
                type:"post",
                url:"admin/media?action=vedio",
                dataType:"json",//暂时使用text，待改为json
                data : form,
                async : false,
                cache:false,
                processData:false,
                contentType: false,  
                success:function(result){  
                    console.log(result.ifExist);
                    if(result.ifExist == 1){
                        bars.style.display = "none";//隐藏进度条
                        alert("上传成功！视频转码中，请稍后再看！");
                        window.location.href ="admin/media?action=update";
                    }
                    else if(result.ifExist == 2){
                        alert("上传失败！");
                        bars.style.display = "none";//隐藏进度条
                    }
                    else if(result.ifExist == 3){
                        alert("上传失败！标题或视频已经存在！");
                        bars.style.display = "none";//隐藏进度条
                    }
                    //parent.location.reload();//parent.location.reload()刷新父亲对象（用于框架）
//                      window.location.href ="manage.jsp";
                },  
                error:function(XMLHttpRequest, textStatus, errorThrown){  
                    //alert(XMLHttpRequest.status);
                    //alert(XMLHttpRequest.readyState);
                    //alert(textStatus); 
                    alert("网络错误，请重试！！");
                    bars.style.display = "none";//隐藏进度条
                }  
            });
        }
    }
}