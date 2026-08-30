-- Sample data so the API can be tried out right after startup.
-- Ids are assigned automatically (IDENTITY) in the same order as insertion.

-- ==============================
-- GENRES (1..6)
-- ==============================
INSERT INTO genres (name) VALUES ('Science Fiction'); -- 1
INSERT INTO genres (name) VALUES ('Drama');           -- 2
INSERT INTO genres (name) VALUES ('Action');          -- 3
INSERT INTO genres (name) VALUES ('Comedy');          -- 4
INSERT INTO genres (name) VALUES ('Horror');          -- 5
INSERT INTO genres (name) VALUES ('Animation');       -- 6

-- ==============================
-- YEARS (1..6)
-- ==============================
INSERT INTO years (year_value) VALUES (1994); -- 1
INSERT INTO years (year_value) VALUES (1999); -- 2
INSERT INTO years (year_value) VALUES (2010); -- 3
INSERT INTO years (year_value) VALUES (2014); -- 4
INSERT INTO years (year_value) VALUES (2019); -- 5
INSERT INTO years (year_value) VALUES (2023); -- 6

-- ==============================
-- ACTORS (1..10)
-- ==============================
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Tim Robbins', 'American', '1958-10-16');           -- 1
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Morgan Freeman', 'American', '1937-06-01');        -- 2
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Keanu Reeves', 'Canadian', '1964-09-02');          -- 3
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Carrie-Anne Moss', 'Canadian', '1967-08-21');      -- 4
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Leonardo DiCaprio', 'American', '1974-11-11');     -- 5
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Joseph Gordon-Levitt', 'American', '1981-02-17');  -- 6
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Scarlett Johansson', 'American', '1984-11-22');    -- 7
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Robert Downey Jr.', 'American', '1965-04-04');     -- 8
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Lupita Nyong''o', 'Kenyan-Mexican', '1983-03-01'); -- 9
INSERT INTO actors (name, nationality, date_of_birth) VALUES ('Jordan Peele', 'American', '1979-02-21');          -- 10

-- ==============================
-- MOVIES
-- (genre_id, year_id reference the tables above)
-- ==============================
INSERT INTO movies (title, synopsis, duration_minutes, director, genre_id, year_id) VALUES
  ('The Shawshank Redemption', 'A banker is convicted of a crime he did not commit and seeks redemption in Shawshank prison.', 142, 'Frank Darabont', 2, 1); -- 1

INSERT INTO movies (title, synopsis, duration_minutes, director, genre_id, year_id) VALUES
  ('The Matrix', 'A programmer discovers that reality is a simulation controlled by machines.', 136, 'Lana Wachowski', 1, 2); -- 2

INSERT INTO movies (title, synopsis, duration_minutes, director, genre_id, year_id) VALUES
  ('Inception', 'A thief who steals secrets through dreams is given the inverse task: to plant an idea.', 148, 'Christopher Nolan', 1, 3); -- 3

INSERT INTO movies (title, synopsis, duration_minutes, director, genre_id, year_id) VALUES
  ('The Avengers', 'A team of heroes joins forces to face a global threat.', 143, 'Joss Whedon', 3, 4); -- 4

INSERT INTO movies (title, synopsis, duration_minutes, director, genre_id, year_id) VALUES
  ('Us', 'A family is confronted by their sinister doubles during a summer vacation.', 116, 'Jordan Peele', 5, 5); -- 5

INSERT INTO movies (title, synopsis, duration_minutes, director, genre_id, year_id) VALUES
  ('Spider-Man: Across the Spider-Verse', 'Miles Morales travels across the multiverse alongside other Spider-people.', 140, 'Joaquim Dos Santos', 6, 6); -- 6

-- ==============================
-- N:M RELATION MOVIE <-> ACTOR
-- ==============================
INSERT INTO movie_actor (movie_id, actor_id) VALUES (1, 1); -- The Shawshank Redemption - Tim Robbins
INSERT INTO movie_actor (movie_id, actor_id) VALUES (1, 2); -- The Shawshank Redemption - Morgan Freeman

INSERT INTO movie_actor (movie_id, actor_id) VALUES (2, 3); -- The Matrix - Keanu Reeves
INSERT INTO movie_actor (movie_id, actor_id) VALUES (2, 4); -- The Matrix - Carrie-Anne Moss

INSERT INTO movie_actor (movie_id, actor_id) VALUES (3, 5); -- Inception - Leonardo DiCaprio
INSERT INTO movie_actor (movie_id, actor_id) VALUES (3, 6); -- Inception - Joseph Gordon-Levitt

INSERT INTO movie_actor (movie_id, actor_id) VALUES (4, 7); -- The Avengers - Scarlett Johansson
INSERT INTO movie_actor (movie_id, actor_id) VALUES (4, 8); -- The Avengers - Robert Downey Jr.

INSERT INTO movie_actor (movie_id, actor_id) VALUES (5, 9);  -- Us - Lupita Nyong'o
INSERT INTO movie_actor (movie_id, actor_id) VALUES (5, 10); -- Us - Jordan Peele (cameo)

INSERT INTO movie_actor (movie_id, actor_id) VALUES (6, 6); -- Spider-Man - Joseph Gordon-Levitt (fictional cameo for the example)
