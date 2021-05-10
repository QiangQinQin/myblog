$(function(){
	loadNextPage(99999999999);

	// $(window).scroll(function(){
	// 	checkload();
	// });
	
//建立加载判断函数
function checkload(){
	totalheight = parseFloat($(window).height()) + parseFloat($(window).scrollTop());
    if ($(document).height() <= totalheight) {
    	if($("#lastId").val() != 'undefined' && $("#lastId").val() != null && $("#lastId").val() != ''){
        	loadNextPage($("#lastId").val());
    	}
    }
}

//创建ajax加载函数，并设置变量C，用于输入调用的页面频道,请根据实际情况判断使用，非必要。
function loadNextPage(lastId){
		$.ajax({
									// url : '../article/loadPage/'+$("#categoryId").val()+'/'+lastId,
        //..不然报错：GET http://localhost:8080/loadPage/loadPage1/1 404   (Not Found)
			                        url:'../loadPage1/'+$("#categoryId").val(), //从articlepager1.jsp过来，提交给HomeController处理，按类别加载子页面
									type : "get",
									success : function(data) {
										if($.trim(data) != ''){
											$("#lastId").remove();
											$("#content").append(data);
											$('img').lazyload();
										}
									}
		});	
}  
});  

// function goTag(tagName){
// 	window.location.href = "http://coriger.com/tag/"+encodeURI(encodeURI(tagName))
// }

//暂时用不到
// function getRootPath() {
// 	//获取当前网址，如： http://localhost:8080/GameFngine/share/meun.jsp
// 	var curWwwPath = window.document.location.href;
// 	//获取主机地址之后的目录，如： GameFngine/meun.jsp
// 	var pathName = window.document.location.pathname;
// 	var pos = curWwwPath.indexOf(pathName);
// 	//获取主机地址，如： http://localhost:8080
// 	var localhostPaht = curWwwPath.substring(0, pos);
// 	//获取带"/"的项目名，如：/GameFngine
// 	var projectName = pathName.substring(0,
// 			pathName.substr(1).indexOf('/') + 1);
// 	return (localhostPaht + projectName + "/");
// }
