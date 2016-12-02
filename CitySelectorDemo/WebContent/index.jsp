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
$( "#keyWord" ).autocomplete({
	minLength: 2,
	delay: 500,
    source: function( request, response ) {
        $.ajax({
            url: "search",
            type:"POST",
            dataType: "json",
            data:{
            	keyWord : request.term
            },
            success: function( data ) {
                response( $.map( data, function( item ) {
                    return {
                    	label:item.lbnkNm,
                    	value:item.LbnkNo
                    }
                }));
            }
        });
    },
      select: function( event, ui ) {
    	console.log(ui.item);
       $("#keyWord").val(ui.item.bankNm);
    }
});

</script>
</body>
</html>