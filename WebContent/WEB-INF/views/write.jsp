<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script>
window.onload = function() {
    var message = '${message}';
    if (message) {
        alert(message);
    }
};
</script>
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
  <h1>글 작성</h1>
  <form action="write" method="post">
    <input type="hidden" name="category" value="${category}">
    <div class="mb-3">
      <label for="title" class="form-label">제목</label>
      <input type="text" class="form-control" id="title" name="title">
    </div>
    <div class="mb-3">
      <label for="content" class="form-label">내용</label>
      <textarea class="form-control" id="content" name="content"></textarea>
    </div>
    <%
	// 세션에서 유저 정보 가져오기
	Object userObject = session.getAttribute("userid");
	if (userObject != null) {
	    String userid = (String) userObject;
	%>
	    <input type="hidden" id="author" name="author" value="<%=userid%>">
	<%
	}
	%>
    <button type="submit" class="btn btn-primary">작성하기</button>
  </form>
  <a href="<%=request.getContextPath()%>/" class="btn btn-secondary">처음 화면으로 돌아가기</a>
</div>
</body>
</html>
