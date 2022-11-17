
drop table users;

  SELECT * FROM commentb;--게시물테이블
  SELECT * FROM users; --사용자
  SELECT * FROM reply; --답변
  
    CREATE TABLE users(
     user_id VARCHAR2(20) PRIMARY KEY,
     user_name VARCHAR2(30)
  );
  select * from users;
  
  INSERT INTO users VALUES('jang','장희정');
  INSERT INTO users VALUES('kim','김종연');
  
  CREATE TABLE commentb(
     comment_No INT PRIMARY KEY, --글번호 
     user_id VARCHAR2(20) REFERENCES users(user_id), --작성자
     comment_Content VARCHAR2(100),
     reg_Date date
  );
  
  INSERT INTO commentb VALUES(1,'jang','호호',sysdate);
  INSERT INTO commentb VALUES(2,'jang','즐거운 JDBC',sysdate);
  INSERT INTO commentb VALUES(3,'kim','즐거운 oracle',sysdate);
  INSERT INTO commentb VALUES(4,'kim','최고의 cj',sysdate);
  INSERT INTO commentb VALUES(5,'jang','즐거운 mybatis',sysdate);
  

  CREATE TABLE reply(
    reply_no INT PRIMARY KEY, -- 답글번호
    comment_No INT REFERENCES commentb(comment_No), --부모글번호
    user_id VARCHAR2(20) REFERENCES users(user_id),
    reply_content VARCHAR2(100),
    reg_date date
  );
  
  INSERT INTO reply VALUES(10,1,'kim', '1번의 답글 -1', SYSDATE);
  INSERT INTO reply VALUES(20,1,'jang', '1번의 답글 -2', SYSDATE);
  INSERT INTO reply VALUES(30,1,'kim', '1번의 답글 -3', SYSDATE);
  
  INSERT INTO reply VALUES(40,3,'jang', '3번의 답글 -1', SYSDATE);
  INSERT INTO reply VALUES(50,3,'kim', '3번의 답글 -2', SYSDATE);
  
  commit;
  drop table reply;
  
  select * from users;
  select * from commentb;
   select * from reply;
   
   
  
  --1:1의 관계 문제1) 한개의 Comment는 한명의 작성자 정보를 가진다.
  SELECT c.comment_No, c.user_id, u.user_name, c.comment_Content, c.reg_Date
		FROM commentb c JOIN users u
		ON c.user_id = u.user_id
        
        
		
--1:다의 관계 문제2) 한개의 Comment는 여러명의 유저가 작성할 수 있다.		
  SELECT c.comment_no, c.user_id, c.comment_Content, 
       r.reply_no, r.reply_content,r.user_id, r.reg_date
  FROM commentB c JOIN reply r
  ON c.comment_no = r.comment_no
  
  ----------------------------------------------
  
  
  SELECT * FROM emp;
  SELECT * FROM dept;
  SELECT * FROM salgrade;
  
  SELECT d.dname, d.loc, e.empno, e.ename
  FROM emp e JOIN dept d
  ON e.deptno = d.deptno
  
  
  
  SELECT empno, ename, job, sal, grade, losal, hisal
  FROM emp e JOIN salgrade s
  ON e.sal BETWEEN s.losal AND s.hisal
  
  
  
  
  
  SELECT c.comment_no,  c.comment_Content, 
       r.reply_no, r.reply_content, r.user_id,  r.reg_date
     FROM commentB c JOIN reply r
     ON c.comment_no = r.comment_no
  
  
  
  select  comment_no , user_id, comment_content, reg_date
  from commentb
  
   INSERT INTO commentb VALUES(8,'jang',null,sysdate);
  commit
  
  
  
  SELECT c.comment_no, c.user_id, c.comment_Content, 
       r.reply_no, r.reply_content,r.user_id  , r.reg_date
	   FROM commentB c JOIN reply r
	   ON c.comment_no = r.comment_no 
  
  
  
  
  
