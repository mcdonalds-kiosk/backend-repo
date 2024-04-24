CREATE DATABASE IF NOT EXISTS mcdb;

USE mcdb;

drop table if exists member;

create table member(
                       idx int PRIMARY KEY AUTO_INCREMENT,
                       id varchar(20),
                       pw varchar(20),
                       name varchar(20),
                       email varchar(100),
                       role varchar(100),
                       join_date date DEFAULT (current_date)
);
DESC member;

INSERT INTO member (id, pw, name, email, role, join_date)
VALUES ('hyorry', '1234', '효리', 'borieya0619@gmail.com', 'user', now());

INSERT INTO member (id, pw, name, email, role, join_date)
VALUES ('admin', '1234', '관리자', 'admin@gmail.com', 'admin', now());
drop table if exists menu;

CREATE TABLE menu (
                      idx BIGINT PRIMARY KEY AUTO_INCREMENT,
                      `name` VARCHAR(255),
                      image_url VARCHAR(255),
                      price BIGINT,
                      category VARCHAR(255),
                      updated_at DATETIME  DEFAULT (current_date())
);

INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("빅맥", "https://www.mcdonalds.co.kr/upload/product/pcList/1583727841393.png", 5500, "버거&세트", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("맥치킨 모짜렐라", "https://www.mcdonalds.co.kr/upload/product/pcList/1583727633823.png", 5000, "버거&세트", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("1955 버거 세트", "https://www.mcdonalds.co.kr/upload/product/pcList/1599120019760.png", 7800, "버거&세트", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("더블 불고기 버거 세트", "https://www.mcdonalds.co.kr/upload/product/pcList/1583730048428.png", 6000, "버거&세트", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("베이턴 에그 맥머핀", "https://www.mcdonalds.co.kr/upload/product/pcList/1680763640986.png", 3400, "맥모닝", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("핫케이크 2조각", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201902111059484920.png", 2800, "맥모닝", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("맥윙", "https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png", 3400, "사이드", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("상하이 치킨 스낵랩", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201902080435011620.png", 3000, "사이드", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("아이스크림콘", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201902041118158920.png", 1100, "디저트", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("오레오 맥플러리", "https://www.mcdonalds.co.kr/upload/product/pcList/1615378570338.png", 3300, "디저트", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("바닐라 쉐이크", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201901290255488970.png", 2800, "음료", now());
INSERT INTO menu (`name`, image_url, price, category, updated_at) VALUES ("코카콜라", "https://www.mcdonalds.co.kr/upload/product/pcList/1583889953745.png", 1700, "음료", now());

drop table if exists purchase;

create table purchase(
                         idx BINARY(16) PRIMARY KEY,
                         member_idx INT,
                         pay_type VARCHAR(20),
                         price INT,
                         payment_key VARCHAR(20),
                         amount INT,
                         point INT,
                         status INT,
                         created_at date DEFAULT (current_date),
                         updated_at date DEFAULT (current_date),
                         CONSTRAINT fk_purchase_member_idx FOREIGN KEY (member_idx) REFERENCES member(idx)
);

drop table if exists `order`;

CREATE TABLE `order`
(
    `idx` BINARY(16) PRIMARY KEY,
    `menu_count` INT NOT NULL,
    `total_price` INT NOT NULL,
    `status` INT NOT NULL,
    `member_idx` INT,
    `purchase_idx` BINARY(16),
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_order_member_idx FOREIGN KEY (member_idx) REFERENCES member(idx),
    CONSTRAINT fk_order_purchase_idx FOREIGN KEY (purchase_idx) REFERENCES purchase(idx)
);
