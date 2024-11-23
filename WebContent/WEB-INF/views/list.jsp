<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
  <div class="container-fluid">
    <a class="navbar-brand" href="<%=request.getContextPath()%>/">인하공업전문대학 컴퓨터정보공학과</a> 
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <ul class="navbar-nav">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            학과안내
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=intro">학과소개</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=history">학과연혁</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            교과과정
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=year1">1학년</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=year2">2학년</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=year3">3학년</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=advanced">전공심화</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            커뮤니티
          </a>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=notice">공지사항</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=faq">FAQ</a></li>
            <li><a class="dropdown-item" href="<%=request.getContextPath()%>/list?category=freeboard">자유게시판</a></li>
          </ul>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/list?category=directions">오시는 길</a>
        </li>
        <% 
        // 세션에서 유저 정보 가져오기
        String username = (String) session.getAttribute("username");
        if (username != null) {
        %>
        <li class="nav-item">
          <a class="nav-link" href="<%=request.getContextPath()%>/list?category=myPost">내가쓴 글</a>
        </li>
        <%
        }
        %>
      </ul>
      <div class="d-flex ms-auto">
        <% 
        if (username == null) {
        %>
          <a class="nav-link" href="<%=request.getContextPath()%>/login">로그인</a>
        <% 
        } else {
        %>
          <a class="nav-link" href="<%=request.getContextPath()%>/updateUser"><%=username%>님</a>
        <% 
        }
        %>
      </div>
    </div>
  </div>
</nav>
<div class="container">
  <c:choose>
  <c:when test="${category == 'intro'}">
		<h1>학과소개</h1>
	</c:when>
	<c:when test="${category == 'history'}">
		<h1>학과연혁</h1>
	</c:when>
	<c:when test="${category == 'year1'}">
		<h1>1학년 교과과정</h1>
	</c:when>
	<c:when test="${category == 'year2'}">
		<h1>2학년 교과과정</h1>
	</c:when>
	<c:when test="${category == 'year3'}">
		<h1>3학년 교과과정</h1>
	</c:when>
	<c:when test="${category == 'advanced'}">
		<h1>전공심화과정</h1>
	</c:when>
	<c:when test="${category == 'notice'}">
		<h1>공지사항</h1>
	</c:when>
	<c:when test="${category == 'faq'}">
		<h1>FAQ</h1>
	</c:when>
	<c:when test="${category == 'freeboard'}">
		<h1>자유게시판</h1>
	</c:when>
	<c:when test="${category == 'directions'}">
		<h1>오시는 길</h1>
	</c:when>
	<c:when test="${category == 'myPost'}">
		<h1>내가쓴 글</h1>
	</c:when>
</c:choose>
  <c:choose>
  <c:when test="${sessionScope.userid != null && category != 'myPost' && (category == 'notice' || category == 'faq' || category == 'freeboard' || sessionScope.admin == 1)}">
    <a href="write?category=${category}" class="btn btn-primary">글 작성</a>
  </c:when>
</c:choose>
  <a href="<%=request.getContextPath()%>/" class="btn btn-secondary">처음 화면으로 돌아가기</a>
  <table class="table table-striped">
    <thead>
      <tr>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일자</th>
        <th>조회수</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="post" items="${posts}">
        <tr>
          <td><a href="view?id=${post.id}&category=${category}">${post.title}</a></td>
          <td>${post.authorname}</td>
          <td>${post.createdAt}</td>
          <td>${post.viewCount}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
