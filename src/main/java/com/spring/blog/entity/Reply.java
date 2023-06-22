package com.spring.blog.entity;

import lombok.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;

// Entity 는 불변성을 지키기 위해 @Setter 를 제공하지 않습니다.
// 한 번 생성된 데이터가 변경될 가능성을 없앱니다.

@Getter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor

public class Reply {
    private long replyId;
    private long blogId;
    private String replyWriter;
    private String replyContent;
    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;
}


