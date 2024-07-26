package bitc.fullstack405.publicwc.service;

// 서비스에서 사용할 메서드를 정의
public interface LocationService {
    // 위도(lat)와 경도(lon)를 받아 주소를 반환
    String getAddress(double lat, double lon);
}
