--테이블 삭제
drop table gallery;

--시퀀스 삭제
drop sequence seq_gallery_no;

--table 생성
create table gallery (
    no          number,
    user_no     number,
    content     varchar2(1000),
    filePath    varchar2(500),
    orgName     varchar2(500),
    saveName    varchar2(500),
    fileSize    number,
    primary key(no),
    constraint gallery_fk foreign key (user_no)
    references users(no)
);
    
--시퀀스 생성
CREATE SEQUENCE seq_gallery_no
INCREMENT BY 1 
START WITH 1 ;

--insert
insert into gallery
values(
    seq_gallery_no.nextval,
    1,
    '유재석 사진 입니다.',
    'C:\javaStudy\upload\16281439718838a01bc1e-18ff-4072-8a9c-163b8405a992.jpg',
    'Yoo-Jae-Suk.jpg',
    '16281439718838a01bc1e-18ff-4072-8a9c-163b8405a992.jpg',
    82185
);
  
--select
select  g.no, g.user_no, g.content, g.filePath, g.orgName, g.saveName, g.fileSize,
        u.no, u.id, u.password, u.name, u.gender
from gallery g, users u
where g.user_no = u.no;

--확인용
select *
from gallery;

commit;
rollback;
    