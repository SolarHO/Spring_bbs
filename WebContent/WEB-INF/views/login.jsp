<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 페이지</title>
<script type="text/javascript">
    window.onload = function() {
        // 로그인 에러 메시지가 있을 경우 알림창 표시
        var loginError = '${loginError}';
        if(loginError) {
            alert(loginError);
        }
    }
</script>
</head>
<body>
    <h2>로그인</h2>
    <form action="loginProcess" method="post">
        <label for="userid">아이디:</label><br>
        <input type="text" id="userid" name="userid"><br>
        <label for="password">비밀번호:</label><br>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="로그인">
    </form>
    <form action="signup" method="get">
        <input type="submit" value="회원가입">
    </form>
</body>
</html>
