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
