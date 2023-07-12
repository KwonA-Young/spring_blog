package com.spring.blog.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDateTime;

// Entity 는 불변성을 지키기 위해 @Setter 를 제공하지 않습니다.
// 한 번 생성된 데이터가 변경될 가능성을 없앱니다.

@Getter @ToString @Builder @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long replyId;

    @Column(nullable = false)
    private long blogId;

    @Column(nullable = false)
    private String replyWriter;

    @Column(nullable = false)
    private String replyContent;

    private LocalDateTime publishedAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void setDefaultValue() {
        this.publishedAt = LocalDateTime.parse("YYYY:mmDD HH:MM:SS");
        this.updatedAt = LocalDateTime.parse("YYYY:mmDD HH:MM:SS");
    }

    // @PreUpdate 어노테이션은 update 되기 전 실행할 로직을 작성한다.
    @PreUpdate
    public void setUpdateValue() {
        this.updatedAt = LocalDateTime.parse("YYYY:mmDD HH:MM:SS");}

}


