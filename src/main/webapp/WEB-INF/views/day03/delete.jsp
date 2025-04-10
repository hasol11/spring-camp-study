<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> 방 명 록 </title>
<style type="text/css">
	a { text-decoration: none;}
	table{width: 800px; border-collapse:collapse; text-align: center;}
	table,th,td{border: 1px solid black; padding: 10px}
	input{padding: 5px;}
	div{width: 800px; margin:auto; text-align: center;}
</style>

</head>
<body>
	<div>
		<h2>방 명 록  : 삭 제 화 면</h2>
		<hr>
		<p>[<a href="/guestBookList">목록으로 이동]</a></p>
		<form action="/guestBookDeleteOK" method="post"   >
			<table>
				<tr align="center">
					<td bgcolor="#99ccff">비밀번호</td>
					<td><input type="password" name="gb_pw" id="gb_pw" size ="20" required></td>
				</tr>
				
				<tfoot>
					<tr align="center">
						<td colspan="2">
							<input type="button" onclick="delete_ok(this.form)" value="저장">
							<input type="hidden" name="gb_idx" value="${gvo.gb_idx}" >
							<input type="reset" value="취소">
						</td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function delete_ok(f) {
			let pwdchk = `${gvo.gb_pw}`;
			let pwd = document.querySelector("#gb_pw").value ;
			// console.log("pwdchk : " + pwdchk);
			// console.log("pwd : " + pwd);
			if(pwd === pwdchk){
			   const msg = confirm("정말삭제할까요?") ;
			   if(msg){
				   f.submit();
			   }else{
				   // 이전화면으로 이동 
				   window.history.go(-1);
			   }
			}else{
				alert("비밀번호 틀림");
				document.querySelector("#gb_pw").value = "";
				document.querySelector("#gb_pw").focus();
				return;
			}
		}
	</script>
</body>
</html>









