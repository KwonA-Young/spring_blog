package com.spring.blog.service;

import com.spring.blog.entity.Blog;
import com.spring.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 빈 컨테이너에 적재.
public class BlogServiceImpl implements BlogService {

    private final BlogRepository blogRepository;

    @Autowired // 필드 주입보다 생성자 주입이 더 속도가 빠르다.
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    public List<Blog> findAll() {
        // List<Blog> blogList = blogRepository.findAll();
        // return blogList;
        return blogRepository.findAll();
    }

    @Override
    public Blog findByID(long blogId) {
        return blogRepository.findById(blogId);
    }

    @Override
    public void deleteByID(long blogId) {
        blogRepository.deleteById(blogId);
    }

    @Override
    public void save(Blog blog) {
        blogRepository.save(blog);
    }

    @Override
    public void update(Blog blog) {
        blogRepository.update(blog);
    }


}
