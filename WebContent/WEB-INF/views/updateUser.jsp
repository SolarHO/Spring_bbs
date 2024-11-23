<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 수정</title>
</head>
<body>
<h2>회원정보</h2>
<form:form action="updateUserProcess" method="post" modelAttribute="userForm">
    <form:hidden path="userid"/>
    <label for="password">새 비밀번호:</label><br>
    <form:password path="password" id="password"/><br>
    <form:errors path="password" cssClass="error"/></br>
    <label for="username">새 이름:</label><br>
    <form:input path="username" id="username"/><br>
    <form:errors path="username" cssClass="error"/></br>
    <input type="submit" value="수정">
</form:form>
<form action="deleteUser" method="post">
    <input type="submit" value="탈퇴">
</form>
<form action="logout" method="post">
    <input type="submit" value="로그아웃">
</form>
</body>
</html>
