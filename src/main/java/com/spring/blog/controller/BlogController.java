package com.spring.blog.controller;

import com.spring.blog.entity.Blog;
import com.spring.blog.exception.NotFoundBlogIdException;
import com.spring.blog.service.BlogService;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.SimpleTimeZone;

@Controller  // 컨트롤러 어노테이션은 1. 빈 등록 + 2. URL 매핑 처리 기능을 함께 가지고 있으므로,
             // 다른 어노테이션과 교한해서 쓸 수 없다.
@RequestMapping("/blog")
@Log4j2 //syso 이 아닌 로깅을 통한 디버깅을 위해 선언.
public class BlogController {
    private BlogService blogService;

    @Autowired   // 생성자 주입.
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // /blog/list 주소로 get방식 접속했을 때
    // 1. 서비스 객체를 이용해 게시글 전체를 얻어오세요.
    // 2. 얻어온 게시글을 .jsp로 보낼 수 있도록 적재해주세요.
    // 3. .jsp 에서 볼 수 있도록 출력해주세요.
    // 해당 파일의 이름은 board/list.jsp 입니다.
    @RequestMapping("/list")
    public String list(Model model) {
        List<Blog> blogList = blogService.findAll();
        model.addAttribute("blogList", blogList);
        return "blog/list";
    }

    // 디테일 페이지의 주소 패턴
    // /blog/detail/글번호
    // 위 방식으로 글번호를 입력받아, service 를 이용해 해당 글 번호 요소만 얻어서
    // 뷰에 적재하는 코드를 작성해주세요.
    @RequestMapping("/detail/{blogId}")
    public String detail(@PathVariable Long blogId, Model model) {
        Blog blog = blogService.findByID(blogId);

        if (blog == null) {
            try {
                throw new NotFoundBlogIdException("없는 blogId로 조회했습니다. 조회번호 : " + blogId);
            } catch (NotFoundBlogIdException e) {
                e.printStackTrace(); // 콘솔에서 예외메세지 체크.
                return "blog/NotFoundBlogIdExceptionResultPage";
            }
        }
        model.addAttribute("blog", blog);

        //model.addAttribute("blog", blogService.findByID(blogId));
        return "blog/detail";
    }

    // 폼 페이지와 실제 등록 url은 같은 url을 쓰도록 한다.
    // 대신 폼 페이지는 GET 방식으로 접속했을 때 연결해주고
    // 폼에서 작성완료한 내용을 POST 방식으로 제출해 저장하도록 만들어준다.
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert() {
        return "blog/blog-form";
    }

    @RequestMapping (value = "/insert", method = RequestMethod.POST)
    public String insert(Blog blog) {
        // 서비스 객체를 이용해서 DB에 저장해주시고
        blogService.save(blog);
        // 저장 후에는 리다이렉트로 list 페이지로 돌아오도록 해주세요.
        return "redirect:/blog/list";
    }

    // DELETE 로직은 삭제 후 /blog/list 로 리다잉렉트 되어서 자료가 삭제된 것을 확인해야 합니다.
    // 글 번호만으로 삭제를 진행해야 합니다.
    // 따라서, 디테일 페이지에 삭제버튼을 추가하고, 해당 버튼을 클릭했을 때,
    // 삭제 번호가 전달되어서 전달받은 번호를 토대로 삭제하도록 로직을 구성해주세요.
    // 폼에 추가한 삭제버튼 코드와 컨트롤러에 작성한 delete 메서드
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(long blogId) {
        blogService.deleteByID(blogId);
        return "redirect:/blog/list";
    }

    // UPDATE 구무은 다른 내역은 다 INSERT 와 비슷하지만
    // 한 가지 차이점은, 폼이 이미 기존에 작성된 정보로 채워져 있다는 점입니다.
    // 이를 구현하기 위해 수정 버튼이 눌렸을 때, 제일 먼저 해당 글 정보를 획득한 다음
    // 폼 페이지에 model.addAttribute() 로 보내줘야 합니다.
    // 그 다음 수정용 폼 페이지에 해당 자료를 채운 채 연결해주면 됩니다.
    // 이를 위해 value= 를 이용하면 미리 원하는 내용으로 폼을 채워둘 수 있습니다.
    @RequestMapping(value = "/updateform", method = RequestMethod.POST)
    public String update(long blogId, Model model) {
        // blogId 를 이용해서 blog 객체를 받아옵니다.
        Blog blog = blogService.findByID(blogId);
        // .jsp 로 보내기 위해 적재합니다.
        model.addAttribute("blog", blog);
        return "blog/blog-update-form";
    }

    // /blog/update 주소로 POST 요청을 넣으면 글이 수정되도록 합니다.
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Blog blog) {
        // 받아온 blog 엔터티로 글 수정.
        blogService.update(blog);
        // 리다이렉트는 가능하다면 해당 글 번호의 디테일 페이지로 넘어가게 해주세요.
        return "redirect:/blog/detail/" + blog.getBlogId();

        // 어려우면 list로 넘어가도록 해주세요.
        // return "redirect:/blog/list";

    }
}


