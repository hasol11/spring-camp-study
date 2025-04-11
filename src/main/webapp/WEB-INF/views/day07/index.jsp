<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div>
        <input type="email" id="email" placeholder="등록된 이메일 입력" />
        <button onclick="sendCode()">인증코드 전송</button>
    </div>
    <div>
        <input type="text" id="code" placeholder="인증코드 입력" />
        <button onclick="verifyCode()">인증 확인</button>
    </div>
    <div id="pw-change-form" style="display: none;">
        <input type="password" id="newPw" placeholder="새 비밀번호 입력" />
        <button onclick="changePw()">비밀번호 변경</button>
    </div>
</body>
</html>