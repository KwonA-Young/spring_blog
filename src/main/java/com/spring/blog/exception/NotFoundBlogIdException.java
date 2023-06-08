package com.spring.blog.exception;

public class NotFoundBlogIdException extends RuntimeException{

    // 생성자에 에러 사유를 전달할 수 있도록 메세지를 작성합니다.
    public NotFoundBlogIdException (String message) {
        super(message);
    }
}
