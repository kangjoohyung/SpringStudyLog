DROP TABLE MEMBER cascade constraint;

CREATE TABLE MEMBER(
	MEMBER_ID VARCHAR2(20) PRIMARY KEY, /*ȸ�� ID */
	MEMBER_PASSWORD CHAR(60) NOT NULL, /*��й�ȣ*/
 	MEMBER_NAME VARCHAR2(100) NOT NULL, /*�̸�*/
	MEMBER_EMAIL VARCHAR2(100) NOT NULL /*�̸����ּ�*/
);

--���� (admin-admin)
insert into member values('admin', '$2a$10$fbS1tn.E2iOsQ0aoDe5yQulgq.Z0tJUpSlWIzFvaVsi9JzvGzLs.m', '������', 'admindaum.net');

/*********************************���� ���̺�********************************/
/*
 *  - ROLE_ADMIN
 *    - ROLE_MEMBER
 */
DROP TABLE AUTHORITIES;

CREATE TABLE AUTHORITIES(
	USERNAME VARCHAR2(100) NOT NULL, /* ID*/
	ROLE VARCHAR(30) NOT NULL,    /**/
	CONSTRAINT MEMBER_AUTHORITIES_FK FOREIGN KEY(USERNAME) REFERENCES MEMBER(MEMBER_ID),
	CONSTRAINT AUTHORITIES_PK PRIMARY KEY(USERNAME,ROLE)
);

insert into authorities values('admin', 'ROLE_ADMIN');
insert into authorities values('admin', 'ROLE_MEMBER');


commit





