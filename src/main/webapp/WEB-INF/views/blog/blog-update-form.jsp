<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>form</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
</head>
</head>
<body>
<div class="container">
    <form action="/blog/update" method="POST">
        <div class="row">
            <div class="col-3">
                <label for="writer" class="form-label">글쓴이</label>
                <input type="text" class="form-control" name="writer" id="writer" value="${blog.writer}" placeholder="글쓴이를 입력해주세요." readonly>
            </div>
            <div class="col-3">
                <label for="title" class="form-label">제목</label>
                <input type="text" class="form-control" name="blogTitle" id="title" value="${blog.blogTitle}" placeholder="제목을 입력해주세요.">
            </div>
        </div>
        <div class="row">
            <div class="col-6">
                <label for="content" class="form-label">본문</label>
                <textarea class="form-control" name="blogContent" id="content" rows="10">${blog.blogContent}</textarea>
            </div>
        </div>
        <div class="col-6">
            <input type="submit" class="btn btn-primary" value="글쓰기">
        </div>
    </form>
</div>
</body>
</html>