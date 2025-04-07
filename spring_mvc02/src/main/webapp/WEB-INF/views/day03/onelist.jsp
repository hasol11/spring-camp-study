<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<link rel="stylesheet" href="resources/css/summernote-lite.css">
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 3px}
	div{width: 800px; margin:auto; text-align: center;}
	
	/* summernote toolbar 수정  
	.note-btn-group{width: auto;} */
	.note-toolbar{width: auto;}
</style>

</head>
<body>
	<div>
		<h2>방 명 록 2 : 상 세 화 면</h2>
		<hr>
		<p>[<a href="/guestBookList">목록으로 이동]</a></p>
		<form method="post" action="">
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">작성자</td>
					<td>${gvo.gb_name}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">제  목</td>
					<td>${gvo.gb_subject}</td>
				</tr>
				<tr align="center">
					<td bgcolor="#99ccff">email</td>
					<td>${gvo.gb_email}</td>
				</tr>

				<tr align="center">
					<td bgcolor="#99ccff">첨부파일</td>
					<c:choose>
						<c:when test="${empty gvo.gb_f_name}">
							<td><b>첨부파일없음</b></td>
						</c:when>
						<c:otherwise>
							<td>
								<a href="/guestBookDown?f_name=${gvo.gb_f_name}">
								<img width="150px" src='<c:url value="/resources/upload/${gvo.gb_f_name}"/>'></a>
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr align="left">
					<td colspan="2">
						<pre style="padding-left: 20px;">${gvo.gb_content}</pre>
					</td>
				</tr>
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" value="삭제">
							<input type="button" value="수정">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>

</body>
</html>
