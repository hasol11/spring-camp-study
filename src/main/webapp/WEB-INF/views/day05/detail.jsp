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
</style>

</head>
<body>
	<div id="bbs">
	<form method="post" encType="multipart/form-data">
		<table summary="게시판 글쓰기">
			<caption>게시판 글쓰기</caption>
			<tbody>
				<tr>
					<th>제목:</th>
					<td>${bvo.subject}</td>
				</tr>
				<tr>
					<th>이름:</th>
					<td>${bvo.writer }</td>
				</tr>
				<tr>
					<th>내용:</th>
					<td><pre>${bvo.content}</pre></td>
				</tr>
				<tr>
					<th>첨부파일:</th>
					<c:choose>
						<c:when test="${empty bvo.f_name}">
							<td><b>첨부파일없음</b></td>
						</c:when>
						<c:otherwise>
							<td>
							<a href="/bbs_down?f_name=${bvo.f_name}">
								<img width="150px" src='<c:url value="/resources/upload/${bvo.f_name}"/>'>
							</a></td>
						</c:otherwise>
					</c:choose>
					<td></td>
				</tr>
				<tr >
					<td colspan="2" style="text-align: center;">
						<input type="button" value="수정" onclick="bbs_update(this.form)">
						<input type="button" value="삭제" onclick="bbs_delete(this.form)">
						<input type="button" value="목록" onclick="bbs_list(this.form)">
						<input type="hidden" name="b_idx" value="${bvo.b_idx }">
					</td>
				</tr>
			</tbody>
		</table>
	</form>
	</div>
	<script>
		function bbs_update(f){
			f.action="/bbs_update";
			f.submit();
		}
		function bbs_delete(f){
			f.action="/bbs_delete";
			f.submit();
		}
		function bbs_list(f){
			f.action="/day05";
			f.submit();
		}
		</script>
</body>
</html>

