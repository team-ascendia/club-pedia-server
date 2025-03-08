-- Region 기본 데이터
INSERT INTO region(id, title, sequence)
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
