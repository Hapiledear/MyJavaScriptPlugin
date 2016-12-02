<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="jquery-ui.css" rel="stylesheet" type="text/css" />  
<style>
  .ui-autocomplete {
    max-height: 300px;
    overflow-y: auto;
    /* 防止水平滚动条 */
    overflow-x: hidden;
  }
  /* IE 6 不支持 max-height
   * 我们使用 height 代替，但是这会强制菜单总是显示为那个高度
   */
  * html .ui-autocomplete {
    height: 300px;
  }
  </style>
</head>
<body>
<div class="gover_search">
	<form action="search" method="get" id="searchForm">
	开户行:<input type="text" id="keyWord" name="keyWord" autocomplete="off">
	<input type="submit" value="查询">
	</form>
	
	<div class="search_suggest" id="gov_search_suggest">  
         <ul>  
         </ul>  
    </div>  
</div>

<div id="result" >
	<table id="list">
		
	</table>
</div>
<script src="jquery.js"></script>
<script src="jquery-ui.js"></script>
<script>
//http://www.ziqiangxuetang.com/jqueryui/example-autocomplete.html


var dataArray = [];
var keyWord = "";
var pageNum = 1;
var pageSize = 10;
$( "#keyWord" ).autocomplete({
	minLength: 2,
	delay: 500,
    source: function( request, response ) {
    	pageConfigInit(request.term);
        $.ajax({
            url: "search",
            type:"POST",
            dataType: "json",
            data:{
            	keyWord : request.term,
            	pageNum : pageNum,
            	pageSize:pageSize
            },
            success: function( data ) {
            	pageConfigUpdate(data);
            	dataArray = [];
            	for(var i =0 in data){
                	var item = data[i];
                	dataArray.push({
                        bankNm:item.lbnkNm,
                        bankNo:item.lbnkNo
                	});
                }
            	response(dataArray);
            }
        });
    },
    select: function( event, ui ) {
    	console.log(ui.item);
    	 $("#keyWord").val(ui.item.bankNm);
       return false;//阻止控件自动填写而防止覆盖
    },
    change : function(event,ui){
    	console.log("change");
    },
    close : function(event,ui){
    	console.log("close");
    },
    create :function(event,ui){
    	console.log("create");
    },
    focus : function(event,ui){
    	console.log("focus");
    },
    open : function(event,ui){
    	console.log("open");
    },
    response : function(event ,ui){
    	console.log("response");
    },
    search : function(event,ui){
    	console.log("search");
    }
 
});
$("#keyWord").autocomplete( {}).data("ui-autocomplete")._renderItem = function( ul, item ) {
	//每一个item的显示风格
    return $( "<li>" )
    .append( "<a>" + item.bankNm + "<br>" + item.bankNo + "</a>" )
    .appendTo( ul );
};

$("#keyWord").autocomplete( {}).data('ui-autocomplete')._renderMenu = function(ul, items) {
	  var self = this;
		//menu的显示风格
	  ul.append("<div class='pagination' onclick='page(this);return true;'> 分页</div>");
	  ul.append("<hr />")
	  $.each( items, function( index, item ) {
		self._renderItemData( ul, item );//将数据向下传递
	  });
	};	
	
function pageConfigInit(newWord){
	var strK = keyWord.replace(/\s+/g,"");
	var strN = newWord.replace(/\s+/g,"");
	if(strN != strK){
		keyWord = newWord;
    	pageNum = 1;
    	pageSize= 10;
	}
/* 	console.log("keyword:"+keyWord);
	console.log("newWord:"+newWord);
	console.log("pageNum:"+pageNum);
	console.log("pageSize:"+pageSize); */
}
//页面相关信息的更新
function pageConfigUpdate(data){
	
}
/* var $widget = $("#keyWord").autocomplete( "widget" );
$widget.append("<div class='pagination'> 分页</div") */
var $data  = $("#keyWord").autocomplete( {}).data('ui-autocomplete');

function page(obj){
	pageNum++;
	console.log("点击了分页");
	dataArray = [];

	$('#keyWord').autocomplete("search")

}


</script>
</body>
</html>