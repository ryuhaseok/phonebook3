<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
    
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>전화번호부</h1>

<h2>리스트</h2>

<p>
	등록된 전화번호 리스트입니다.
</p>

<c:forEach items="${requestScope.personList }" var="personVo">

<table border="1">
	<tr>
		<th>이름(name)</th>
		<td>${personVo.name }</td>
	</tr>
	<tr>
		<th>핸드폰(hp)</th>
		<td>${personVo.hp }</td>
	</tr>
	<tr>
		<th>회사(company)</th>
		<td>${personVo.company }</td>
	</tr>
	<tr>
		<td>${personVo.personId }
			<a href="/phonebook3/pbc?action=delete&no=${personVo.personId }">[삭제]</a>
		</td>
		<td>
			<a href="/phonebook3/pbc?action=updateForm&no=${personVo.personId }">[수정]</a>
		</td>
	</tr>
</table>
<br>

</c:forEach>

</body>
</html>