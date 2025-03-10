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

-- Event 데이터 삽입
INSERT INTO event (title, thumbnail_image_url, price, summary, start_date, end_date, club_id)
VALUES
    ('EDM Night Fever', 'https://example.com/event1.jpg', 30000, '강렬한 EDM 파티', '2024-04-01', '2024-04-02', 1),
    ('HipHop Night Show', 'https://example.com/event2.jpg', 25000, '힙합 뮤지션들과 함께', '2024-04-03', '2024-04-04', 2),
    ('Techno Rave', 'https://example.com/event3.jpg', 35000, '테크노 뮤직의 밤', '2024-04-05', '2024-04-06', 3),
    ('House Music Party', 'https://example.com/event4.jpg', 20000, '하우스 뮤직을 즐기자', '2024-04-07', '2024-04-08', 4),
    ('K-POP Dance Night', 'https://example.com/event5.jpg', 28000, 'K-POP과 함께 춤을', '2024-04-09', '2024-04-10', 5),
    ('Reggaeton Fiesta', 'https://example.com/event6.jpg', 27000, '레게톤과 함께하는 핫한 밤', '2024-04-11', '2024-04-12', 6),
    ('RnB Soul Night', 'https://example.com/event7.jpg', 22000, '소울풀한 R&B의 세계', '2024-04-13', '2024-04-14', 7),
    ('Funk & Groove Party', 'https://example.com/event8.jpg', 24000, '펑키한 음악과 함께', '2024-04-15', '2024-04-16', 8),
    ('Indie Music Showcase', 'https://example.com/event9.jpg', 18000, '인디 아티스트 공연', '2024-04-17', '2024-04-18', 9),
    ('Chroma Electronic Festival', 'https://example.com/event10.jpg', 40000, '최고의 일렉트로닉 음악 축제', '2024-04-19', '2024-04-20', 10),
    ('Underground HipHop Battle', 'https://example.com/event11.jpg', 23000, '언더그라운드 래퍼 배틀', '2024-04-21', '2024-04-22', 2),
    ('Sunset Rooftop Party', 'https://example.com/event12.jpg', 28000, '루프탑에서 즐기는 EDM', '2024-04-23', '2024-04-24', 3),
    ('Latin Night Special', 'https://example.com/event13.jpg', 26000, '라틴 댄스 파티', '2024-04-25', '2024-04-26', 5),
    ('Boombar HipHop Night', 'https://example.com/event14.jpg', 20000, 'Boombar에서 힙합 밤', '2024-04-27', '2024-04-28', 7),
    ('Soap Seoul Indie Vibes', 'https://example.com/event15.jpg', 17000, '인디 뮤직과 함께하는 특별한 밤', '2024-04-29', '2024-04-30', 8),
    ('FAUST House Legends', 'https://example.com/event16.jpg', 31000, '하우스 뮤직의 전설들', '2024-05-01', '2024-05-02', 6),
    ('Arena EDM Festival', 'https://example.com/event17.jpg', 39000, 'Arena에서 펼쳐지는 EDM 대축제', '2024-05-03', '2024-05-04', 2),
    ('SKRT Seoul Funky Night', 'https://example.com/event18.jpg', 20000, 'SKRT Seoul에서 즐기는 펑키 나이트', '2024-05-05', '2024-05-06', 10),
    ('Made Itaewon Latin Party', 'https://example.com/event19.jpg', 27000, 'Made Itaewon에서 라틴 뮤직과 함께', '2024-05-07', '2024-05-08', 3),
    ('Chroma K-POP Festival', 'https://example.com/event20.jpg', 35000, 'K-POP 스타들과 함께하는 축제', '2024-05-09', '2024-05-10', 9);
