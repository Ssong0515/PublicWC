package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class Users {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    @ColumnDefault("false")
    private String handicap;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int passkey;

    @ManyToMany
    @JoinTable(name = "userWcFavorites", joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "wcId"))
    @ToString.Exclude
    private List<WcInfo> favoriteWcList = new ArrayList<>();

    @OneToMany(mappedBy = "createUser", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<WcInfo> createWcList = new ArrayList<>();

    @OneToMany(mappedBy = "bestUsers", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Best> userBestList = new ArrayList<>();

}
