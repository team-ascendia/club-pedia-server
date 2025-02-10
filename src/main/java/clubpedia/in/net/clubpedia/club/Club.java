package clubpedia.in.net.clubpedia.club;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
public class Club {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="entrnce_fee")
    private String entranceFee;

    @Column(name="instragram_id")
    private String instagramId;

    @Column(name="dress_code")
    private String dressCode;

    @Column(name="admission_start_age")
    private Integer admissionStartAge;

    @Column(name="admission_end_age")
    private Integer admissionEndAge;

    @Column(name="grade")
    private Float grade;

    @Column(name="latitude")
    private Float latitude;

    @Column(name="longitude")
    private Float longitude;

    @Column(name="address")
    private String address;

    @Column(name="google_place_id")
    private String googlePlaceId;
}
