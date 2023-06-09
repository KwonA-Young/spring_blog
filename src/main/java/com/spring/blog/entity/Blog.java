package com.spring.blog.entity;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @Builder

// 역직렬화(디비 -> 자바객체)가 가능하돋록 blog 테이블 구조에 맞춰서 멤버변수를 선언해주세요.
public class Blog {
    private long blogId;   // 숫자는 웬만하면 long형 사용.
    private String writer;
    private String blogTitle;
    private String blogContent;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
    private long blogCount;
    }

