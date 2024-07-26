package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 로그인 페이지를 보여줍니다.
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 로그인 폼 제출을 처리하고, 로그인 성공 시 메인 페이지로 리다이렉트합니다.
     * 로그인 실패 시 로그인 페이지로 돌아갑니다.
     */
    @PostMapping("action=\"/login.do")
    public String login(@RequestParam String userId, @RequestParam String password, Model model) {
        Users users = userService.findById(userId).orElse(null);

        if (users != null && userService.checkPassword(users, password)) {
            model.addAttribute("users", users);
            return "redirect:/";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "/login";
        }
    }
}


