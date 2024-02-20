<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>전화번호</h1>

<h2>수정폼</h2>

<p>
	전화번호를 수정하려면<br>
	아래 항목을 기입하고 "수정" 버튼을 클릭하세요
</p>

<form action="/phonebook3/pbc" method="get">
	<input type="hidden" name="action" value="update">

	<div>
		<label>이름(name)</label>
		<input type="text" name="name" value="">
	</div>
	
	<div>
		<label>핸드폰(hp)</label>
		<input type="text" name="hp" value="">
	</div>

	<div>
		<label>회사(company)</label>
		<input type="text" name="company" value="">
	</div>
	
	<input type="hidden" name="no" value="<%=request.getAttribute("no")%>">
	<button type="submit">수정</button>
</form>

<br><br>
<a href="">리스트페이지로 이동</a>

</body>
</html>