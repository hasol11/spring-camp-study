<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	table{
		width: 800px;
		margin: 50px auto;
		border-collapse: collapse;
	}bookid
	table, th, td{
		border: 1px solid green;
		text-align: center;
		padding: 20px;
	}
	h1{
		text-align: center;
	}
	button{
		padding: 10px;
	}
a{
	text-decoration: none;
}
a:link, a:active, a:visited {
	color: black;
}

</style>
</head>
<body>
	<h2>BookList</h2>
	<table>
		<thead>
			<tr>
				<th>NO</th><th>BOOKID</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty list}">
					<tr><td colspan="5"><span>자료가 존재하지 않습니다.</span></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="k" items="${list}" varStatus="v">
						<tr>
						<th>${v.count}</th>
						<th><a href="/bookdetail?bookid=${k.bookid}">${k.bookname}</a></th>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

</body>
</html>