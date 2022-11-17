create table suggest(
  num int ,
  word varchar2(100)
)

	    
insert into suggest values(1, 'Ajax');
insert into suggest values(2, 'Ajax 실전 프로그래밍');
insert into suggest values(3, '자라');
insert into suggest values(4, '자바');
insert into suggest values(4, '자바 프로그래밍');
insert into suggest values(5, '자바 서버 페이지');
insert into suggest values(6, '웹프로그래밍');
insert into suggest values(7, '웹마스터');
insert into suggest values(8, '웹디자인');
insert into suggest values(9, '자바스터디');
insert into suggest values(10, '자바 서비스');
insert into suggest values(11, '자 캔');



select word from suggest 
where lower(word) like lower('a%');



commit












