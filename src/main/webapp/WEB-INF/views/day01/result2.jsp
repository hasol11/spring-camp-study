<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 라이브러리를 다운받지 않았고 lib 넣지도 않았다.
	그래도 되는 이유는 pom.xml에서 의존성 주입을 한다.
 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>결과보기</h1>
<c:forEach var="k" items="${carName}">
	<li>${k}</li>
</c:forEach>
</body>
</html>