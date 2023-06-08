<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>detail</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <style>
        div {
            border: 1px solid black;}
    </style>
</head>
<body>
    <div class="container">
        <div class="row first-row">
            <div class="col-sm">
                글번호
            </div>
            <div class="col-sm">
                ${blog.blogId}
            </div>
            <div class="col-sm">
                글제목
            </div>
            <div class="col-sm">
                ${blog.blogTitle}
            </div>
            <div class="col-sm">
                작성자
            </div>
            <div class="col-sm">
                ${blog.writer}
            </div>
            <div class="col-sm">
                조회수
            </div>
            <div class="col-sm">
                ${blog.blogCount}
            </div>
        </div>  <!-- .first-row-->

        
        <div class="row second-row">
            <div class="col-sm-1">
                작성일
            </div>

            <div class="col-sm-5">
                ${blog.publishedAt}
            </div>

            <div class="col-sm-1">
                수정일
            </div>

            <div class="col-sm-5">
                ${blog.updatedAt}
            </div>
        </div> <!-- .row second-->


        <div class="row third row">
            <div class="col-sm-1">
                본문
            </div>

            <div class="col-sm-11">
                ${blog.blogContent}
            </div>
        </div> <!-- .row third -->


        <div class="row fourth-row">
            <div class="col">
                <a href="/blog/list"><button class="btn btn-outline-secondary">목록으로</button> </a>
            </div>
            <div class="col">
                <form action="/blog/delete" method="POST">
                    <input type="hidden" name="blogId" value="${blog.blogId}">
                    <input type="submit" value="삭제하기" class="btn btn-outline-warning">
                </form>
            </div>
            <div class="col">
                <form action="/blog/updateform" method="POST">
                    <input type="hidden" name="blogId" value="${blog.blogId}">
                    <input type="submit" value="수정하기" class="btn btn-outline-info">
                </form>
            </div>
        </div> <!-- .row fourth -->
    </div>   <!-- .container -->
</body>
</html>