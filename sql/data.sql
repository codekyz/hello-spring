-- =============================================================
-- Seed Data — 개발/테스트 환경 초기 데이터
-- 실행 순서: users → books → reading → records → bookmarks → clubs → club_members
-- 비밀번호는 BCrypt 인코딩 값 (원문: "password1234")
-- =============================================================

-- -------------------------------------------------------------
-- 1. USERS
-- -------------------------------------------------------------
INSERT INTO users (email, password, nickname, profile_image, created_at, updated_at)
VALUES
  ('alice@example.com',
   '$2a$12$wdY6PS5HHkQ4nNBTPNJiROCXh4wr7MQ9HXS9F.zDXX5GWkVwxFKWK',
   '앨리스', NULL, NOW(), NOW()),

  ('bob@example.com',
   '$2a$12$wdY6PS5HHkQ4nNBTPNJiROCXh4wr7MQ9HXS9F.zDXX5GWkVwxFKWK',
   '밥', NULL, NOW(), NOW()),

  ('carol@example.com',
   '$2a$12$wdY6PS5HHkQ4nNBTPNJiROCXh4wr7MQ9HXS9F.zDXX5GWkVwxFKWK',
   '캐롤', NULL, NOW(), NOW());

-- -------------------------------------------------------------
-- 2. BOOKS  (외부 API 캐시 시뮬레이션)
-- -------------------------------------------------------------
INSERT INTO books (isbn, title, author, publisher, cover_image, description, published_date)
VALUES
  ('9788936434120',
   '채식주의자',
   '한강',
   '창비',
   'https://example.com/covers/vegetarian.jpg',
   '세 편의 연작 소설로 이루어진 한강의 장편소설.',
   '2007-10-30'),

  ('9788954651288',
   '클루지',
   '게리 마커스',
   '갈매나무',
   'https://example.com/covers/kluge.jpg',
   '인간 마음의 진화적 결함을 탐구하는 과학 교양서.',
   '2009-04-15'),

  ('9788936472542',
   '소년이 온다',
   '한강',
   '창비',
   'https://example.com/covers/human-acts.jpg',
   '5.18 광주 민주화운동을 다룬 소설.',
   '2014-05-19'),

  ('9788932920238',
   '파친코',
   '이민진',
   '문학사상',
   'https://example.com/covers/pachinko.jpg',
   '재일 조선인 가족 4대의 이야기.',
   '2022-03-25');

-- -------------------------------------------------------------
-- 3. READING  (독서 현황)
-- -------------------------------------------------------------
INSERT INTO reading (user_id, book_id, status, started_at, finished_at, created_at, updated_at)
VALUES
  -- alice: 채식주의자 완독
  (1, 1, 'COMPLETED', '2025-01-10', '2025-01-20', NOW(), NOW()),
  -- alice: 클루지 읽는 중
  (1, 2, 'READING',   '2025-04-01', NULL,          NOW(), NOW()),
  -- bob: 소년이 온다 완독
  (2, 3, 'COMPLETED', '2025-02-05', '2025-02-15', NOW(), NOW()),
  -- bob: 파친코 중단
  (2, 4, 'STOPPED',   '2025-03-01', NULL,          NOW(), NOW()),
  -- carol: 파친코 읽는 중
  (3, 4, 'READING',   '2025-04-10', NULL,          NOW(), NOW());

-- -------------------------------------------------------------
-- 4. RECORDS  (독서 기록 — 별점 + 감상문)
-- -------------------------------------------------------------
INSERT INTO records (user_id, book_id, reading_id, rating, content, created_at, updated_at)
VALUES
  -- alice: 채식주의자 5점
  (1, 1, 1, 5.0,
   '한강 작가의 문체가 너무 아름다웠다. 세 편 모두 연결되면서도 각자 독립적인 이야기가 인상적이었다.',
   NOW(), NOW()),

  -- bob: 소년이 온다 4.5점
  (2, 3, 3, 4.5,
   '역사의 상처를 이렇게 섬세하게 담아낸 소설은 처음이었다. 읽는 내내 마음이 무거웠지만 꼭 읽어야 할 책.',
   NOW(), NOW());

-- -------------------------------------------------------------
-- 5. BOOKMARKS  (밑줄긋기)
-- -------------------------------------------------------------
INSERT INTO bookmarks (user_id, book_id, page, content, created_at)
VALUES
  (1, 1, 47,  '나는 꿈을 꾼다. 그러므로 나는 산다.',       NOW()),
  (1, 1, 112, '폭력은 항상 선택이었다.',                   NOW()),
  (2, 3, 88,  '우리가 기억해야 한다는 것, 그것만이 진실이다.', NOW()),
  (3, 4, 23,  '역사는 그들을 지워버렸지만, 삶은 계속됐다.',   NOW());

-- -------------------------------------------------------------
-- 6. CLUBS  (독서 클럽)
-- -------------------------------------------------------------
INSERT INTO clubs (owner_id, name, description, max_members, is_public, created_at, updated_at)
VALUES
  (1, '한강 소설 읽기 모임',
   '한강 작가의 소설을 함께 읽고 이야기 나눕니다.',
   10, TRUE, NOW(), NOW()),

  (2, '과학·인문 교양 독서단',
   '과학과 인문학의 경계를 넘나드는 교양서를 읽습니다.',
   20, TRUE, NOW(), NOW()),

  (3, '비공개 독서 써클',
   '초대된 멤버만 참여할 수 있는 소규모 모임.',
   5, FALSE, NOW(), NOW());

-- -------------------------------------------------------------
-- 7. CLUB_MEMBERS  (멤버십)
-- -------------------------------------------------------------
INSERT INTO club_members (club_id, user_id, role, joined_at)
VALUES
  -- 클럽 1: alice(OWNER), bob(MEMBER), carol(MEMBER)
  (1, 1, 'OWNER',  NOW()),
  (1, 2, 'MEMBER', NOW()),
  (1, 3, 'MEMBER', NOW()),

  -- 클럽 2: bob(OWNER), alice(MEMBER)
  (2, 2, 'OWNER',  NOW()),
  (2, 1, 'MEMBER', NOW()),

  -- 클럽 3: carol(OWNER)
  (3, 3, 'OWNER',  NOW());
