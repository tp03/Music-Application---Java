ALTER TABLE SONG_DATA ADD picture VARCHAR2(30);

ALTER TABLE SONG DROP COLUMN LENGTH;

ALTER TABLE SONG ADD LENGTH integer not null;

SELECT * FROM SONG_DATA;

INSERT INTO author VALUES (2, 'Zenon Martyniuk', 99999);

INSERT INTO author VALUES (3, 'Strachy na Lachy', 120000);

INSERT INTO author VALUES (4, 'Sławomir', 100000);

INSERT INTO author VALUES (5, 'Szanty', 1000);

INSERT INTO SONG_DATA VALUES (1, 'narazie_nic', 'recordings/chwile.mp3', 'assets/zenek1.png');
INSERT INTO SONG_DATA VALUES (2, 'narazie_nic', 'recordings/gwiazda.mp3', 'assets/zenek2.jpg');
INSERT INTO SONG_DATA VALUES (3, 'narazie_nic', 'recordings/gwiazda.mp3', 'assets/zenek3.jpeg');
INSERT INTO SONG_DATA VALUES (4, 'narazie_nic', 'recordings/chwile.mp3', 'assets/zenek1.png');
INSERT INTO SONG_DATA VALUES (5, 'narazie_nic', 'recordings/pila-tango.mp3', 'assets/strachy.jpg');
INSERT INTO SONG_DATA VALUES (6, 'narazie_nic', 'recordings/slawomir.mp3', 'assets/slawomir.jpg');
INSERT INTO SONG_DATA VALUES (7, 'narazie_nic', 'recordings/przechyly.mp3', 'assets/szanta.png');


INSERT INTO SONG VALUES (1, 'Życie To Są Chwilę', 200, 1, 10000);
INSERT INTO SONG VALUES (2, 'Przez Twe Oczy Zielone', 213, 2, 100010);
INSERT INTO SONG VALUES (3, 'Moja Gwiazda', 300, 3, 100);
INSERT INTO SONG VALUES (4, 'Przekorny Los', 100000, 4, 250);
INSERT INTO SONG VALUES (5, 'Piła Tango', 100000, 5, 200);
INSERT INTO SONG VALUES (6, 'Miłość w Zakopanem', 1000000000, 6, 250);
INSERT INTO SONG VALUES (7, 'Przechyły', 10000, 7, 300);

INSERT INTO AUTHOR_SONGS VALUES (1, 2, 1);
INSERT INTO AUTHOR_SONGS VALUES (2, 2, 2);
INSERT INTO AUTHOR_SONGS VALUES (3, 2, 3);
INSERT INTO AUTHOR_SONGS VALUES (4, 2, 4);
INSERT INTO AUTHOR_SONGS VALUES (5, 3, 5);
INSERT INTO AUTHOR_SONGS VALUES (6, 4, 6);
INSERT INTO AUTHOR_SONGS VALUES (7, 5, 7);

SELECT COUNT(*) FROM app_user;

DELETE FROM APP_USER;

SELECT * FROM APP_USER;

SELECT * FROM app_user WHERE (nick = 'tp03') AND (password = '12345');