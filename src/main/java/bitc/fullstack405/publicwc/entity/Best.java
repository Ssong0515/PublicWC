package bitc.fullstack405.publicwc.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "best")
@Setter
@Getter
@NoArgsConstructor
public class Best {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private boolean good;

    @ManyToOne
    @JoinColumn(name = "userId")
    @ToString.Exclude
    private Users bestUsers;

    @ManyToOne
    @JoinColumn(name = "wcId")
    @ToString.Exclude
    private WcInfo bestWc;

}