-- 2013-11-23 --
CREATE TABLE `board` (
  `bno` int NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `writer` varchar(100) NOT NULL,
  `content` text,
  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
  `moddate` datetime DEFAULT CURRENT_TIMESTAMP,
  `readcount` int DEFAULT '0',
  PRIMARY KEY (`bno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci

-- 2023-12-10  -- 
create table comment(
cno int auto_increment,
bno int not null,
writer varchar(100) default "unkmown",
content varchar(1000) not null,
regdate datetime default now(),
primary key(cno));

-- 2023-12-11 -- 
alter table board add imageFile varchar(100);