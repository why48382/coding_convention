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
-- 샘플 데이터 삽입
-- ===============================

-- users
INSERT INTO users (email, nickname, password, created_at, updated_at, platform, status, enabled)
VALUES
    ('admin@example.com', '관리자', '1234', NOW(), NOW(), 'LOCAL', 'ACTIVE', true),
    ('user1@example.com', '유저1', '1234', NOW(), NOW(), 'LOCAL', 'ACTIVE', true);

-- project
INSERT INTO project (project_name, url, description, language, creator_id)
VALUES
    ('프로젝트A', 'http://example.com/a', '설명 A', 'JAVA', 1),
    ('프로젝트B', 'http://example.com/b', '설명 B', 'PYTHON', 2);

-- files
INSERT INTO files (file_name, created_at, type, save_time_at, project_idx)
VALUES
    ('README.md', NOW(), 'FILE', NOW(), 1),
    ('src', NOW(), 'DIRECTORY', NOW(), 1);

-- project_member
INSERT INTO project_member (status, user_id, project_id)
VALUES
    ('ADMIN', 1, 1),
    ('USER',  2, 1);

-- email_verify
INSERT INTO email_verify (uuid, user_idx)
VALUES
    ('abc123', 1),
    ('xyz456', 2);
