package org.factoriaf5.year;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.factoriaf5.movie.MovieEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Year entity.
 * 1:N relation with MovieEntity -> a year can have many movies released in
 * it, but each movie is released in a single year.
 */
@Entity
@Table(name = "years")
public class YearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Mapped to "year_value" instead of "value": VALUE is a reserved word in H2
    // and breaks CREATE TABLE if used unquoted as a column name.
    @Column(name = "year_value", nullable = false, unique = true)
    private Integer value;

    @OneToMany(mappedBy = "year", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<MovieEntity> movies = new ArrayList<>();

    public YearEntity() {
    }

    public YearEntity(Integer value) {
        this.value = value;
    }

    public YearEntity(Long id, Integer value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<MovieEntity> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieEntity> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof YearEntity)) return false;
        YearEntity that = (YearEntity) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
