package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wcinfo")
@Data
@NoArgsConstructor
public class WcInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 번호

    @Column(nullable = true)
    @ColumnDefault("1")
    private int level; // 화장실 등급

    @Column(nullable = true)
    private String name; // 화장실 이름

    @Column(nullable = false)
    private String addr1; // 도로명 주소
    
    @Column(nullable = false)
    private String addr2; // 지번 주소

    @Column(nullable = false)
    private String detailAddr; // 상세 주소

    @Column(nullable = false)
    private String time; // 개방 시간

    @Column(nullable = true)
    private String comment; // 상세글

    @Column(nullable = false)
    private String latitude; // 위도

    @Column(nullable = false)
    private String longitude;  // 경도

    @Column(nullable = true)
    private String wcpass;


    @ManyToMany(mappedBy = "favoriteWcList")
    @ToString.Exclude
    private List<Users> favoriteUsers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "createUserId")
    @ToString.Exclude
    private Users createUser;

    @OneToMany(mappedBy = "bestWc", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Best> wcBestList = new ArrayList<>();
}
