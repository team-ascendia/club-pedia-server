-- Region 기본 데이터
INSERT INTO region  (id, title, sequence)
VALUES (1, '신사동', 1),
       (2, '삼성동', 2),
       (3, '청담동', 3),
       (4, '압구정동', 4),
       (5, '논현동', 5),
       (6, '이태원동', 6),
       (7, '화양동', 7);

-- Genre 기본 데이터
INSERT INTO genre(id, title, sequence)
VALUES (1, 'EDM', 1),
       (2, '힙합', 2),
       (3, '테크노', 3),
       (4, '하우스', 4),
       (5, 'K-POP', 5),
       (6, '레게톤', 6),
       (7, 'R&B', 7),
       (8, '펑크', 8),
       (9, '인디', 9);

-- Club 기본 데이터
INSERT INTO club (title, thumbnail_image_url, address, price, region_id)
VALUES ('Octagon', 'https://example.com/octagon.jpg', '서울 강남구 신사동 123', '20000', 1),
       ('Arena', 'https://example.com/arena.jpg', '서울 강남구 삼성동 456', '25000', 2),
       ('Made Itaewon', 'https://example.com/made.jpg', '서울 용산구 이태원동 789', '30000', 6),
       ('Club Mass', 'https://example.com/mass.jpg', '서울 강남구 논현동 101', '15000', 5),
       ('Cakeshop', 'https://example.com/cakeshop.jpg', '서울 용산구 이태원동 222', '20000', 6),
       ('FAUST', 'https://example.com/faust.jpg', '서울 광진구 화양동 333', '10000', 7),
       ('Boombar', 'https://example.com/boombar.jpg', '서울 강남구 청담동 444', '18000', 3),
       ('Soap Seoul', 'https://example.com/soap.jpg', '서울 강남구 압구정동 555', '22000', 4),
       ('Club Chroma', 'https://example.com/chroma.jpg', '서울 강남구 삼성동 666', '27000', 2),
       ('SKRT Seoul', 'https://example.com/skrt.jpg', '서울 강남구 청담동 777', '19000', 3);

-- Club과 Genre 연결 (N:M 관계)
INSERT INTO club_genre (club_id, genre_id)
VALUES (1, 1),  -- Octagon -> EDM
       (1, 3),  -- Octagon -> 테크노
       (2, 2),  -- Arena -> 힙합
       (2, 5),  -- Arena -> K-POP
       (3, 1),  -- Made Itaewon -> EDM
       (3, 6),  -- Made Itaewon -> 레게톤
       (4, 4),  -- Club Mass -> 하우스
       (5, 7),  -- Cakeshop -> R&B
       (5, 8),  -- Cakeshop -> 펑크
       (6, 3),  -- FAUST -> 테크노
       (6, 4),  -- FAUST -> 하우스
       (7, 2),  -- Boombar -> 힙합
       (7, 7),  -- Boombar -> R&B
       (8, 9),  -- Soap Seoul -> 인디
       (9, 1),  -- Club Chroma -> EDM
       (9, 5),  -- Club Chroma -> K-POP
       (10, 8), -- SKRT Seoul -> 펑크
       (10, 3); -- SKRT Seoul -> 테크노

-- Octagon 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (1, 'MONDAY', '18:00', '03:00'),
    (1, 'TUESDAY', '18:00', '03:00'),
    (1, 'WEDNESDAY', '18:00', '04:00'),
    (1, 'THURSDAY', '18:00', '04:00'),
    (1, 'FRIDAY', '18:00', '05:00'),
    (1, 'SATURDAY', '18:00', '06:00'),
    (1, 'SUNDAY', '18:00', '02:00');

-- Arena 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (2, 'MONDAY', '19:00', '02:00'),
    (2, 'TUESDAY', '19:00', '02:00'),
    (2, 'WEDNESDAY', '19:00', '03:00'),
    (2, 'THURSDAY', '19:00', '03:00'),
    (2, 'FRIDAY', '19:00', '05:00'),
    (2, 'SATURDAY', '19:00', '06:00'),
    (2, 'SUNDAY', '19:00', '02:00');

-- Made Itaewon 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (3, 'MONDAY', '20:00', '02:00'),
    (3, 'TUESDAY', null, null),
    (3, 'WEDNESDAY', '20:00', '03:00'),
    (3, 'THURSDAY', '20:00', '03:00'),
    (3, 'FRIDAY', '20:00', '05:00'),
    (3, 'SATURDAY', '20:00', '06:00'),
    (3, 'SUNDAY', '20:00', '01:00');

-- Club Mass 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (4, 'MONDAY', null, null),
    (4, 'TUESDAY', null, null),
    (4, 'WEDNESDAY', '19:00', '02:00'),
    (4, 'THURSDAY', '19:00', '03:00'),
    (4, 'FRIDAY', '19:00', '05:00'),
    (4, 'SATURDAY', '19:00', '05:00'),
    (4, 'SUNDAY', '19:00', '01:00');

-- Cakeshop 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (5, 'MONDAY', null, null),
    (5, 'TUESDAY', null, null),
    (5, 'WEDNESDAY', '21:00', '02:00'),
    (5, 'THURSDAY', '21:00', '03:00'),
    (5, 'FRIDAY', '21:00', '05:00'),
    (5, 'SATURDAY', '21:00', '06:00'),
    (5, 'SUNDAY', '21:00', '02:00');

-- FAUST 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (6, 'MONDAY', null, null),
    (6, 'TUESDAY', null, null),
    (6, 'WEDNESDAY', '20:00', '02:00'),
    (6, 'THURSDAY', '20:00', '03:00'),
    (6, 'FRIDAY', '20:00', '05:00'),
    (6, 'SATURDAY', '20:00', '06:00'),
    (6, 'SUNDAY', '20:00', '01:00');

-- Boombar 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (7, 'MONDAY', null, null),
    (7, 'TUESDAY', '19:00', '02:00'),
    (7, 'WEDNESDAY', '19:00', '02:00'),
    (7, 'THURSDAY', '19:00', '03:00'),
    (7, 'FRIDAY', '19:00', '04:00'),
    (7, 'SATURDAY', '19:00', '05:00'),
    (7, 'SUNDAY', '19:00', '01:00');

-- Soap Seoul 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (8, 'MONDAY', null, null),
    (8, 'TUESDAY', null, null),
    (8, 'WEDNESDAY', '20:00', '02:00'),
    (8, 'THURSDAY', '20:00', '03:00'),
    (8, 'FRIDAY', '20:00', '05:00'),
    (8, 'SATURDAY', '20:00', '06:00'),
    (8, 'SUNDAY', '20:00', '01:00');

-- Club Chroma 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (9, 'MONDAY', '18:00', '02:00'),
    (9, 'TUESDAY', '18:00', '02:00'),
    (9, 'WEDNESDAY', '18:00', '03:00'),
    (9, 'THURSDAY', '18:00', '04:00'),
    (9, 'FRIDAY', '18:00', '05:00'),
    (9, 'SATURDAY', '18:00', '06:00'),
    (9, 'SUNDAY', '18:00', '02:00');

-- SKRT Seoul 운영시간 추가
INSERT INTO club_operating_time (club_id, day_of_week, open_time, close_time)
VALUES
    (10, 'MONDAY', null, null),
    (10, 'TUESDAY', null, null),
    (10, 'WEDNESDAY', '20:00', '02:00'),
    (10, 'THURSDAY', '20:00', '03:00'),
    (10, 'FRIDAY', '20:00', '05:00'),
    (10, 'SATURDAY', '20:00', '06:00'),
    (10, 'SUNDAY', '20:00', '01:00');
