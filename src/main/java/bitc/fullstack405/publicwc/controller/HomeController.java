package bitc.fullstack405.publicwc.controller;

import bitc.fullstack405.publicwc.entity.Users; // Users 엔티티 임포트
import bitc.fullstack405.publicwc.entity.WcInfo; // WcInfo 엔티티 임포트
import bitc.fullstack405.publicwc.service.ToiletService; // ToiletService 임포트
import org.springframework.beans.factory.annotation.Autowired; // @Autowired 임포트
import org.springframework.web.bind.annotation.GetMapping; // @GetMapping 임포트
import org.springframework.web.bind.annotation.RequestParam; // @RequestParam 임포트
import org.springframework.web.bind.annotation.RestController; // @RestController 임포트

import jakarta.servlet.http.HttpSession; // HttpSession 임포트
import java.util.List; // List 임포트

@RestController // 이 클래스가 RESTful 웹 서비스의 컨트롤러임을 지정
public class HomeController {

    @Autowired // ToiletService를 자동으로 주입
    private ToiletService toiletService;

    // 도로명 주소를 받아 화장실 목록을 가져오는 API
    @GetMapping("/wcList") // "/wcList" 경로로 GET 요청을 처리
    public List<WcInfo> getWcList(@RequestParam String address, HttpSession session) {
        // 세션에서 사용자 정보를 가져옴 (필요시 사용)
        Users user = (Users) session.getAttribute("users");

        // 도로명 주소가 존재할 경우, 해당 주소로 화장실 검색
        return toiletService.searchToiletsByAddress(address); // 검색 결과 반환
    }
}
