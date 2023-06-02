package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class BlogServiceTest {

    @Autowired
    BlogService blogService;

    @Test
    @Transactional // 이 테스트의 결과가 디비 커밋을 하지 않음.
    public void findAllTest() {
        // given : 없음.

        // when : 전체 데이터 가져오기.
        List<Blog> blogList = blogService.findAll();

        // then : 길이가 3일 것이다.
        // assertEquals (3, blogList.size()); 아래 코드와 동일한 코드.
        assertThat(blogList.size()).isEqualTo(3); // import assertj.....
    }

    // findById를 직접 테스트하는 코드를 작성해주세요. @Transactional 어노테이션도 붙여주세요.
    @Test
    @Transactional
    public void findByIdTest() {
        // given : 조회 할 번호인 2번 변수에 저장, 예상 글쓴이, 본문정보 저장.
        long blogId = 2;
        String writer = "2번유저";
        String blogTitle = "2번제목";

        // when : DB 에서 2번 유저 얻어오기.
        Blog blog = blogService.findByID(blogId);

        // then : 얻어온 유저의 번호는 blogId, 글쓴이는 writer 변수, 제목은 blogTitle 변수에 든 값일 것임.
        assertEquals(blogId, blog.getBlogId());
        assertEquals(writer, blog.getWriter());
        assertEquals(blogTitle, blog.getBlogTitle());
    }

    // deleteByID에 대해서 테스트코드를 작성해주세요.
    @Test
    @Transactional
    // @Commit  // 트랜잭션 적용된 테스트의 결과를 커밋해서 DB 에 반영하도록 해준다.
    public void deleteByIDTest() {
        // given : 삭제할 번호 2번 지정.
        long blogId = 2;
        String writer = "2번유저";
        String blogTitle = "2번제목";

        // when
        blogService.deleteByID(blogId);

        // then : 삭제되었다면, 총 개수는 2개, 2번으로 재 조회시 null
        assertEquals(2, blogService.findAll().size());
        assertNull(blogService.findByID(blogId));
    }

    // 저장로직에 대해서 테스트코드를 "빌더패턴을 써서" 작성해주세요.
    @Test
    @Transactional
    public void saveTest() {
        // given : Blog 객체에 필요데이터인 writer, BlogTitle, BlogContent 를 주입해 builder 패턴으로 생성한다.
        String writer = "서비스 추가 글쓴이";
        String blogTitle = "서비스 추가 제목";
        String blogContent = "서비스 추가 본문";
        int lastBlogIndex = 3;
        Blog blog = Blog.builder()
                .writer(writer)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build();

        // when : .save()를 호출해서 DB 에 저장한다.
        blogService.save(blog);

        // then : 전체 요소의 개수가 4개인지 확인하고,
        // 현재 얻어온 마지막 요소의 writer, blogTitle, blogContent 가 생성시 사용한 자료와 일치하는지 확인.
        assertEquals(4, blogService.findAll().size());
        assertEquals(writer, blogService.findAll().get(lastBlogIndex).getWriter());
        assertEquals(blogTitle, blogService.findAll().get(lastBlogIndex).getBlogTitle());
        assertEquals(blogContent, blogService.findAll().get(lastBlogIndex).getBlogContent());
    }

    // update() 메서드를 테스트하는 코드를 작성해주세요.
    @Test
    @Transactional
    public void updateTest() {
        // given : blogID 2번글의 제목을 "수정 제목"으로, 본문을 "수정 본문"으로 바꾸기 위한
        // 픽스처 선언 및 Blog 객체 선언.
        long blogId = 2;
        String blogTitle = "수정 제목";
        String blogContent = "수정 내용";
        Blog blog = Blog.builder()
                .blogId(blogId)
                .blogTitle(blogTitle)
                .blogContent(blogContent)
                .build();

        // when : update() 메서드를 이용해 상단 blog 객체를 파라미터로 수정 실행.
        blogService.update(blog);

        // then : blogId번 글을 가져와서, blogTitle, blogContent 가ㅏ 수정을 위한 픽스처와 동일하다고 단언.
        assertEquals(blogId, blogService.findByID(blogId).getBlogId());
        assertEquals(blogTitle, blogService.findByID(blogId).getBlogTitle());
        assertEquals(blogContent, blogService.findByID(blogId).getBlogContent());
    }
}
