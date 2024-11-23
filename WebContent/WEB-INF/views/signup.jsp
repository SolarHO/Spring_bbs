<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script>
window.onload = function() {
    var message = '${duplicateid}';
    if (message) {
        alert(message);
    }
};
</script>
</head>
<body>
<h2>회원가입</h2>
<form:form modelAttribute="userForm" method="post">
    <label for="userid">아이디</label>
    <form:input path="userid"/>
    <form:errors path="userid" cssClass="error"/></br>
    
    <label for="password">비밀번호</label>
    <form:password path="password"/>
    <form:errors path="password" cssClass="error"/></br>
    
    <label for="username">이름</label>
    <form:input path="username"/>
    <form:errors path="username" cssClass="error"/></br>
    
    <input type="submit" value="회원가입"/>
</form:form>


</body>
</html>
