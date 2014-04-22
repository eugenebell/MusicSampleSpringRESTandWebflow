drop table if exists ALBUM;
drop table if exists SONG;


create table IF NOT EXISTS  ALBUM (
ALBUM_ID integer  primary key AUTO_INCREMENT,
TITLE varchar(50) not null,
ARTIST varchar(50) not null
);

create table IF NOT EXISTS  SONG (
SONG_ID integer  primary key AUTO_INCREMENT,
ALBUM_ID integer not null,
NAME varchar(50) not null,
DURATION varchar(30) not null);

alter table SONG add constraint FK_ALBUM_SONG 
foreign key (ALBUM_ID) references ALBUM(ALBUM_ID) on delete cascade;

/* cheating integration test */
insert into ALBUM (ALBUM_ID, TITLE, ARTIST) values (1, 'Random Access Memories', 'Daft Punk');
insert into ALBUM (ALBUM_ID, TITLE, ARTIST) values (2, 'Bad Blood', 'Bastille');

insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (1, 1, 'Give Life Back to Music', '4:35');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (2, 1, 'The Game of Love', '5:22');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (3, 1, 'Giorgio by Moroder', '9:04');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (4, 1, 'Within', '3:48');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (5, 1, 'Instant Crush', '5:37');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (6, 2, 'Pompeii', '3:34');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (7, 2, 'Things We Lost In The Fire', '4:00');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (8, 2, 'Bad Blood', '3:32');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (9, 2, 'Overjoyed', '3:26');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (10, 2, 'These Streets', '2:55');
insert into SONG (SONG_ID, ALBUM_ID, NAME, DURATION) values (11, 2, 'Weight Of Living, Pt. II', '2:54');

commit;