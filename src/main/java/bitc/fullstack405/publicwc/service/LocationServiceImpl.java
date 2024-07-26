//package bitc.fullstack405.publicwc.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpMethod;
//
//import java.util.List;
//import java.util.Map;
//
//@Service
//public class LocationServiceImpl implements LocationService {
//
//    private final RestTemplate restTemplate;
//
//    @Value("${kakao.api.key}")
//    private String apiKey;
//
//    public LocationServiceImpl() {
//        this.restTemplate = new RestTemplate();
//    }
//
//    @Override
//    public String getAddress(double lat, double lon) {
//        String url = String.format("https://dapi.kakao.com/v2/local/geo/coord2address.json?x=%s&y=%s", lon, lat);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("Authorization", "KakaoAK " + apiKey);
//
//        HttpEntity<String> entity = new HttpEntity<>(headers);
//
//        try {
//            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
//            Map<String, Object> body = response.getBody();
//
//            if (body != null && body.get("documents") != null) {
//                Map<String, Object> addressInfo = ((List<Map<String, Object>>) body.get("documents")).get(0);
//                Map<String, String> address = (Map<String, String>) addressInfo.get("address");
//                return address.get("address_name");
//            }
//            throw new RuntimeException("No address found for the given coordinates.");
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to get address: " + e.getMessage(), e);
//        }
//    }
//}
