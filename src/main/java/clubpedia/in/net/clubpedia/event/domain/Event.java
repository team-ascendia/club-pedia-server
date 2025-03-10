package clubpedia.in.net.clubpedia.event.domain;

import clubpedia.in.net.clubpedia.club.domain.Club;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_image_url")
    private String thumbnailImageUrl;

    @Column(name = "price")
    private Integer price;

    @Column(name = "summary")
    private String summary;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;
}
