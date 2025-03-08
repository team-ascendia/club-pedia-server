-- Genre Table 생성
CREATE TABLE genre
(
    id       BIGSERIAL PRIMARY KEY,
    title    VARCHAR(255),
    sequence INTEGER,
);

-- Region Table 생성
CREATE TABLE region
(
    id       BIGSERIAL PRIMARY KEY,
    title    VARCHAR(255),
    sequence INTEGER,
);

-- Club Table 생성 (외래 키 직접 포함)
CREATE TABLE club
(
    id                  BIGSERIAL PRIMARY KEY,
    region_id           BIGINT NOT NULL REFERENCES region (id),
    address             VARCHAR(255),
    price               VARCHAR(255),
    thumbnail_image_url VARCHAR(255),
    title               VARCHAR(255)
);

-- Club:Genre N:M Table 생성 (외래 키 직접 포함)
CREATE TABLE club_genre
(
    club_id  BIGINT NOT NULL REFERENCES club (id),
    genre_id BIGINT NOT NULL REFERENCES genre (id),
    PRIMARY KEY (club_id, genre_id)
);

-- Member Table 생성
CREATE TABLE member
(
    id                       BIGSERIAL PRIMARY KEY,
    is_location_term_agreed  BOOLEAN DEFAULT FALSE NOT NULL,
    is_marketing_agreed      BOOLEAN DEFAULT FALSE NOT NULL,
    is_over_14_agreed        BOOLEAN DEFAULT FALSE NOT NULL,
    is_privacy_policy_agreed BOOLEAN DEFAULT FALSE NOT NULL,
    is_service_term_agreed   BOOLEAN DEFAULT FALSE NOT NULL,
    is_signup                BOOLEAN DEFAULT FALSE NOT NULL,
    social_type              SMALLINT CHECK (social_type BETWEEN 0 AND 2),
    birthday                 TIMESTAMP(6),
    email                    VARCHAR(255),
    gender                   VARCHAR(255) CHECK (gender IN ('MALE', 'FEMALE', 'UNKNOWN')),
    name                     VARCHAR(255),
    phone_number             VARCHAR(255),
    profile_image_url        VARCHAR(255),
    refresh_token            VARCHAR(255),
    social_id                VARCHAR(255)
);
