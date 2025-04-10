<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Day02</h2>
	<form action="/logIn01" method="post">
		<label>ID : <input type="text" name="userId" required></label>
		<label>PW : <input type="password" name="userPw" required></label>
		<input type="submit" value="로그인">
	</form>
	<form action="/cal" method="get">
		<h2>계산기</h2>
		<label>첫 번쨰 값 : <input type="text" name="firstNum" required></label>
		<label>두 번쨰 값 : <input type="text" name="SecNum" required></label>
		<hr>
		<input type="radio" name="cal" value="+" checked>+
		<input type="radio" name="cal" value="-">-
		<input type="radio" name="cal" value="*">*
		<input type="radio" name="cal" value="/">/
		<input type="submit" value="완료">
	</form>
	<form action="/hobby" method="post">
		<p>취미 :
			<input type="checkbox" name="hobby" value="축구">축구
			<input type="checkbox" name="hobby" value="야구">야구
			<input type="checkbox" name="hobby" value="농구">농구
			<input type="checkbox" name="hobby" value="배구">배구
		</p>
			<input type="submit" value="보내기"> 
	</form>
	
	<button onclick="getBookList()">book 테이블 정보 보기</button>
	<script type="text/javascript">
	function getBookList(){
		location.href = "/bookList";
	}
	</script>
	
</body>
</html>