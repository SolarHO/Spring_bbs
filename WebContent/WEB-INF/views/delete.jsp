<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 삭제</title>
</head>
<body>
<%-- 페이지 로드 후 게시글 목록으로 리다이렉트 --%>
<script>
    window.location.href = "list?category=<%=request.getParameter("category")%>";
</script>
</body>
</html>
