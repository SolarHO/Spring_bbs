<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">인하공업전문대학 컴퓨터정보공학과</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    학과안내
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=학과소개">학과소개</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=학과연혁">학과연혁</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    교과과정
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=1학년">1학년</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=2학년">2학년</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=3학년">3학년</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=전공심화">전공심화</a>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    커뮤니티
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=공지사항">공지사항</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=FAQ">FAQ</a>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/list?category=자유게시판">자유게시판</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">오시는 길</a>
            </li>
        </ul>
        <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                <a class="nav-link" href="#">LOGIN</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container">
    <h1 class="mt-5">게시글이 삭제되었습니다.</h1>
    <a class="btn btn-primary mb-3" href="${pageContext.request.contextPath}/list?category=${category}">목록으로 돌아가기</a>
    <a class="btn btn-secondary mb-3" href="${pageContext.request.contextPath}/">처음 화면으로 돌아가기</a>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
