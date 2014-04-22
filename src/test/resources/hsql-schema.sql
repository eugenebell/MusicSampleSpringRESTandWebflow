drop table if exists SONG;
drop table if exists ALBUM;


create table IF NOT EXISTS ALBUM (
ALBUM_ID integer identity primary key,
TITLE varchar(50) not null,
ARTIST varchar(50) not null
);

create table IF NOT EXISTS SONG (
SONG_ID integer identity primary key,
ALBUM_ID integer not null,
NAME varchar(50) not null,
DURATION varchar(30) not null);

alter table SONG add constraint FK_ALBUM_SONG 
foreign key (ALBUM_ID) references ALBUM(ALBUM_ID) on delete cascade;
