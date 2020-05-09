-- inicjalizacja bazy danych: tworzenie tabel

create table blog_post (
	id integer auto_increment primary key,
	title varchar(250),
    content text
);
