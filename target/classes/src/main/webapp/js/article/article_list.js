$(function () {
    loadNextPage(99999999999);//到id为99999999999时分页显示


//创建ajax加载函数，并设置变量C，用于输入调用的页面频道,请根据实际情况判断使用，非必要。
    function loadNextPage(lastId) {
        $.ajax({
            // url: 'article/loadPage/-1/' + lastId,
            url:'loadPageAll', //从home/index.jsp过来的, HomeController加载所有文章 子页面
            type: "get",
            success: function (data) {
                if ($.trim(data) != '') {
                    $("#lastId").remove();
                    $("#content").append(data);
                    $('img').lazyload();
                }
            }
        });
    }
});  

// /*function goTag(tagName){
// 	window.location.href = "http://coriger.com/tag/"+encodeURI(encodeURI(tagName))
// }*/
//
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