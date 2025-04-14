<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과 페이지</h2>
	<h3>
		<ul>
			<li>올린사람 : ${name}</li>
			<li>파일이름 : ${fname}</li>
			<li>저장위치 : ${savePath}</li>
			<li>파일타입 : ${contentType}</li>
			<li>파일크기 : ${size}KB</li>
		</ul>
		<!-- 현재는 저장위치가 고정되어 있으므로 파일이름만 파라미터값으로 사용 -->
		<a href="/down?fname=${fname}">
			<img width="150px" alt="" src='<c:url value="/resources/upload/${fname}"/>'>
		</a>
	</h3>
	<hr>

</body>
</html>