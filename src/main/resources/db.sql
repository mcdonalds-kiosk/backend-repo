CREATE DATABASE IF NOT EXISTS mcdb;

USE mcdb;

CREATE TABLE menu (
                      idx BIGINT PRIMARY KEY AUTO_INCREMENT,
                      `name` VARCHAR(255),
                      image_url VARCHAR(255),
                      price BIGINT,
                      category VARCHAR(255),
                      updated_at DATETIME  DEFAULT (current_date())
);

INSERT INTO menu VALUES ("빅맥", "https://www.mcdonalds.co.kr/upload/product/pcList/1583727841393.png", 5500, "버거&세트", now());
INSERT INTO menu VALUES ("맥치킨 모짜렐라", "https://www.mcdonalds.co.kr/upload/product/pcList/1583727633823.png", 5000, "버거&세트", now());
INSERT INTO menu VALUES ("1955 버거 세트", "https://www.mcdonalds.co.kr/upload/product/pcList/1599120019760.png", 7800, "버거&세트", now());
INSERT INTO menu VALUES ("더블 불고기 버거 세트", "https://www.mcdonalds.co.kr/upload/product/pcList/1583730048428.png", 6000, "버거&세트", now());
INSERT INTO menu VALUES ("베이턴 에그 맥머핀", "https://www.mcdonalds.co.kr/upload/product/pcList/1680763640986.png", 3400, "맥모닝", now());
INSERT INTO menu VALUES ("핫케이크 2조각", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201902111059484920.png", 2800, "맥모닝", now());
INSERT INTO menu VALUES ("맥윙", "https://www.mcdonalds.co.kr/upload/product/pcList/1712028381366.png", 3400, "사이드", now());
INSERT INTO menu VALUES ("상하이 치킨 스낵랩", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201902080435011620.png", 3000, "사이드", now());
INSERT INTO menu VALUES ("아이스크림콘", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201902041118158920.png", 1100, "디저트", now());
INSERT INTO menu VALUES ("오레오 맥플러리", "https://www.mcdonalds.co.kr/upload/product/pcList/1615378570338.png", 3300, "디저트", now());
INSERT INTO menu VALUES ("바닐라 쉐이크", "https://www.mcdonalds.co.kr/uploadFolder/product/prol_201901290255488970.png", 2800, "음료", now());
INSERT INTO menu VALUES ("코카콜라", "https://www.mcdonalds.co.kr/upload/product/pcList/1583889953745.png", 1700, "음료", now());

CREATE TABLE `order`
(
  `idx` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `menu_count` INT NOT NULL,
  `total_price` INT NOT NULL,
  `status` INT NOT NULL,
  `member_idx` INT,
  `purchase_idx` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  -- FOREIGN KEY (`member_idx`) REFERENCES `member`(`idx`),
  -- FOREIGN KEY (`purchase_idx`) REFERENCES `purchase`(`idx`)
);



