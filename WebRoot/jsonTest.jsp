<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.min.js"></script>

<script type="text/javascript">
	function requestJson(){
		//使用ajax请求
		$.ajax({
			
			type:'post',
			url:'${pageContext.request.contextPath }/requestJson.action',
			//请求的json，设置
			contentType:'application/json;charset=utf-8',
			data:'{"name":"手机","price":999}',
			success:function(data){
				alert(data.name);
			}
			
		});
		
	}
	
	//请求key/value响应json
	function responseJson(){
		//使用ajax请求
		$.ajax({
			
			type:'post',
			url:'${pageContext.request.contextPath }/responseJson.action',
			//请求的json，设置
			//contentType:'application/json;charset=utf-8',
			//不用指定contentType因为默认提交是key/value
			data:'name=手机&price=999',
			success:function(data){
				alert(data);
			}
			
		});
		
	}

</script>
<title>json测试</title>
</head>
<body>
<input type="button" value="请求json响应json" onclick="requestJson()">
<input type="button" value="请求key/value响应json" onclick="responseJson()">
</body>
</html>