## 댓글 테이블 설정
CREATE TABLE reply(
                      reply_id int primary key auto_increment,
                      blog_id int not null,
                      reply_writer varchar(40) not null,
                      reply_content varchar(200) not null,
                      published_at datetime default now(),
                      updated_at datetime default now()
);
# 외래키 설정
# blog_id 에는 기존에 존재하는 글의 blog_id만 들어가야 한다.
alter table reply add constraint fk_reply foreign key (blog_id) references blog(blog_id);

## 더미 데이터 입력
INSERT INTO reply VALUES(null, 2, "댓글쓴사람", "댓글1", now(), now()),
(null, 2, "루시", "루시 귀여워", now(), now()),
(null, 2, "스키", "스키 귀여워!", now(), now()),
(null, 2, "쵸리", "쵸리 귀여워!!", now(), now()),
(null, 3, "감자", "감자 귀여워!!!", now(), now());
