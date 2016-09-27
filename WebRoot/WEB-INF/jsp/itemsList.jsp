<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询商品列表</title>
</head>
<body> 

<script>
	function deleteitems(){
	
	//待用户选择完商品执行提交 
	document.itemsform.action="${pageContext.request.contextPath }/items/deleteItems.action";
	document.itemsform.submit();
	
}
function queryitems(){
	
	document.itemsform.action="${pageContext.request.contextPath }/items/queryItems.action";
	document.itemsform.submit();
	
}
//批量修改
function editAll(){
	
	document.itemsform.action="${pageContext.request.contextPath }/items/editAll.action";
	document.itemsform.submit();
}


</script>


<form name="itemsform" method="post">
查询条件：
<table width="100%" border=1>
<tr>
<td>
商品名称：
<input type="text" name="itemsCustom.name"/>
商品类型：
<select name="itemtype">
	<c:forEach items="${itemtypes }" var="itemtype">
		<option value="${itemtype.key }">${itemtype.value }</option>		
	</c:forEach>
</select>

</td>
<td><input type="button" value="查询" onclick="queryitems()"/>
<input type="button" value="删除" onclick="deleteitems()"/>
<input type="button" value="批量修改" onclick="editAll()"/>

</td>
</tr>
</table>
商品列表：
<table width="100%" border=1>
<tr>
	<td>选择</td>
	<td>商品名称</td>
	<td>商品价格</td>
	<td>生产日期</td>
	<td>商品描述</td>
	<td>操作</td>
</tr>
<c:forEach items="${itemsList }" var="item" varStatus="status">
<tr>
	<td>
	<input type="checkbox" name="id" value="${item.id}"/>
	</td>
	<td>
	<input type="text" name="itemsList[${status.index }].name" value="${item.name }"/>
	</td>
	<td>
	<input type="text" name="itemsList[${status.index }].price" value="${item.price }"/>
	</td>
	<td><fmt:formatDate value="${item.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	<td>${item.detail }</td>
	
	<td><a href="${pageContext.request.contextPath }/items/editItems.action?id=${item.id}">修改</a></td>

</tr>
</c:forEach>

</table>
</form>
</body>

</html>