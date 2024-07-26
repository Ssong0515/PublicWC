package bitc.fullstack405.publicwc.service;

import bitc.fullstack405.publicwc.entity.WcInfo; // WcInfo 엔티티 임포트

import java.util.List;

public interface ToiletService {
    // 도로명 주소로 화장실 목록을 검색하는 메소드
    List<WcInfo> searchToiletsByAddress(String address);

    // 모든 화장실 목록을 가져오는 메소드 (추가적인 기능으로 넣을 수 있음)
    List<WcInfo> getAllToilets();
}
