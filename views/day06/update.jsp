<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#bbs table {
	    width:580px;
	    margin:0 auto;
	    margin-top:20px;
	    border:1px solid black;
	    border-collapse:collapse;
	    font-size:14px;
	    
	}
	
	#bbs table caption {
	    font-size:20px;
	    font-weight:bold;
	    margin-bottom:10px;
	}
	
	#bbs table th {
	    text-align:center;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	#bbs table td {
	    text-align:left;
	    border:1px solid black;
	    padding:4px 10px;
	}
	
	.no {width:15%}
	.subject {width:30%}
	.writer {width:20%}
	.reg {width:20%}
	.hit {width:15%}
	.title{background:lightsteelblue}
	.odd {background:silver}
	
	th, td{margin: 5px; padding: 10px;}
table caption {
	font-size: 20px;
	font-weight: bold;
	margin: 30px 0px;
}
</style>
<script>
	<c:if test="${pwdchk=='fail'}">
		alert("비밀번호 틀림");
	</c:if>
</script>
</head>
<body>
	<div id="bbs">
	<form method="post" encType="multipart/form-data">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td><input type="text" name="title" size="45" value="${boardVO.title}"></td>
				</tr>
				<tr>
					<th>이름:</th>
					<td><input type="text" name="writer" size="12" value="${boardVO.writer}"></td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><textarea name="content" cols="50" 
							rows="8">${boardVO.content}</textarea></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty boardVO.f_name}">
							<td><input type="file" name="file_name"><b>첨부파일없음</b>
								<input type="hidden" name="old_f_name" value="">
							
							</td>
						</c:when>
						<c:otherwise>
							<td><input type="file" name="file_name"><b><br>(${boardVO.f_name})</b>
								<input type="hidden" name="old_f_name" value="${boardVO.f_name}">
							</td>
						</c:otherwise>
					</c:choose>
				</tr>
				<tr>
					<th>비밀번호:</th>
					<td><input type="password" name="pwd" size="12"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="button" value="수정하기" onclick="board_update_ok(this.form)">
						<input type="button" value="목록" onclick="board_list(this.form)">
						<input type="hidden" name="bo_idx" value="${boardVO.bo_idx}">
						<input type="hidden" name="cPage" value="${cPage}">
					</td>
				</tr>
			</tbody>
		</table> 
	</form>
	</div>
	<script>
		function board_update_ok(f){
			f.action="/board_update_ok";
			f.submit();
		}
		function board_list(f){
			f.action="/day06";
			f.submit();
		}
		</script>
</body>
</html>

