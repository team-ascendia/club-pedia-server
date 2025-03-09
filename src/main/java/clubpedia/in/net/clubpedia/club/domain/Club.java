package clubpedia.in.net.clubpedia.club.domain;

import clubpedia.in.net.clubpedia.genre.domain.Genre;
import clubpedia.in.net.clubpedia.region.domain.Region;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "price")
    private String price;

    @ManyToMany
    @JoinTable(
            name = "club_genre",
            joinColumns = @JoinColumn(name = "club_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;
}
