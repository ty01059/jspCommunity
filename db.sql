DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;

# ȸ�� ���̺� ����
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `name` CHAR(50) NOT NULL,
    `nickname` CHAR(50) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    loginId CHAR(50) NOT NULL UNIQUE,
    loginPw VARCHAR(200) NOT NULL,
    adminLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=Ż��/1=�α�������/2=�Ϲ�/3=������,4=������'
);

# ȸ��1 ����
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "��μ�",
`nickname` = "���ٶ�",
`email` = "jangka512@gmail.com",
loginId = "user1",
loginPw = "user1";

# ȸ��2 ����
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
`name` = "��̼�",
`nickname` = "�̶�������������",
`email` = "jangka512@gmail.com",
loginId = "user2",
loginPw = "user2";

# �Խ��� ���̺� ����
CREATE TABLE board (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    `code` CHAR(10) NOT NULL UNIQUE,
    `name` CHAR(10) NOT NULL UNIQUE
);

# �������� �Խ��� ����
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '��������';

# ���� �Խ��� ����
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'guestBook',
`name` = '����';

# �����Խ��� ����
INSERT INTO board
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '����';

# �Խù� ���̺� ����
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

# �׽�Ʈ �Խù� ����
INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '����1',
`body` = '����1';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '����2',
`body` = '����2';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 1,
boardId = 1,
title = '����3',
`body` = '����3';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '����4',
`body` = '����4';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
memberId = 2,
boardId = 1,
title = '����5',
`body` = '����5';

# cellphoneNo �߰� �� Į�� ���� ������
ALTER TABLE `member` CHANGE `loginId` `loginId` CHAR(50) NOT NULL AFTER `updateDate`,
                     CHANGE `loginPw` `loginPw` VARCHAR(200) NOT NULL AFTER `loginId`,
                     ADD COLUMN `cellphoneNo` CHAR(20) NOT NULL AFTER `email`;
                     
# adminLevel�� authLevel�� ����
ALTER TABLE `member` CHANGE `adminLevel` `authLevel` TINYINT(1) UNSIGNED DEFAULT 2 NOT NULL COMMENT '0=Ż��/1=�α�������/2=�Ϲ�/3=������,4=������';

# ����ȸ���� ����� ��ȣȭ
UPDATE `member`
SET loginPw = SHA2(loginPw, 256);

# �ΰ����� ���̺�
# attr ���ᳯ¥ �߰�
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

# attr ����ũ �ε��� �ɱ�
# �ߺ����� ��������
# ����ã�� �ӵ� ����ȭ
alter table `attr` add unique index (`relTypeCode`, `relId`, `typeCode`, `type2Code`);

# Ư�� ������ �����ϴ� ȸ�� �Ǵ� �Խù�(��Ÿ ������)�� ������ ã�� ���ؼ�
alter table `attr` add index(`relTypeCode`, `typeCode`, `type2Code`);