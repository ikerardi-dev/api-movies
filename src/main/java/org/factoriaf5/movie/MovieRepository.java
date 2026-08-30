package org.factoriaf5.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * MovieRepository
 * JPA Query Methods:
 * https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html
 */
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    // Search by title (contains, case-insensitive)
    List<MovieEntity> findByTitleContainingIgnoreCase(String title);

    // Search by genre name (contains, case-insensitive)
    List<MovieEntity> findByGenre_NameContainingIgnoreCase(String genreName);

    // Endpoint 6: combined findBy (title AND/OR genre), used by the general search
    @Query("SELECT DISTINCT m FROM MovieEntity m " +
            "WHERE (:title IS NULL OR LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))) " +
            "AND (:genre IS NULL OR LOWER(m.genre.name) LIKE LOWER(CONCAT('%', :genre, '%')))")
    List<MovieEntity> searchByTitleOrGenre(@Param("title") String title, @Param("genre") String genre);
}
