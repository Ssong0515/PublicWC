package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users;
import bitc.fullstack405.publicwc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("users")
public class LoginController {

    @Autowired
    private UserService userService;

    // 메인 페이지 요청 처리
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 로그인 페이지 요청 처리
    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    // 로그인 폼 제출 처리
    @PostMapping("/loginProcess.do")
    public String login(@RequestParam String userId, @RequestParam String password, Model model) {
        // 입력된 아이디로 사용자 정보를 조회
        Users users = userService.findById(userId).orElse(null);

        // 사용자 정보가 존재하고 비밀번호가 일치하는지 확인
        if (users != null && userService.checkPassword(users, password)) {
            model.addAttribute("users", users); // 세션에 사용자 정보 추가
            return "redirect:/"; // 로그인 성공 시 메인 페이지로 리다이렉트
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 올바르지 않습니다."); // 오류 메시지 설정
            return "login/login"; // 로그인 페이지로 다시 이동
        }
    }
}
