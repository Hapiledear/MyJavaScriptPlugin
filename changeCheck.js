
	var settings = {
			objStr :"",
			hitColor : "#FFC408",
			primaryColor : "#D5D5D5"
	};
	var propArray = [];
	var valueArray = [];
	
	function init(obj){
		parse(obj);
		saveOldData(objStr);
		bindKeyUpEvent();
		//缺省参数
	}
	
	function parse(obj){
		if("objStr"	in obj) settings.objStr = obj.objStr;
		if("hitColor" in obj) settings.hitColor = obj.hitColor;
		if("primaryColor" in obj) settings.primaryColor = obj.primaryColor;
	}
	function saveOldData(dataObj){
		var regProp = /\w+(?==)/g;
		var regVal = /[\w\s:\u4e00-\u9fa5]+(?=,|})/g;
		
		 propArray = dataObj.match(regProp);
		 valueArray = dataObj.match(regVal);
		 
		for(var i in valueArray){
			valueArray[i] = formatDate(valueArray[i]);
		}
 		console.log(propArray.toString());
		console.log(valueArray.toString()); 
		
	}

	function formatDate(value){
		var reg = /\sCST\s/g;
		if(typeof(value) == "string" && reg.test(value)){
			return CSTtoUTCe8(value);
		}
		return value;
	}
	
	function CSTtoUTCe8(cstStr){
		var date = cstStr.replace(/\s/g,":").split(":");
		var utcDate = date[0]+" "+date[1]+" "+date[2]+" "+date[7]+" "+date[3]+":"+date[4]+":"+date[5]+" GMT+0800 (中国标准时间)";
		return utcDate;
	}
	
	function isDateFormat(str){
		var reg = /\sGMT/g;
		return reg.test(str);
	}
	
	function bindKeyUpEvent(){
		for(var i in propArray){
			var $obj = $("[name='"+propArray[i]+"']");
			$obj.attr("_index",i);
			if(isDateFormat(valueArray[i])){
				$obj.attr("format","date");
			}
			
			$obj.keyup(function(){
				var format = $obj.attr("format");
				var newValue = $obj.val();
				if(format == "date"){
					newValue = new Date(newValue).toString();
				}
				var index = $obj.attr("_index");
				if(newValue == valueArray[index]){
					$obj.css({"border-color":settings.primaryColor});
					$obj.attr("newValue",false);
				}else{
					$obj.css({"border-color":settings.hitColor});
					$obj.attr("newValue",true);
				}
			});
		}
	}