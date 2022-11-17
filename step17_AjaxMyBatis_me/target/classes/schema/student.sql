select * from emp; -- alt+x


drop table student;
create table student(
  st_no varchar2(3) primary key,
  st_name varchar2(15) ,
  st_age int,
  st_addr varchar2(50),
  st_phone varchar2(20)
)

insert into student values('A01','장희정',10,'건국대학','1111');
insert into student values('A02','하승선',20,'CJ네트웍','222');

commit
select * from student where st_no='A01'
select * from student where st_no='A02'
select * from student














