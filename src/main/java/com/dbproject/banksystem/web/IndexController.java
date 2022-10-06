package com.dbproject.banksystem.web;

import com.dbproject.banksystem.config.auth.LoginUser;
import com.dbproject.banksystem.config.auth.dto.SessionUser;
import com.dbproject.banksystem.domain.posts.PostsRepository;
import com.dbproject.banksystem.service.PostsService;
import com.dbproject.banksystem.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    // private final HttpSession httpSession;
    // Model
    // * 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장
    // * 여기서는 postsService.findAllDesc()로 가져온 결과를 posts 로 index.mustache 에 전달
    @GetMapping("/")
    // @LoginUser SessionUser user
    // * 기존에 (User) httpSession.getAttribute("user")로 가져오던 세션 정보값이 개선
    // * 이제는 어느 컨트롤러든지 @LoginUser 만 사용하면 세션 정보를 가져올 수 있음
    public String index(Model model, @LoginUser SessionUser user){
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");

        if(user != null){
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

}