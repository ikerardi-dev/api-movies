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
