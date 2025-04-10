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
				<th>BOOKID</th><th>BOOKNAME</th><th>PUBLISTER</th><th>PRICE</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${bvo.bookid}</td>
				<td>${bvo.bookname}</td>
				<td>${bvo.publisher}</td>
				<td>${bvo.price}</td>
			</tr>		
		</tbody>
		<tfoot>
			<tr>
				<td colspan="4">
					<button onclick="update_go()">수정</button>
					<button onclick="delete_go()">삭제</button>
				</td>
			</tr>
		</tfoot>
	</table>
	<script>
	function update_go(){
		location.href="/bookupdate?bookid="+${bvo.bookid};
	}
	function delete_go(){
		location.href=`/bookdelete?bookid=${bvo.bookid}`;	
	}
	
	</script>
</body>
</html>