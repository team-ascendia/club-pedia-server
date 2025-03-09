package clubpedia.in.net.clubpedia.genre.domain;

import clubpedia.in.net.clubpedia.club.domain.Club;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "sequence")
    private Integer sequence;

    @ManyToMany(mappedBy = "genres")
    private Set<Club> clubs = new HashSet<>();
}