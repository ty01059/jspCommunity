DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50) NOT NULL,
    `nickname` CHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    loginId CHAR(50) NOT NULL UNIQUE,
    loginPw VARCHAR(200) NOT NULL,
    adminLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증된,4=관리자',
    `pwDate` DATETIME NOT NULL
);

# 회원1 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "김민수",
`nickname` = "강바람",
`email` = "jangka512@gmail.com",
loginId = "user1",
loginPw = "user1",
`pwDate` = DATE_ADD(NOW(), INTERVAL 90 DAY);

# 회원2 생성
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "김미소",
`nickname` = "이또한지나가리라",
`email` = "jangka512@gmail.com",
loginId = "user2",
loginPw = "user2",
`pwDate` = DATE_ADD(NOW(), INTERVAL 90 DAY);

# 게시판 테이블 생성`member`
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(10) NOT NULL UNIQUE,
    `name` CHAR(10) NOT NULL UNIQUE
);

# 공지사항 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지사항';

# 방명록 게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'guestBook',
`name` = '방명록';

# 자유게시판 생성
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';

# 게시물 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    boardId INT(10) UNSIGNED NOT NULL,
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    hitsCount INT(10) UNSIGNED NOT NULL DEFAULT 0
);

# 테스트 게시물 생성
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목1',
`body` = '내용1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목2',
`body` = '내용2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '제목3',
`body` = '내용3';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '제목4',
`body` = '내용4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '제목5',
`body` = '내용5';

# cellphoneNo 추가 및 칼럼 순서 재정렬
ALTER TABLE `member` CHANGE `loginId` `loginId` CHAR(50) NOT NULL AFTER `updateDate`,
                     CHANGE `loginPw` `loginPw` VARCHAR(200) NOT NULL AFTER `loginId`,
                     ADD COLUMN `cellphoneNo` CHAR(20) NOT NULL AFTER `email`;
                     
# adminLevel을 authLevel로 변경
ALTER TABLE `member` CHANGE `adminLevel` `authLevel` TINYINT(1) UNSIGNED DEFAULT 2 NOT NULL COMMENT '0=탈퇴/1=로그인정지/2=일반/3=인증된,4=관리자';

# 임시패스워드 사용 여부
ALTER TABLE `member` add `tempPw` TINYINT(1) UNSIGNED NOT NULL '0=임시비번X, 1=임시비번사용중';

create table attr (
	id int(10) unsigned not null primary key auto_increment,
	regDate datetime not null,
	updateDate datetime not null,
	`relTypeCode` char(20) not null,
	`relId` int(10) unsigned not null,
	`typeCode` char(30) not null,
	`type2Code` char(30) not null,
	`value` char(30) not null,
	`expireDate` datetime not null
);

# attr 유니크 인덱스 걸기
# 중복변수 생성금지
# 변수찾는 속도 최적화
alter table `attr` add unique index (`relTypeCode`, `relId`, `typeCode`, `type2Code`);

# 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서
alter table `attr` add index(`relTypeCode`, `typeCode`, `type2Code`);

select now(), date_add(now(), INTERVAL 90 DAY);