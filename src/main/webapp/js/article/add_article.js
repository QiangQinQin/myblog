// 保存文章
function saveArticle(){
    var param = {}; //存放 标题  类别  标签列表等信息

    // 收集参数（id唯一；不传名字，为避免重复） 校验
    //                $("#categoryId").val() 是<select>里的id
    var categoryId = $("#categoryId").val();
    if(categoryId == '-1'){
    	autoCloseAlert("请选择栏目",500);
    	return false;
    }
    param["categoryId"] = categoryId;
    
    var title = $("#title").val();
    //判空方法来自E:\tulun\myblog\src\main\webapp\js\validation.js
    if(isEmpty(title)){
    	autoCloseAlert("请输入标题",500);
    	return false;
    }
    param["title"] = title;
    
    var arr = [];
    arr.push(UE.getEditor('editor').getContent());//富文本编辑器内容包含标签等信息
    var content = arr.join("\n");
    
    // 纯文本简介（无格式）
    var description = UE.getEditor('editor').getContentTxt().substring(0,20);
    
    // 标签（可多选）
    var tagId = $(".chosen-select").val();
    // alert(tagId);
    if(!isEmpty(tagId)){
        //即先变字符串，然后分割成  {"id":"5"}   {"id":"6"}
    	var ids = (tagId+"").split("\,");
    	var tagArray = [];
    	for(var i=0;i<ids.length;i++){
    		tagObj = {"id":ids[i]};//好像必须得这种格式
    		// Alert(tagObj.id);
    		tagArray.push(tagObj);
    	}
    	param["tagArray"] = tagArray;
    	console.info(tagArray);
    }else{
    	autoCloseAlert("请输入标签",500);
    	return false;
    }
    // alert('param='+encodeURI(encodeURI(JSON.stringify(param))));
    // 保存文章
    $.ajax({
        type : "POST",
        url : '../article/addContent',
        //encodeURI（）相当于对前端传输的数据以某种方式进行加密
        data : 'param='+encodeURI(encodeURI(JSON.stringify(param)))+"&content="+encodeURI(encodeURI(content)).replace(/\&/g, "%26").replace(/\+/g, "%2B")+"&description="+encodeURI(encodeURI(description)),
        success  : function(data) {  //sucess表示传递到后台给我返回的数据（json）
        	if(data.resultCode != 'success'){ //状态码
        		autoCloseAlert(data.errorInfo,1000);
				return false;
			}else{
				// 调到列表页 URL 控制器？？？？？？？？？？
				// window.location.href = "../article/list";
                //即http://localhost:8080/article/addContent 到了 http://localhost:8080
                window.location.href = "../../../";
			}
		}
    });
}

function cancelSaveArticle(){
	window.location.href = "../article/list";
}