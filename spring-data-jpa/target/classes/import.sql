insert into library values (1, 'Biblioteka Miejska');
insert into library values (2, 'Biblioteka Publiczna');
insert into library values (3, 'Biblioteka Wrocławska');

insert into book values (1, 'Pierwsza książka',1);
insert into book values (2, 'Druga książka',1);
insert into book values (3, 'Trzecia książka',1);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
