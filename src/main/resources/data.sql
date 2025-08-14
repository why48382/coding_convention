-- ===============================
-- 데이터베이스 선택
-- ===============================
CREATE DATABASE IF NOT EXISTS project
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_general_ci;
USE project;

-- ===============================
-- 기존 테이블 삭제 (외래키 역순)
-- ===============================
DROP TABLE IF EXISTS email_verify;
DROP TABLE IF EXISTS project_member;
DROP TABLE IF EXISTS files;
DROP TABLE IF EXISTS chat;
DROP TABLE IF EXISTS project;
DROP TABLE IF EXISTS users;

-- ===============================
-- users 테이블 생성
-- ===============================
CREATE TABLE users (
                       idx INT AUTO_INCREMENT PRIMARY KEY,
                       email VARCHAR(120) NOT NULL UNIQUE,
                       nickname VARCHAR(200) NOT NULL,
                       password VARCHAR(100),
                       profile_img VARCHAR(500),
                       nick_updated_at DATETIME,
                       created_at DATETIME NOT NULL,
                       updated_at DATETIME NOT NULL,
                       platform VARCHAR(20) NOT NULL,
                       platform_key VARCHAR(200),
                       status VARCHAR(20) NOT NULL,
                       last_login DATETIME,
                       browser VARCHAR(100),
                       enabled BOOLEAN
);

-- ===============================
-- project 테이블 생성
-- ===============================
CREATE TABLE project (
                         idx INT AUTO_INCREMENT PRIMARY KEY,
                         project_name VARCHAR(255),
                         url VARCHAR(255),
                         description TEXT,
                         language VARCHAR(20),
                         creator_id INT,
                         FOREIGN KEY (creator_id) REFERENCES users(idx) ON DELETE CASCADE
);

-- ===============================
-- files 테이블 생성
-- ===============================
CREATE TABLE files (
                       idx INT AUTO_INCREMENT PRIMARY KEY,
                       file_name VARCHAR(500),
                       created_at DATETIME NOT NULL,
                       type VARCHAR(20) NOT NULL,
                       save_time_at DATETIME NOT NULL,
                       project_idx INT,
                       FOREIGN KEY (project_idx) REFERENCES project(idx) ON DELETE CASCADE
);

-- ===============================
-- project_member 테이블 생성
-- ===============================
CREATE TABLE project_member (
                                idx INT AUTO_INCREMENT PRIMARY KEY,
                                status VARCHAR(20),
                                user_id INT,
                                project_id INT,
                                FOREIGN KEY (user_id) REFERENCES users(idx) ON DELETE CASCADE,
                                FOREIGN KEY (project_id) REFERENCES project(idx) ON DELETE CASCADE
);

-- ===============================
-- email_verify 테이블 생성
-- ===============================
CREATE TABLE email_verify (
                              idx INT AUTO_INCREMENT PRIMARY KEY,
                              uuid VARCHAR(255),
                              user_idx INT,
                              FOREIGN KEY (user_idx) REFERENCES users(idx) ON DELETE CASCADE
);

-- ===============================
-- chat 테이블 생성
-- ===============================
CREATE TABLE chats (
                      idx INT AUTO_INCREMENT PRIMARY KEY,
                      project_id INT,
                      user_id INT,
                      message TEXT,
                      sent_at DATETIME NOT NULL,
                      FOREIGN KEY (project_id) REFERENCES project(idx) ON DELETE CASCADE,
                      FOREIGN KEY (user_id) REFERENCES users(idx) ON DELETE CASCADE
);


-- ===============================
-- 샘플 데이터 삽입
-- ===============================

-- users (기존 데이터 유지)
INSERT INTO users (email, nickname, password, created_at, updated_at, platform, status, enabled)
VALUES
    ('admin@example.com', '관리자', '1234', NOW(), NOW(), 'LOCAL', 'ACTIVE', true),
    ('user1@example.com', '유저1', '1234', NOW(), NOW(), 'LOCAL', 'ACTIVE', true);

-- project (총 3개 프로젝트 생성)
INSERT INTO project (project_name, url, description, language, creator_id)
VALUES
    ('프로젝트 A - 웹사이트', 'http://project.a.com', '반응형 웹사이트 제작', 'JAVASCRIPT', 1),
    ('프로젝트 B - AI 챗봇', 'http://project.b.com', '자연어 처리 기반 챗봇 개발', 'PYTHON', 2),
    ('프로젝트 C - 모바일 앱', 'http://project.c.com', '안드로이드/iOS 앱 개발', 'JAVA', 1);

-- files (각 프로젝트마다 여러 개의 파일 추가)
INSERT INTO files (file_name, created_at, type, save_time_at, project_idx)
VALUES
    -- 프로젝트 A 파일
    ('index.js', NOW(), 'FILE', NOW(), 1),
    ('style.css', NOW(), 'FILE', NOW(), 1),
    ('README.md', NOW(), 'FILE', NOW(), 1),
    ('assets/logo.png', NOW(), 'FILE', NOW(), 1),

    -- 프로젝트 B 파일
    ('main.py', NOW(), 'FILE', NOW(), 2),
    ('data.json', NOW(), 'FILE', NOW(), 2),
    ('model.h5', NOW(), 'FILE', NOW(), 2),
    ('requirements.txt', NOW(), 'FILE', NOW(), 2),

    -- 프로젝트 C 파일
    ('MainActivity.java', NOW(), 'FILE', NOW(), 3),
    ('strings.xml', NOW(), 'FILE', NOW(), 3),
    ('layout/activity_main.xml', NOW(), 'FILE', NOW(), 3),
    ('AndroidManifest.xml', NOW(), 'FILE', NOW(), 3);

-- project_member (각 프로젝트에 멤버 추가)
INSERT INTO project_member (status, user_id, project_id)
VALUES
    ('ADMIN', 1, 1), -- 관리자가 프로젝트 A 멤버 (생성자)
    ('USER', 2, 1), -- 유저1이 프로젝트 A 멤버

    ('ADMIN', 2, 2), -- 유저1이 프로젝트 B 멤버 (생성자)
    ('USER', 1, 2), -- 관리자가 프로젝트 B 멤버

    ('ADMIN', 1, 3), -- 관리자가 프로젝트 C 멤버 (생성자)
    ('USER', 2, 3); -- 유저1이 프로젝트 C 멤버

-- chat (각 프로젝트에 여러 개의 메시지 추가)
INSERT INTO chats (project_id, user_id, message, sent_at)
VALUES
    -- 프로젝트 A 채팅
    (1, 1, '웹사이트 랜딩 페이지 디자인 초안을 공유했습니다.', NOW()),
    (1, 2, '확인했습니다! 피드백 드릴게요. 이미지 파일은 assets 폴더에 넣으면 되겠죠?', NOW()),
    (1, 1, '네, 맞아요. 로고 파일도 같이 업로드했습니다.', NOW()),
    (1, 2, '좋습니다. css 수정해서 레이아웃 잡아볼게요.', NOW()),

    -- 프로젝트 B 채팅
    (2, 2, '챗봇 학습 데이터셋을 업데이트했습니다.', NOW()),
    (2, 1, '고생 많으셨습니다. 모델 재학습 돌려보겠습니다.', NOW()),
    (2, 2, '모델 성능 개선을 위해 데이터 전처리 스크립트를 수정해볼까요?', NOW()),
    (2, 1, '좋은 생각입니다. requirements.txt에 필요한 라이브러리 추가해주세요.', NOW()),

    -- 프로젝트 C 채팅
    (3, 1, '앱 기능 명세서 최종본입니다.', NOW()),
    (3, 2, '새로운 푸시 알림 기능에 대해 논의가 필요할 것 같아요.', NOW()),
    (3, 1, '네, 좋아요. 어떤 부분에 대해 논의하면 좋을까요?', NOW()),
    (3, 2, 'AndroidManifest.xml 설정에 대해서 얘기해보면 좋겠습니다.', NOW());

-- email_verify (기존 데이터 유지)
INSERT INTO email_verify (uuid, user_idx)
VALUES
    ('abc123', 1),
    ('xyz456', 2);
