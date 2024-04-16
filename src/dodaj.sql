ALTER TABLE SONG_DATA ADD picture VARCHAR2(30);

ALTER TABLE SONG DROP COLUMN LENGTH;

ALTER TABLE SONG ADD LENGTH integer not null;

SELECT * FROM SONG;

INSERT INTO author VALUES (2, 'Zenon Martyniuk', 99999);

INSERT INTO SONG_DATA VALUES (1, 'narazie_nic', 'recordings/chwile.mp3', 'assets/zenek1.png');
INSERT INTO SONG_DATA VALUES (2, 'narazie_nic', 'recordings/gwiazda.mp3', 'assets/zenek2.jpg');
INSERT INTO SONG_DATA VALUES (3, 'narazie_nic', 'recordings/gwiazda.mp3', 'assets/zenek3.jpeg');

INSERT INTO SONG VALUES (1, 'Życie To Są Chwilę', 200, 1, 10000);
INSERT INTO SONG VALUES (2, 'Przez Twe Oczy Zielone', 213, 2, 100010);
INSERT INTO SONG VALUES (3, 'Moja Gwiazda', 300, 3, 100);

INSERT INTO AUTHOR_SONGS VALUES (1, 2, 1);
INSERT INTO AUTHOR_SONGS VALUES (2, 2, 2);
INSERT INTO AUTHOR_SONGS VALUES (3, 2, 3);

SELECT * FROM SONG;