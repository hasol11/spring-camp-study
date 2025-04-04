<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<h2>CustomerDetail</h2>
	<table>
		<thead>
			<tr>
				<th>CUSTID</th><th>USERNAME</th><th>ADDR</th><th>PHONE</th>
			</tr>
		</thead>
		<tbody>
<%-- 			<tr>
				<td>${cus.custid}</td>
				<td>${cus.username}</td>
				<td>${cus.addr}</td>
				<td>${cus.phone}</td>
			</tr>	 --%>	
			<c:choose>
				<c:when test="${empty cus}">
					<tr>
					<td colspan="4">존재하지 않는 고객입니다.</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
				<td>${cus.custid}</td>
				<td>${cus.username}</td>
				<td>${cus.addr}</td>
				<td>${cus.phone}</td>
			</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>